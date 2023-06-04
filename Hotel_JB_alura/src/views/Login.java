package views;


import java.awt.Color; 
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Component;


public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private int xMouse, yMouse;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Establecer el aspecto del sistema similar a Windows
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
    	setTitle(" -- ACCESO AL REGISTRO DEL HOTEL --");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/jbjavac_40x47.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        
        JPanel titleBarPanel = new JPanel();
        titleBarPanel.setBackground(Color.GRAY);
        titleBarPanel.setBounds(0, 0, getWidth(), 30);
        titleBarPanel.setLayout(null);
        getContentPane().add(titleBarPanel);
        
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Dubai", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 0, getWidth(), 30);
        titleBarPanel.add(lblTitle);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("USUARIO :");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setBounds(34, 85, 148, 27);
        contentPane.add(lblNewLabel);

        JLabel lblPassword = new JLabel("CONTRASEÑA :");
        lblPassword.setForeground(new Color(255, 255, 255));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
        lblPassword.setBounds(34, 165, 150, 27);
        contentPane.add(lblPassword);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.BOLD, 16));
        usernameField.setBounds(34, 118, 220, 30);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.BOLD, 16));
        passwordField.setBounds(34, 198, 220, 30);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLogin.setBounds(34, 260, 100, 25);
        contentPane.add(btnLogin);
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setForeground(new Color(0, 128, 0));
                btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setForeground(Color.BLACK);
                btnLogin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (attemptLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    MenuUsuario menu = new MenuUsuario();
                    menu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }
                dispose();
            }
        });

        JLabel lblNewJgoodiesLabel_3 = new JLabel("");
        lblNewJgoodiesLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewJgoodiesLabel_3.setIcon(new ImageIcon(Login.class.getResource("/imagenes/jbjavac_85x100.png")));
        lblNewJgoodiesLabel_3.setBounds(304, 85, 100, 112);
        contentPane.add(lblNewJgoodiesLabel_3);

        JButton btnNewButton = new JButton("CERRAR");
        btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setBounds(304, 260, 100, 25);
        contentPane.add(btnNewButton);
        btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButton.setForeground(Color.RED);
                btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButton.setForeground(Color.BLACK);
                btnNewButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JButton btnMenu = new JButton("MENU");
        btnMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMenu.setBounds(194, 260, 100, 25);
        contentPane.add(btnMenu);
        
        JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("ACCESO AL REGISTRO DEL HOTEL");
        lblNewJgoodiesLabel.setForeground(new Color(255, 255, 255));
        lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewJgoodiesLabel.setBounds(34, 22, 370, 27);
        contentPane.add(lblNewJgoodiesLabel);
        
        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(255, 255, 255));
        separator_1_2.setBackground(new Color(255, 255, 255));
        separator_1_2.setBounds(29, 57, 375, 2);
        contentPane.add(separator_1_2);
        
        JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("");
        lblNewJgoodiesLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/wall-blue560x350.png")));
        lblNewJgoodiesLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewJgoodiesLabel_1.setBounds(0, 0, 436, 313);
        contentPane.add(lblNewJgoodiesLabel_1);

        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirMenuPrincipal();
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenu.setForeground(Color.BLUE);
                btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenu.setForeground(Color.BLACK);
                btnMenu.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void abrirMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        dispose(); // Cierra la ventana actual (Login)
    }

    public boolean attemptLogin(String username, String password) {
        String jdbcUrl = "jdbc:mysql://localhost/JB_HOTEL?useTimeZone=true&serverTimeZone=UTC";
        String dbUser = "admin";
        String dbPassword = "bata1234";

        try {
            // Establecer la conexión a la base de datos
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Preparar la consulta SQL para verificar las credenciales del usuario
            String sql = "SELECT COUNT(*) FROM USUARIO WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Obtener el resultado
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    // Las credenciales son válidas, el inicio de sesión es exitoso
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Las credenciales son inválidas o se produjo un error en la conexión o consulta
        return false;
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
}
