package be.jp.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jp.pojo.Lesson;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ShowsAllLesson extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Lesson> listLesson;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowsAllLesson frame = new ShowsAllLesson();
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
	public ShowsAllLesson() {
		this.listLesson = Lesson.getAllLesson();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(773, 289, 103, 64);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 866, 270);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel(new String[] {
				"ID",
				"Category",
				"Level lesson", 
				"Name instructor", 
				"Email instructor",
				"Phone instructor",
				"Minimum bookings",
				"Maxim bookings"} , 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		showsAllLesson();
		
		
		
	}
	
	private void showsAllLesson() {
		for(Lesson l:listLesson) {
			model.addRow(new Object[] {
					l.getId(),
					l.getLessonType().getAccreditation().getName(),
					l.getLessonType().getLevelName(),
					l.getInstructor().getName() + " " + l.getInstructor().getFirstname(),
					l.getInstructor().getEmail(),
					l.getInstructor().getPhone(),
					l.getMinBookings(),
					l.getMaxBookings()});
		}
	}
}
