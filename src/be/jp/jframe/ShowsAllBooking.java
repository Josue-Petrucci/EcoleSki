package be.jp.jframe;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jp.pojo.Booking;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowsAllBooking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Booking> listBooking = Booking.getAllBooking();
	private List<Booking> filteredBooking = new ArrayList<>();
	private JButton btnCancel;
	private JButton btnRefresh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowsAllBooking frame = new ShowsAllBooking();
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
	public ShowsAllBooking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 800, 300);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel(new String[] {
				"ID",
				"Category",
				"Level lesson", 
				"Name instructor", 
				"Name Skier",
				"Schedule",
				"Price"} , 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(707, 320, 103, 64);
		contentPane.add(btnCancel);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				showsAllBooking();
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRefresh.setBounds(481, 320, 103, 64);
		contentPane.add(btnRefresh);
		
		showsAllBooking();
	}
	
	private void showsAllBooking() {
		filteredBooking.clear();
		for(Booking b:listBooking) {
			filteredBooking.add(b);
			model.addRow(new Object[] {
					b.getId(),
					b.getLesson().getLessonType().getAccreditation().getName(),
					b.getLesson().getLessonType().getLevelName(),
					b.getLesson().getInstructor().getName().toUpperCase() + " " + b.getLesson().getInstructor().getFirstname().toUpperCase(),
					b.getSkier().getName().toUpperCase() + " " + b.getSkier().getFirstname().toUpperCase(),
					b.getSchedules(),
					b.calculatePrice()});
		}
	}
}
