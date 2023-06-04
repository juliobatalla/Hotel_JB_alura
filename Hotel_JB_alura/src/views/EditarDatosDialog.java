package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import ConnectionFactory.MySQLConnector;

@SuppressWarnings("serial")
public class EditarDatosDialog extends JFrame {

    private JTextField searchField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField fechaNacimientoField;
    private JTextField nacionalidadField;
    private JTextField telefonoField;
    private JTextField fechaEntradaField;
    private JTextField fechaSalidaField;
    private JTextField valorField;
    private JTextField formaPagoField;

    public EditarDatosDialog(String searchText) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(EditarDatosDialog.class.getResource("/imagenes/jbjavac_40x47.png")));
        JPanel mainPanel = createUI(searchText);
        getContentPane().add(mainPanel, BorderLayout.NORTH);

        cargarDatos(searchText);
        setSize(540, 500);
        setLocationRelativeTo(null); // Centrar la ventana en el monitor
    }
    

    public JPanel createUI(String searchText) {
        JPanel mainPanel = new JPanel();
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        GridBagLayout gbl_mainPanel = new GridBagLayout();
        gbl_mainPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        gbl_mainPanel.rowHeights = new int[]{25, 60, 35, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
        gbl_mainPanel.columnWeights = new double[]{0.0, 0.0};
        gbl_mainPanel.columnWidths = new int[]{0, 0};
        mainPanel.setLayout(gbl_mainPanel);
        
        JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("EDICION DE REGISTRO");
        lblNewJgoodiesLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        lblNewJgoodiesLabel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        lblNewJgoodiesLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        GridBagConstraints gbc_lblNewJgoodiesLabel = new GridBagConstraints();
        gbc_lblNewJgoodiesLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewJgoodiesLabel.gridx = 1;
        gbc_lblNewJgoodiesLabel.gridy = 1;
        mainPanel.add(lblNewJgoodiesLabel, gbc_lblNewJgoodiesLabel);

        JLabel lblRegistro = new JLabel("NUMERO DE REGISTRO : ");
        lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblRegistro = new GridBagConstraints();
        gbc_lblRegistro.anchor = GridBagConstraints.EAST;
        gbc_lblRegistro.insets = new Insets(0, 0, 5, 5);
        gbc_lblRegistro.gridx = 0;
        gbc_lblRegistro.gridy = 2;
        mainPanel.add(lblRegistro, gbc_lblRegistro);

        searchField = new JTextField();
        searchField.setMargin(new Insets(2, 10, 2, 2));
        searchField.setForeground(new Color(255, 0, 0));
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchField.setColumns(20);
        searchField.setText(searchText);
        searchField.setEditable(false); // Hacer el campo no editable
        GridBagConstraints gbc_searchField = new GridBagConstraints();
        gbc_searchField.insets = new Insets(0, 0, 5, 0);
        gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
        gbc_searchField.gridx = 1;
        gbc_searchField.gridy = 2;
        mainPanel.add(searchField, gbc_searchField);

        JLabel lblNombre = new JLabel("NOMBRE : ");
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.anchor = GridBagConstraints.EAST;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 3;
        mainPanel.add(lblNombre, gbc_lblNombre);

        nombreField = new JTextField();
        nombreField.setMargin(new Insets(2, 10, 2, 2));
        nombreField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_nombreField = new GridBagConstraints();
        gbc_nombreField.insets = new Insets(0, 0, 5, 0);
        gbc_nombreField.fill = GridBagConstraints.HORIZONTAL;
        gbc_nombreField.gridx = 1;
        gbc_nombreField.gridy = 3;
        mainPanel.add(nombreField, gbc_nombreField);
        nombreField.setColumns(20);

        JLabel lblApellido = new JLabel("APELLIDO : ");
        lblApellido.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblApellido = new GridBagConstraints();
        gbc_lblApellido.anchor = GridBagConstraints.EAST;
        gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
        gbc_lblApellido.gridx = 0;
        gbc_lblApellido.gridy = 4;
        mainPanel.add(lblApellido, gbc_lblApellido);

        apellidoField = new JTextField();
        apellidoField.setMargin(new Insets(2, 10, 2, 2));
        apellidoField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_apellidoField = new GridBagConstraints();
        gbc_apellidoField.insets = new Insets(0, 0, 5, 0);
        gbc_apellidoField.fill = GridBagConstraints.HORIZONTAL;
        gbc_apellidoField.gridx = 1;
        gbc_apellidoField.gridy = 4;
        mainPanel.add(apellidoField, gbc_apellidoField);
        apellidoField.setColumns(20);
        
        JLabel lblFechaNacimiento = new JLabel("FECHA DE NACIMIENTO : ");
        lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
        gbc_lblFechaNacimiento.anchor = GridBagConstraints.EAST;
        gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaNacimiento.gridx = 0;
        gbc_lblFechaNacimiento.gridy = 5;
        mainPanel.add(lblFechaNacimiento, gbc_lblFechaNacimiento);

        fechaNacimientoField = new JTextField();
        fechaNacimientoField.setMargin(new Insets(2, 10, 2, 2));
        fechaNacimientoField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_fechaNacimientoField = new GridBagConstraints();
        gbc_fechaNacimientoField.insets = new Insets(0, 0, 5, 0);
        gbc_fechaNacimientoField.fill = GridBagConstraints.HORIZONTAL;
        gbc_fechaNacimientoField.gridx = 1;
        gbc_fechaNacimientoField.gridy = 5;
        mainPanel.add(fechaNacimientoField, gbc_fechaNacimientoField);
        fechaNacimientoField.setColumns(20);
        
        JLabel lblNacionalidad = new JLabel("NACIONALIDAD : ");
        lblNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
        gbc_lblNacionalidad.anchor = GridBagConstraints.EAST;
        gbc_lblNacionalidad.insets = new Insets(0, 0, 5, 5);
        gbc_lblNacionalidad.gridx = 0;
        gbc_lblNacionalidad.gridy = 6;
        mainPanel.add(lblNacionalidad, gbc_lblNacionalidad);

        nacionalidadField = new JTextField();
        nacionalidadField.setMargin(new Insets(2, 10, 2, 2));
        nacionalidadField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_nacionalidadField = new GridBagConstraints();
        gbc_nacionalidadField.insets = new Insets(0, 0, 5, 0);
        gbc_nacionalidadField.fill = GridBagConstraints.HORIZONTAL;
        gbc_nacionalidadField.gridx = 1;
        gbc_nacionalidadField.gridy = 6;
        mainPanel.add(nacionalidadField, gbc_nacionalidadField);
        nacionalidadField.setColumns(20);
        
        JLabel lblTelefono = new JLabel("TELEFONO : ");
        lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
        gbc_lblTelefono.anchor = GridBagConstraints.EAST;
        gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
        gbc_lblTelefono.gridx = 0;
        gbc_lblTelefono.gridy = 7;
        mainPanel.add(lblTelefono, gbc_lblTelefono);

        telefonoField = new JTextField();
        telefonoField.setMargin(new Insets(2, 10, 2, 2));
        telefonoField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_telefonoField = new GridBagConstraints();
        gbc_telefonoField.insets = new Insets(0, 0, 5, 0);
        gbc_telefonoField.fill = GridBagConstraints.HORIZONTAL;
        gbc_telefonoField.gridx = 1;
        gbc_telefonoField.gridy = 7;
        mainPanel.add(telefonoField, gbc_telefonoField);
        telefonoField.setColumns(20);
        
        JLabel lblFechaEntrada = new JLabel("FECHA DE ENTRADA : ");
        lblFechaEntrada.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblFechaEntrada = new GridBagConstraints();
        gbc_lblFechaEntrada.anchor = GridBagConstraints.EAST;
        gbc_lblFechaEntrada.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaEntrada.gridx = 0;
        gbc_lblFechaEntrada.gridy = 8;
        mainPanel.add(lblFechaEntrada, gbc_lblFechaEntrada);

        fechaEntradaField = new JTextField();
        fechaEntradaField.setMargin(new Insets(2, 10, 2, 2));
        fechaEntradaField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_fechaEntradaField = new GridBagConstraints();
        gbc_fechaEntradaField.insets = new Insets(0, 0, 5, 0);
        gbc_fechaEntradaField.fill = GridBagConstraints.HORIZONTAL;
        gbc_fechaEntradaField.gridx = 1;
        gbc_fechaEntradaField.gridy = 8;
        mainPanel.add(fechaEntradaField, gbc_fechaEntradaField);
        fechaEntradaField.setColumns(20);
        
        JLabel lblFechaSalida = new JLabel("FECHA DE SALIDA : ");
        lblFechaSalida.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblFechaSalida = new GridBagConstraints();
        gbc_lblFechaSalida.anchor = GridBagConstraints.EAST;
        gbc_lblFechaSalida.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaSalida.gridx = 0;
        gbc_lblFechaSalida.gridy = 9;
        mainPanel.add(lblFechaSalida, gbc_lblFechaSalida);

        fechaSalidaField = new JTextField();
        fechaSalidaField.setMargin(new Insets(2, 10, 2, 2));
        fechaSalidaField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_fechaSalidaField = new GridBagConstraints();
        gbc_fechaSalidaField.insets = new Insets(0, 0, 5, 0);
        gbc_fechaSalidaField.fill = GridBagConstraints.HORIZONTAL;
        gbc_fechaSalidaField.gridx = 1;
        gbc_fechaSalidaField.gridy = 9;
        mainPanel.add(fechaSalidaField, gbc_fechaSalidaField);
        fechaSalidaField.setColumns(20);
        
        JLabel lblValor = new JLabel("VALOR :");
        lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblValor = new GridBagConstraints();
        gbc_lblValor.anchor = GridBagConstraints.EAST;
        gbc_lblValor.insets = new Insets(0, 0, 5, 5);
        gbc_lblValor.gridx = 0;
        gbc_lblValor.gridy = 10;
        mainPanel.add(lblValor, gbc_lblValor);

        valorField = new JTextField();
        valorField.setMargin(new Insets(2, 10, 2, 2));
        valorField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_valorField = new GridBagConstraints();
        gbc_valorField.insets = new Insets(0, 0, 5, 0);
        gbc_valorField.fill = GridBagConstraints.HORIZONTAL;
        gbc_valorField.gridx = 1;
        gbc_valorField.gridy = 10;
        mainPanel.add(valorField, gbc_valorField);
        valorField.setColumns(20);
        
        JLabel lblFormaPago = new JLabel("FORMA DE PAGO : ");
        lblFormaPago.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_lblFormaPago = new GridBagConstraints();
        gbc_lblFormaPago.anchor = GridBagConstraints.EAST;
        gbc_lblFormaPago.insets = new Insets(0, 0, 5, 5);
        gbc_lblFormaPago.gridx = 0;
        gbc_lblFormaPago.gridy = 11;
        mainPanel.add(lblFormaPago, gbc_lblFormaPago);

        formaPagoField = new JTextField();
        formaPagoField.setMargin(new Insets(2, 10, 2, 2));
        formaPagoField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_formaPagoField = new GridBagConstraints();
        gbc_formaPagoField.insets = new Insets(0, 0, 5, 0);
        gbc_formaPagoField.fill = GridBagConstraints.HORIZONTAL;
        gbc_formaPagoField.gridx = 1;
        gbc_formaPagoField.gridy = 11;
        mainPanel.add(formaPagoField, gbc_formaPagoField);
        formaPagoField.setColumns(20);
        

        JButton btnSave = new JButton("GUARDAR");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });
        GridBagConstraints gbc_btnSave = new GridBagConstraints();
        gbc_btnSave.gridx = 1;
        gbc_btnSave.gridy = 12;
        mainPanel.add(btnSave, gbc_btnSave);

        return mainPanel;
    }

    public void cargarDatos(String registro) {
        try {
            Connection conn = MySQLConnector.getConnection();
            String sql = "SELECT NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO FROM huespedes WHERE ID_RESERVA = ?";
            String sql1 = "SELECT FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO FROM reservas WHERE ID = ?";
            PreparedStatement huespedes = conn.prepareStatement(sql);
            PreparedStatement reservas = conn.prepareStatement(sql1);
            huespedes.setString(1, registro);
            reservas.setString(1, registro);
            ResultSet resulthuespedes = huespedes.executeQuery();
            ResultSet resultreservas = reservas.executeQuery();

            while (resulthuespedes.next()) {
                String nombre = resulthuespedes.getString("NOMBRE");
                String apellido = resulthuespedes.getString("APELLIDO");
                String fechaNacimiento = resulthuespedes.getString("FECHA_NACIMIENTO");
                String nacionalidad = resulthuespedes.getString("NACIONALIDAD");
                String telefono = resulthuespedes.getString("TELEFONO");

                nombreField.setText(nombre);
                apellidoField.setText(apellido);
                fechaNacimientoField.setText(fechaNacimiento);
                nacionalidadField.setText(nacionalidad);
                telefonoField.setText(telefono);
            }
            
            while (resultreservas.next()) {
                String fechaEntrada = resultreservas.getString("FECHA_ENTRADA");
                String fechaSalida = resultreservas.getString("FECHA_SALIDA");
                String valor = resultreservas.getString("VALOR");
                String formaPago = resultreservas.getString("FORMA_PAGO");

                fechaEntradaField.setText(fechaEntrada);
                fechaSalidaField.setText(fechaSalida);
                valorField.setText(valor);
                formaPagoField.setText(formaPago);

            }
            

            huespedes.close();
            reservas.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void guardarCambios() {
        try {
            Connection conn = MySQLConnector.getConnection();
            String registro = searchField.getText();
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String fechaNacimiento = fechaNacimientoField.getText();
            String nacionalidad = nacionalidadField.getText();
            String telefono = telefonoField.getText();
            String fechaEntrada = fechaEntradaField.getText();
            String fechaSalida = fechaSalidaField.getText();
            String valor = valorField.getText();
            String formaPago = formaPagoField.getText();

            String sqlHuespedes = "UPDATE huespedes SET NOMBRE = ?, APELLIDO = ?, FECHA_NACIMIENTO = ?, NACIONALIDAD = ?, TELEFONO = ? WHERE ID_RESERVA = ?";
            String sqlReservas = "UPDATE reservas SET FECHA_ENTRADA = ?, FECHA_SALIDA = ?, VALOR = ?, FORMA_PAGO = ? WHERE ID = ?";
            PreparedStatement statementHuespedes = conn.prepareStatement(sqlHuespedes);
            PreparedStatement statementReservas = conn.prepareStatement(sqlReservas);
            
            statementHuespedes.setString(1, nombre);
            statementHuespedes.setString(2, apellido);
            statementHuespedes.setString(3, fechaNacimiento);
            statementHuespedes.setString(4, nacionalidad);
            statementHuespedes.setString(5, telefono);
            statementHuespedes.setString(6, registro);
            
            statementReservas.setString(1, fechaEntrada);
            statementReservas.setString(2, fechaSalida);
            statementReservas.setString(3, valor);
            statementReservas.setString(4, formaPago);
            statementReservas.setString(5, registro); // Assuming ID is the primary key for the 'reservas' table
            
            int rowsUpdatedHuespedes = statementHuespedes.executeUpdate();
            int rowsUpdatedReservas = statementReservas.executeUpdate();
            
            if (rowsUpdatedHuespedes > 0 && rowsUpdatedReservas > 0) {
                JOptionPane.showMessageDialog(this, "Los cambios se guardaron exitosamente.");
                dispose();
                // Abrir la ventana de búsqueda
                // abrirVentanaBusqueda();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar los cambios.");
            }

            statementHuespedes.close();
            statementReservas.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EditarDatosDialog dialog = new EditarDatosDialog("1");
                dialog.setVisible(true);
            }
        });
    }
    
   /* public void abrirVentanaBusqueda() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Busqueda busqueda = new Busqueda();
                busqueda.setVisible(true);
            }
        });
    } */

}

