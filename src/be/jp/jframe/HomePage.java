package be.jp.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Skier", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 147, 133);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCreatSkier = new JButton("Add a Skier");
		btnCreatSkier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSkier addSkier = new AddSkier();
				addSkier.setVisible(true);
			}
		});
		btnCreatSkier.setBounds(10, 40, 127, 21);
		panel.add(btnCreatSkier);
	}
}
