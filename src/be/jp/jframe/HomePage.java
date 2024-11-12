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
		panel.setBounds(10, 10, 147, 91);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCreatSkier = new JButton("Add a Skier");
		btnCreatSkier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSkier addSkier = new AddSkier();
				addSkier.setVisible(true);
			}
		});
		btnCreatSkier.setBounds(10, 27, 127, 21);
		panel.add(btnCreatSkier);
		
		JButton btnShowsAllSkier = new JButton("See all the skier");
		btnShowsAllSkier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowsAllSkier showsAllSkier = new ShowsAllSkier();
				showsAllSkier.setVisible(true);
			}
		});
		btnShowsAllSkier.setBounds(10, 58, 127, 21);
		panel.add(btnShowsAllSkier);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Instructor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 111, 147, 91);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAddAInstructor = new JButton("Add a Instructor");
		btnAddAInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddInstructor addInstructor = new AddInstructor();
				addInstructor.setVisible(true);
			}
		});
		btnAddAInstructor.setBounds(10, 24, 127, 21);
		panel_1.add(btnAddAInstructor);
	}
}
