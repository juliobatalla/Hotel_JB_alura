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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Insets;

import views.ReservasView;
import views.Busqueda;
import views.UserRegistrationView;


@SuppressWarnings({ "serial", "unused" })
public class MenuUsuario extends JFrame {
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JLabel lblFecha;
	private JTextArea textArea;

	private int xMouse, yMouse;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUsuario frame = new MenuUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuUsuario() {
		setTitle(" -- Sistema de Reservación del Hotel --");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/imagenes/jbjavac_40x47.png")));
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

		lblNewLabel_1 = new JLabel("Sistema de Reservación del Hotel");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(34, 24, 467, 30);
		contentPane.add(lblNewLabel_1);

		lblFecha = new JLabel("DATE");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setForeground(new Color(255, 255, 255));
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFecha.setBounds(269, 366, 242, 36);
		contentPane.add(lblFecha);

		Date fechaActual = new Date();
		String fecha = new SimpleDateFormat("dd MMMM yyyy").format(fechaActual);
		lblFecha.setText("Hoy es: " + fecha);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(new Color(0, 0, 0, 0));
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setText(
				"Controle y administre de forma óptima y fácil."
				+ "\nEsta herramienta le permitirá llevar un control completo "
				+ "y detallado de sus reservas y huéspedes, tendrá acceso a "
				+ "herramientas especiales para tareas específicas "
				+ "como lo son:\n\n- Registro de Reservas y Huéspedes."
				+ "\n- Edición de Reservas y Huéspedes existentes."
				+ "\n- Eliminar todo tipo de registros."
				+ "\n- Agregar nuevos usuarios.");
		textArea.setBounds(51, 84, 450, 197);
		contentPane.add(textArea);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel(" ");
		lblNewJgoodiesLabel.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/wall-recep.png")));
		lblNewJgoodiesLabel.setBounds(550, 0, 286, 413);
		contentPane.add(lblNewJgoodiesLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(0, 64, 128));
		separator.setBounds(34, 65, 477, 2);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("REGISTRO");
		btnNewButton.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(161, 292, 110, 25);
		contentPane.add(btnNewButton);
		
		// Evento de ratón para el botónes
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setForeground(Color.BLUE);
				btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setForeground(Color.BLACK);
				btnNewButton.setCursor(Cursor.getDefaultCursor());
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ReservasView reservasView = new ReservasView();
		        reservasView.setVisible(true);
		        dispose();
		    }
		});

		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setMargin(new Insets(2, 2, 2, 2));
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscar.setAlignmentY(0.0f);
		btnBuscar.setBounds(281, 292, 110, 25);
		contentPane.add(btnBuscar);
		
		// Evento de ratón para el botónes
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuscar.setForeground(Color.BLUE);
				btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBuscar.setForeground(Color.BLACK);
				btnBuscar.setCursor(Cursor.getDefaultCursor());
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Busqueda busqueda = new Busqueda();
		    	busqueda.setVisible(true);
		    	 dispose();
		    }
		});
		
		JButton btnNewUser = new JButton("NEW USER");
		btnNewUser.setMargin(new Insets(2, 2, 2, 2));
		btnNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewUser.setAlignmentY(0.0f);
		btnNewUser.setBounds(401, 292, 110, 25);
		contentPane.add(btnNewUser);
		
		// Evento de ratón para el botónes
		btnNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewUser.setForeground(Color.LIGHT_GRAY);
				btnNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewUser.setForeground(Color.BLACK);
				btnNewUser.setCursor(Cursor.getDefaultCursor());
			}
		});
		

		btnNewUser.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        UserRegistrationView userRegistrationView = null;
				try {
					userRegistrationView = new UserRegistrationView();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        userRegistrationView.setVisible(true);
		    }
		});

		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setMargin(new Insets(2, 2, 2, 2));
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalir.setAlignmentY(0.0f);
		btnSalir.setBounds(401, 328, 110, 25);
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

		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_1.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/wall-blue.png")));
		lblNewJgoodiesLabel_1.setBounds(0, 0, 554, 413);
		contentPane.add(lblNewJgoodiesLabel_1);

		contentPane.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xMouse, y - yMouse);
			}
		});
	}
}

