package views;


import java.awt.Color ; 
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
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
import javax.swing.UIManager;

import ConnectionFactory.MySQLConnector;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class UserRegistrationView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton closeButton;
    private JLabel lblNewLabel;

    public UserRegistrationView() throws IOException {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\julio\\eclipse-workspace\\Hotel_JB_alura\\src\\imagenes\\jbjavac_40x47.png"));
        setTitle(" - REGISTRO DE USUARIO -");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(350, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblUsername = new JLabel("NUEVO USUARIO :");
        lblUsername.setFont(new Font("Dubai", Font.BOLD, 14));
        lblUsername.setBounds(49, 40, 219, 28);
        panel.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.BOLD, 14));
        usernameField.setBounds(49, 70, 240, 28);
        panel.add(usernameField);

        JLabel lblPassword = new JLabel("AGREGAR CONTRASEÑA :");
        lblPassword.setFont(new Font("Dubai", Font.BOLD, 14));
        lblPassword.setBounds(49, 109, 219, 28);
        panel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordField.setBounds(49, 139, 240, 28);
        panel.add(passwordField);

        registerButton = new JButton("REGISTRAR");
        registerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        registerButton.setBounds(179, 178, 110, 30);
        panel.add(registerButton);

        closeButton = new JButton("CERRAR");
        closeButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        closeButton.setBounds(49, 178, 110, 30);
        panel.add(closeButton);     
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\julio\\eclipse-workspace\\Hotel_JB_alura\\src\\imagenes\\jbjavac_40x47.png"));
        lblNewLabel.setBounds(283, 208, 53, 55);
        panel.add(lblNewLabel);
       

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un usuario y una contraseña", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (username.length() < 5 || username.length() > 20) {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario debe tener entre 5 y 20 caracteres",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (password.length() < 8 || password.length() > 20) {
                    JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 8 y 20 caracteres", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        // Establecer la conexión con la base de datos
                        MySQLConnector mySQLConnector = MySQLConnector.getInstance();
                        Connection connection = mySQLConnector.conectarBase();

                        // Verificar si el usuario o la contraseña ya existen
                        String checkQuery = "SELECT COUNT(*) FROM usuario WHERE username = ? OR password = ?";
                        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
                        checkStatement.setString(1, username);
                        checkStatement.setString(2, password);
                        ResultSet resultSet = checkStatement.executeQuery();
                        resultSet.next();
                        int count = resultSet.getInt(1);
                        resultSet.close();
                        checkStatement.close();

                        if (count > 0) {
                            JOptionPane.showMessageDialog(null, "El usuario o la contraseña ya existen", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Crear la consulta preparada para insertar el usuario en la tabla
                            String sql = "INSERT INTO usuario (username, password) VALUES (?, ?)";
                            PreparedStatement statement = connection.prepareStatement(sql);
                            statement.setString(1, username);
                            statement.setString(2, password);

                            // Ejecutar la consulta
                            int rowsInserted = statement.executeUpdate();

                            // Cerrar la conexión y liberar recursos
                            statement.close();
                            connection.close();

                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
                                dispose();
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al registrar el usuario", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                closeButton.setBackground(Color.LIGHT_GRAY);
                closeButton.setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent e) {
                closeButton.setCursor(Cursor.getDefaultCursor());
                closeButton.setBackground(null);
                closeButton.setForeground(Color.BLACK);
            }
        });
    }

    public static void main(String[] args) {
        try {
            // Establecer el look and feel de la interfaz gráfica
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserRegistrationView window = new UserRegistrationView();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

