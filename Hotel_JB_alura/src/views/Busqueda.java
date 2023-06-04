package views;


import java.awt.Color ;  
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ConnectionFactory.MySQLConnector;
import java.awt.Toolkit;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class Busqueda extends JFrame {
    private MySQLConnector connector;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea textArea;

    public Busqueda() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/jbjavac_40x47.png")));
        connector = new MySQLConnector();

        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(49, 85, 276, 36);
        searchField = new JTextField(12);
        searchField.setForeground(new Color(255, 0, 0));
        searchField.setFont(new Font("Tahoma", Font.BOLD, 14));
        searchField.setMargin(new Insets(2, 5, 2, 2));
        searchButton = new JButton("BUSCAR");
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarReg();
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.add(searchPanel);

        setContentPane(mainPanel);
        
        textArea = new JTextArea();
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        textArea.setBackground(new Color(232, 243, 255));
        textArea.setMargin(new Insets(8, 8, 8, 8));
        textArea.setFont(new Font("Tahoma", Font.BOLD, 13));
        textArea.setColumns(2);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBounds(49, 132, 276, 256);
        mainPanel.add(textArea);
        setTitle(" -- BUSQUEDA DE REGISTROS --");
        setSize(850, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JButton modifyButton = new JButton("MODIFICAR");
        modifyButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir el diálogo EditarDatosDialog
                EditarDatosDialog dialog = new EditarDatosDialog(searchField.getText());
                dialog.setVisible(true);
            }
        });
        mainPanel.add(modifyButton);
        modifyButton.setBounds(355, 220, 110, 25);
        
        modifyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	modifyButton.setForeground(Color.BLUE);
            	modifyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	modifyButton.setForeground(Color.BLACK);
            	modifyButton.setCursor(Cursor.getDefaultCursor());
            }
        });
        
        JButton deleteButton = new JButton("ELIMINAR");
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar1();
            }
        });
        mainPanel.add(deleteButton);
        deleteButton.setBounds(355, 350, 110, 25);
        
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	deleteButton.setForeground(Color.RED);
            	deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	deleteButton.setForeground(Color.BLACK);
            	deleteButton.setCursor(Cursor.getDefaultCursor());
            }
        });
        
        JButton btnMenu = new JButton("MENU");
        btnMenu.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnMenu.setBounds(355, 292, 110, 25);
        mainPanel.add(btnMenu);
        
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnMenu.setForeground(Color.BLUE);
            	btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnMenu.setForeground(Color.BLACK);
            	btnMenu.setCursor(Cursor.getDefaultCursor());
            }
        });
        
        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuUsuario menuUsuario = new MenuUsuario();
                menuUsuario.setVisible(true);
                dispose(); 
            }
        });
        
        
        JButton btnCancelar = new JButton("CANCELAR");
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCancelar.setBounds(355, 256, 110, 25);
        mainPanel.add(btnCancelar);
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuUsuario menuUsuario = new MenuUsuario();
                menuUsuario.setVisible(true);
                dispose(); 
            }
        });
        
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnCancelar.setForeground(Color.RED);
            	btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnCancelar.setForeground(Color.BLACK);
            	btnCancelar.setCursor(Cursor.getDefaultCursor());
            }
        });
        
        JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("REGISTRO DE HUESPEDES");
        lblNewJgoodiesLabel.setForeground(new Color(255, 255, 255));
        lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewJgoodiesLabel.setBounds(13, 22, 452, 38);
        mainPanel.add(lblNewJgoodiesLabel);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(22, 60, 452, 14);
        mainPanel.add(separator);
        
        JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("");
        lblNewJgoodiesLabel_1.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/wall-recep02.png")));
        lblNewJgoodiesLabel_1.setBounds(537, 0, 299, 413);
        mainPanel.add(lblNewJgoodiesLabel_1);
        
        JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("");
        lblNewJgoodiesLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/wall-blue.png")));
        lblNewJgoodiesLabel_2.setBounds(0, 0, 543, 413);
        mainPanel.add(lblNewJgoodiesLabel_2);        
    }

    public void buscarReg() {
        String searchText = searchField.getText();
        try {
        	Connection connection = connector.conectarBase();
            String sqlHuespedes = "SELECT ID_RESERVA, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO FROM huespedes WHERE ID_RESERVA = ?";
            String sqlReservas = "SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO FROM reservas WHERE ID = ?";
            PreparedStatement statementHuespedes = connection.prepareStatement(sqlHuespedes);
            PreparedStatement statementReservas = connection.prepareStatement(sqlReservas);
            statementHuespedes.setString(1, searchText);
            statementReservas.setString(1, searchText);
            ResultSet resultSetHuespedes = statementHuespedes.executeQuery();
            ResultSet resultSetReservas = statementReservas.executeQuery();

            StringBuilder dataBuilder = new StringBuilder();

            dataBuilder.append("Huespedes:\n");
            while (resultSetHuespedes.next()) {
                String idReserva = resultSetHuespedes.getString("ID_RESERVA");
                String nombre = resultSetHuespedes.getString("NOMBRE");
                String apellido = resultSetHuespedes.getString("APELLIDO");
                String fechaNacimiento = resultSetHuespedes.getString("FECHA_NACIMIENTO");
                String nacionalidad = resultSetHuespedes.getString("NACIONALIDAD");
                String telefono = resultSetHuespedes.getString("TELEFONO");

                dataBuilder.append("ID_RESERVA: ").append(idReserva).append("\n");
                dataBuilder.append("Nombre: ").append(nombre).append("\n");
                dataBuilder.append("Apellido: ").append(apellido).append("\n");
                dataBuilder.append("Fecha de Nacimiento: ").append(fechaNacimiento).append("\n");
                dataBuilder.append("Nacionalidad: ").append(nacionalidad).append("\n");
                dataBuilder.append("Teléfono: ").append(telefono).append("\n");
                dataBuilder.append("\n");
            }

            dataBuilder.append("Reservas:\n");
            while (resultSetReservas.next()) {
                String id = resultSetReservas.getString("ID");
                String fechaEntrada = resultSetReservas.getString("FECHA_ENTRADA");
                String fechaSalida = resultSetReservas.getString("FECHA_SALIDA");
                double valor = resultSetReservas.getDouble("VALOR");
                String formaPago = resultSetReservas.getString("FORMA_PAGO");

                dataBuilder.append("ID: ").append(id).append("\n");
                dataBuilder.append("Fecha de Entrada: ").append(fechaEntrada).append("\n");
                dataBuilder.append("Fecha de Salida: ").append(fechaSalida).append("\n");
                dataBuilder.append("Valor: ").append(valor).append("\n");
                dataBuilder.append("Forma de Pago: ").append(formaPago).append("\n");
                dataBuilder.append("\n");
            }

            textArea.setText(dataBuilder.toString());

            resultSetHuespedes.close();
            resultSetReservas.close();
            statementHuespedes.close();
            statementReservas.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarReg() {
        String searchText = searchField.getText();
        EditarDatosDialog dialog = new EditarDatosDialog(searchText);
        dialog.setVisible(true);
    }
    
    public void eliminar1() {
        String searchText = searchField.getText();
        
        int confirm = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
            	Connection connection = connector.conectarBase();
                String sqlHuespedes = "DELETE FROM huespedes WHERE ID_RESERVA = ?";
                String sqlReservas = "DELETE FROM reservas WHERE ID = ?";
                PreparedStatement statementHuespedes = connection.prepareStatement(sqlHuespedes);
                PreparedStatement statementReservas = connection.prepareStatement(sqlReservas);
                statementHuespedes.setString(1, searchText);
                statementReservas.setString(1, searchText);
                statementHuespedes.executeUpdate();
                statementReservas.executeUpdate();
                JOptionPane.showMessageDialog(this, "El registro ha sido eliminado exitosamente.");
                statementHuespedes.close();
                statementReservas.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Busqueda busqueda = new Busqueda();
                busqueda.setVisible(true);
            }
        });
    }

    public void buscarReservas() {
        // TODO Auto-generated method stub
        
    }

    public void buscarHuespedes() {
        // TODO Auto-generated method stub
        
    }

	protected void eliminar() {
		// TODO Auto-generated method stub
		
	}

	protected void modificar() {
		// TODO Auto-generated method stub
		
	}

	protected void buscar() {
		// TODO Auto-generated method stub
		
	}
}
