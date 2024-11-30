package be.jp.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

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
		panel_1.setBounds(165, 10, 172, 91);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAddAInstructor = new JButton("Add a Instructor");
		btnAddAInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddInstructor addInstructor = new AddInstructor();
				addInstructor.setVisible(true);
			}
		});
		btnAddAInstructor.setBounds(10, 24, 152, 21);
		panel_1.add(btnAddAInstructor);
		
		JButton btnSeeAllThe = new JButton("See all the Instructor");
		btnSeeAllThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowsAllInstructor showsAllInstructor = new ShowsAllInstructor();
				showsAllInstructor.setVisible(true);
			}
		});
		btnSeeAllThe.setBounds(10, 55, 152, 21);
		panel_1.add(btnSeeAllThe);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Lesson", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 117, 172, 91);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnCreateALesson = new JButton("Create a lesson");
		btnCreateALesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateLesson createLesson = new CreateLesson();
				createLesson.setVisible(true);
			}
		});
		btnCreateALesson.setBounds(10, 25, 152, 21);
		panel_2.add(btnCreateALesson);
		
		JButton btnSeeAllLesson = new JButton("See all the lesson");
		btnSeeAllLesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowsAllLesson showsAllLesson = new ShowsAllLesson();
				showsAllLesson.setVisible(true);
			}
		});
		btnSeeAllLesson.setBounds(10, 56, 152, 21);
		panel_2.add(btnSeeAllLesson);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Booking", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1.setBounds(204, 111, 172, 91);
		contentPane.add(panel_2_1);
		
		JButton btnCreateBooking = new JButton("Create a booking");
		btnCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateBooking createBooking = new CreateBooking();
				createBooking.setVisible(true);
			}
		});
		btnCreateBooking.setBounds(10, 25, 152, 21);
		panel_2_1.add(btnCreateBooking);
		
		JButton btnSeeAllBooking = new JButton("See all the booking");
		btnSeeAllBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowsAllBooking showAllBooking = new ShowsAllBooking();
				showAllBooking.setVisible(true);
			}
		});
		btnSeeAllBooking.setBounds(10, 56, 152, 21);
		panel_2_1.add(btnSeeAllBooking);
	}
}
