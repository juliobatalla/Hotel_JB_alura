package views;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ReservasView extends JFrame {

    private JPanel contentPane;
    private JTextField txtValor;
    private JDateChooser txtFechaEntrada;
    private JDateChooser txtFechaSalida;
    private JComboBox<String> txtFormaPago;
    private int xMouse, yMouse;
    private JButton btnReservar;
    @SuppressWarnings("unused")
	private Connection connection;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JSeparator separator;

    public static void main(String[] args) {
        ReservasView frame = new ReservasView();
        frame.setVisible(true);
    }

    public ReservasView() {
    	setTitle("NUEVA RESERVACION");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/jbjavac_40x47.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 450);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.control);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel lblFechaDeEntrada = new JLabel("FECHA DE ENTRADA :");
        lblFechaDeEntrada.setForeground(new Color(255, 255, 255));
        lblFechaDeEntrada.setBounds(68, 103, 289, 25);
        contentPane.add(lblFechaDeEntrada);
        lblFechaDeEntrada.setFont(new Font("Tahoma", Font.BOLD, 14));

        txtFechaEntrada = new JDateChooser();
        txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-cal_25x28.png")));
        txtFechaEntrada.setToolTipText("");
        txtFechaEntrada.setBounds(68, 129, 289, 30);
        contentPane.add(txtFechaEntrada);
        txtFechaEntrada.setFont(new Font("Tahoma", Font.BOLD, 14));

        JLabel lblFechaDeSalida = new JLabel("FECHA DE SALIDA :");
        lblFechaDeSalida.setForeground(new Color(255, 255, 255));
        lblFechaDeSalida.setBounds(68, 175, 289, 25);
        contentPane.add(lblFechaDeSalida);
        lblFechaDeSalida.setFont(new Font("Tahoma", Font.BOLD, 14));

        txtFechaSalida = new JDateChooser();
        txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-cal_25x28.png")));
        txtFechaSalida.setBounds(68, 201, 289, 30);
        contentPane.add(txtFechaSalida);
        txtFechaSalida.setFont(new Font("Tahoma", Font.BOLD, 14));

        JLabel lblFormaDePago = new JLabel("FORMA DE PAGO :");
        lblFormaDePago.setForeground(new Color(255, 255, 255));
        lblFormaDePago.setBounds(68, 247, 289, 25);
        contentPane.add(lblFormaDePago);
        lblFormaDePago.setFont(new Font("Tahoma", Font.BOLD, 14));

        txtFormaPago = new JComboBox<String>();
        txtFormaPago.setModel(new DefaultComboBoxModel<String>(new String[] { "TARJETA DE CREDITO", "TARJETA DE DEBITO", "EFECTIVO", "CHEQUE" }));
        txtFormaPago.setBounds(68, 273, 289, 30);
        contentPane.add(txtFormaPago);
        txtFormaPago.setFont(new Font("Tahoma", Font.BOLD, 14));

        JLabel lblValor = new JLabel("VALOR :");
        lblValor.setForeground(new Color(255, 255, 255));
        lblValor.setBounds(68, 319, 289, 25);
        contentPane.add(lblValor);
        lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));

        txtValor = new JTextField();
        txtValor.setMargin(new Insets(2, 10, 2, 2));
        txtValor.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        txtValor.setBounds(68, 345, 289, 30);
        contentPane.add(txtValor);
        txtValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtValor.setColumns(10);

        btnReservar = new JButton("RESERVAR");
        btnReservar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReservar.setBounds(407, 312, 110, 25);
        contentPane.add(btnReservar);
        btnReservar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReservar.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent arg0) {
                try {
					reserva();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        });
        
		// Evento de ratón para el botónes
        btnReservar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReservar.setForeground(Color.BLUE);
				btnReservar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReservar.setForeground(Color.BLACK);
				btnReservar.setCursor(Cursor.getDefaultCursor());				
			}			
		});

        JLabel lblNewLabel_1 = new JLabel("Nueva Reservación");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel_1.setBounds(47, 26, 381, 35);
        contentPane.add(lblNewLabel_1);
        
        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCancelar.setBounds(407, 348, 110, 25);
        contentPane.add(btnCancelar);
        
		// Evento de ratón para el botónes
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setForeground(Color.LIGHT_GRAY);
				btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelar.setForeground(Color.BLACK);
				btnCancelar.setCursor(Cursor.getDefaultCursor());				
			}			
		});
        
        btnCancelar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	MenuUsuario menuUsuario = new MenuUsuario();
		    	menuUsuario.setVisible(true);
		    	dispose();
		    }
		});
        
        btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSalir.setBounds(407, 377, 110, 25);
        contentPane.add(btnSalir);
        
		// Evento de ratón para el botónes
        btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSalir.setForeground(Color.RED);
				btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSalir.setForeground(Color.BLACK);
				btnSalir.setCursor(Cursor.getDefaultCursor());				
			}			
		});
        
        btnSalir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
        
        separator = new JSeparator();
        separator.setBackground(new Color(255, 255, 255));
        separator.setBounds(33, 72, 484, 8);
        contentPane.add(separator);
        
        JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("");
        lblNewJgoodiesLabel.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/wall-recep.png")));
        lblNewJgoodiesLabel.setBounds(547, 0, 289, 413);
        contentPane.add(lblNewJgoodiesLabel);
        
        JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("");
        lblNewJgoodiesLabel_1.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/wall-blue.png")));
        lblNewJgoodiesLabel_1.setBounds(0, 0, 554, 413);
        contentPane.add(lblNewJgoodiesLabel_1);
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JB_HOTEL", "admin", "bata1234");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void reserva() throws SQLException {
        String fechaEntrada = new SimpleDateFormat("yyyy-MM-dd").format(txtFechaEntrada.getDate());
        String fechaSalida = new SimpleDateFormat("yyyy-MM-dd").format(txtFechaSalida.getDate());
        String formaPago = txtFormaPago.getSelectedItem().toString();
        double valor = Double.parseDouble(txtValor.getText());

        if (fechaEntrada == null || fechaSalida == null || formaPago.equals("") || valor == 0) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO reservas (id, fecha_entrada, fecha_salida, forma_pago, valor) VALUES (?, ?, ?, ?, ?)");
                ps.setString(1, UUID.randomUUID().toString().substring(0, 8)); // Generate a shorter UUID
                ps.setString(2, fechaEntrada);
                ps.setString(3, fechaSalida);
                ps.setString(4, formaPago);
                ps.setDouble(5, valor);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Reserva realizada exitosamente", "Reserva",
                        JOptionPane.INFORMATION_MESSAGE);

                ps.close();
                con.close();
                limpiarCampos();
                dispose();

                // Llama al código "RegistroHuesped"
                RegistroHuesped registroHuesped = new RegistroHuesped();
                registroHuesped.setVisible(true);
            } catch (SQLException e) {              
                
                
            }
        }
    }

    public void limpiarCampos() {
        txtFechaEntrada.setDate(null); // Limpiar fecha de entrada
        txtFechaSalida.setDate(null); // Limpiar fecha de salida
        txtFormaPago.setSelectedIndex(0); // Restaurar forma de pago al primer elemento
        txtValor.setText(""); // Limpiar el campo de valor
    }

	public int getxMouse() {
		return xMouse;
	}

	public void setxMouse(int xMouse) {
		this.xMouse = xMouse;
	}

	public int getyMouse() {
		return yMouse;
	}

	public void setyMouse(int yMouse) {
		this.yMouse = yMouse;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}

