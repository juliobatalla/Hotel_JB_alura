package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame {
	private JPanel contentPane;
	private JLabel lblCopyR;
	int xMouse, yMouse;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuPrincipal() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/jbjavac_40x47.png")));
		;
		setBackground(new Color(0, 128, 192));
		setTitle(" -- RECEPCION HOTEL  -- ");
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

		// Etiqueta de derechos de autor
		lblCopyR = new JLabel("Julio Batalla © 2023");
		lblCopyR.setBounds(29, 366, 214, 36);
		contentPane.add(lblCopyR);
		lblCopyR.setHorizontalAlignment(SwingConstants.LEFT);
		lblCopyR.setForeground(new Color(255, 255, 255));
		lblCopyR.setFont(new Font("Dubai", Font.BOLD, 18));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/wall-recep03.png")));
		lblNewLabel.setBounds(692, 0, 144, 413);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/wall_min01.png")));
		lblNewLabel_2.setBounds(0, 0, 254, 413);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_5 = new JLabel("LOGIN");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(543, 225, 94, 36);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("RECEPCION");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Dubai", Font.BOLD, 34));
		lblNewLabel_6.setBounds(488, 11, 194, 58);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/jbjavac_85x100.png")));
		lblNewLabel_7.setBounds(533, 98, 104, 116);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewJgoodiesLabel.setDisplayedMnemonic(KeyEvent.VK_CODE_INPUT);
		lblNewJgoodiesLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewJgoodiesLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/icon-user_100x100.png")));
		lblNewJgoodiesLabel.setBounds(536, 286, 118, 116);
		contentPane.add(lblNewJgoodiesLabel);

		lblNewJgoodiesLabel.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				
				dispose();
			}
		});

		lblNewJgoodiesLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblNewJgoodiesLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				lblNewJgoodiesLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
			}
		});

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/wall_min02.png")));
		lblNewLabel_3.setBounds(211, 0, 254, 413);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/wall-blueBar.png")));
		lblNewLabel_1.setBounds(463, 0, 230, 413);
		contentPane.add(lblNewLabel_1);

		applyVisualImprovements();
	}

	private void applyVisualImprovements() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		contentPane.setBackground(Color.WHITE);

		lblCopyR.setBackground(Color.WHITE);
	}
}
