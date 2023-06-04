package views;


import java.awt.Color; 
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import java.awt.Insets;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textField_1;
	private JTextField textField_2;
	private JDateChooser dateChooser;
	private JTextField textField_3;
	private JTextField textFieldTelefono;

	private Connection connection;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHuesped frame = new RegistroHuesped();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegistroHuesped() {
		setTitle(" -- REGISTRO HUESPEDES --");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/jbjavac_40x47.png")));
		connection = getConnection();;
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
		int ultimoIdAi = obtenerUltimoIdAi();
		String ultimoIdReserva = obtenerUltimoId(ultimoIdAi);

		JLabel lblTitulo = new JLabel("REGISTRO HUESPEDES");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(24, 21, 486, 30);
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblTitulo);

		JLabel lblId = new JLabel("ID RESERVACION :");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblId.setBounds(34, 72, 220, 30);
		contentPane.add(lblId);

		textFieldId = new JTextField();
		textFieldId.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldId.setMargin(new Insets(2, 10, 2, 2));
		textFieldId.setBackground(null);
		textFieldId.setBorder(null);
		textFieldId.setForeground(new Color(255, 0, 0));
		textFieldId.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldId.setBounds(284, 72, 226, 30);
		textFieldId.setEditable(false);
		textFieldId.setText(ultimoIdReserva);
		contentPane.add(textFieldId);

		JLabel lblNombres = new JLabel("NOMBRES :");
		lblNombres.setForeground(new Color(255, 255, 255));
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombres.setBounds(34, 113, 190, 30);
		contentPane.add(lblNombres);

		textField_1 = new JTextField();
		textField_1.setMargin(new Insets(2, 10, 2, 2));
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(34, 142, 220, 30);
		contentPane.add(textField_1);

		JLabel lblApellidos = new JLabel("APELLIDOS :");
		lblApellidos.setForeground(new Color(255, 255, 255));
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellidos.setBounds(290, 113, 209, 30);
		contentPane.add(lblApellidos);

		textField_2 = new JTextField();
		textField_2.setMargin(new Insets(2, 10, 2, 2));
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(290, 142, 220, 30);
		contentPane.add(textField_2);

		JLabel lblFechaNacimiento = new JLabel("FECHA NACIMIENTO :");
		lblFechaNacimiento.setForeground(new Color(255, 255, 255));
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaNacimiento.setBounds(34, 183, 190, 30);
		contentPane.add(lblFechaNacimiento);

		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 18));
		dateChooser.getCalendarButton();
		dateChooser.setBounds(34, 212, 220, 30);
		contentPane.add(dateChooser);

		JLabel lblNacionalidad = new JLabel("NACIONALIDAD :");
		lblNacionalidad.setForeground(new Color(255, 255, 255));
		lblNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNacionalidad.setBounds(290, 183, 190, 30);
		contentPane.add(lblNacionalidad);

		textField_3 = new JTextField();
		textField_3.setMargin(new Insets(2, 10, 2, 2));
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(290, 212, 219, 30);
		contentPane.add(textField_3);

		JLabel lblTelefono = new JLabel("TELÉFONO :");
		lblTelefono.setForeground(new Color(255, 255, 255));
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefono.setBounds(34, 253, 209, 30);
		contentPane.add(lblTelefono);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setMargin(new Insets(2, 10, 2, 2));
		textFieldTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(34, 283, 220, 30);
		contentPane.add(textFieldTelefono);

		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertarHuesped();
			}
		});
		btnAgregar.setForeground(new Color(0, 0, 0));
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgregar.setBounds(400, 318, 110, 25);
		contentPane.add(btnAgregar);
		
		// Evento de ratón para el botónes
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAgregar.setForeground(Color.BLUE);
				btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAgregar.setForeground(Color.BLACK);
				btnAgregar.setCursor(Cursor.getDefaultCursor());
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 59, 486, 2);
		contentPane.add(separator);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/wall-recep.png")));
		lblNewJgoodiesLabel.setBounds(548, 0, 288, 413);
		contentPane.add(lblNewJgoodiesLabel);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.setBounds(400, 354, 110, 25);
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
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_1.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/wall-blue.png")));
		lblNewJgoodiesLabel_1.setBounds(0, 0, 556, 413);
		contentPane.add(lblNewJgoodiesLabel_1);
	}

	public static Connection getConnection() {
		Connection connection = null;

		try {
			String url = "jdbc:mysql://localhost:3306/JB_HOTEL";
			String username = "admin";
			String password = "bata1234";
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos: " + e.getMessage());
		}

		return connection;
	}

	private int obtenerUltimoIdAi() {
		int ultimoIdAi = 0;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String query = "SELECT ID_AI FROM reservas ORDER BY ID_AI DESC LIMIT 1";
			pst = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
			if (rs.next()) {
				ultimoIdAi = rs.getInt("ID_AI");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al obtener el último ID: " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ultimoIdAi;
	}

	private String obtenerUltimoId(int ultimoIdAi) {
		String ultimoId = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String query = "SELECT ID FROM reservas WHERE id_ai = ?";
			pst = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pst.setInt(1, ultimoIdAi);
			rs = pst.executeQuery();
			if (rs.last()) {
				ultimoId = rs.getString("ID");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al obtener el último ID: " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ultimoId;
	}

	private void insertarHuesped() {

		String numeroReserva = textFieldId.getText();
		String nombres = textField_1.getText();
		String apellidos = textField_2.getText();

	    java.util.Date fechaNacimientoUtil = dateChooser.getDate();
	    if (fechaNacimientoUtil == null) {
	        JOptionPane.showMessageDialog(null, "Seleccione una fecha de nacimiento");
	        return;
	    }
	    java.sql.Date fechaNacimiento = new java.sql.Date(fechaNacimientoUtil.getTime());


		String nacionalidad = textField_3.getText();
		String telefono = textFieldTelefono.getText();

		// Verificar que todos los campos estén llenos
		if (numeroReserva.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || nacionalidad.isEmpty()
				|| telefono.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
			return;
		}

		try {
			String query = "INSERT INTO huespedes (ID_RESERVA, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, numeroReserva);
			pst.setString(2, nombres);
			pst.setString(3, apellidos);
			pst.setDate(4, fechaNacimiento);
			pst.setString(5, nacionalidad);
			pst.setString(6, telefono);

			int rowsAffected = pst.executeUpdate();
			
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Huesped registrado correctamente");
	            textFieldId.setText("");
	            textField_1.setText("");
	            textField_2.setText("");
	            dateChooser.setDate(null);
	            textField_3.setText("");
				
	            // Abrir la clase ReservasView después de mostrar el mensaje
	            dispose();
	            ReservasView reservasView = new ReservasView();
	            reservasView.setVisible(true);
	        } else {
	            JOptionPane.showMessageDialog(null, "Error al registrar el huesped");
	        }

	        // ...
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al insertar huesped: " + e.getMessage());
	    }
	}

	//PARA ELIMINAR REGISTRO
	
	/*
	private void eliminarReserva() {
		String idReserva = textFieldId.getText();

		try {
			String query = "DELETE FROM reservas WHERE ID = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, idReserva);

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
			} else {
				JOptionPane.showMessageDialog(null, "No se encontró el registro");
			}

			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + e.getMessage());
		}
	} */
}
