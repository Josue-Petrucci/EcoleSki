package be.jp.jframe;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ShowsAllBooking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Booking> listBooking = Booking.getAllBooking();
	private List<Booking> filteredBooking = new ArrayList<>();
	private JButton btnCancel;
	private JButton btnRefresh;
	private JButton btnDelete;
	private JTextField tfNameInstructor;
	private JTextField tfFirstnameINstructor;
	private JTextField tfFirstnameSkier;
	private JTextField tfNameSkier;

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
		setBounds(100, 100, 829, 439);
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
		btnCancel.setBounds(715, 320, 95, 64);
		contentPane.add(btnCancel);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				showsAllBooking();
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRefresh.setBounds(505, 320, 95, 64);
		contentPane.add(btnRefresh);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteLesson();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(610, 320, 95, 64);
		contentPane.add(btnDelete);
		
		JLabel lblNameInstructor = new JLabel("Name instructor:");
		lblNameInstructor.setBounds(20, 320, 110, 20);
		contentPane.add(lblNameInstructor);
		
		JLabel lblFirstnameInstructor = new JLabel("Firstname instructor:");
		lblFirstnameInstructor.setBounds(20, 345, 110, 20);
		contentPane.add(lblFirstnameInstructor);
		
		tfNameInstructor = new JTextField();
		tfNameInstructor.setBounds(140, 320, 110, 20);
		contentPane.add(tfNameInstructor);
		tfNameInstructor.setColumns(10);
		
		tfFirstnameINstructor = new JTextField();
		tfFirstnameINstructor.setColumns(10);
		tfFirstnameINstructor.setBounds(140, 345, 110, 20);
		contentPane.add(tfFirstnameINstructor);
		
		JButton btnSearchInstructor = new JButton("Search instructor");
		btnSearchInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				searchInstructor();
			}
		});
		btnSearchInstructor.setBounds(20, 372, 230, 21);
		contentPane.add(btnSearchInstructor);
		
		JButton btnSearchSkier = new JButton("Search skier");
		btnSearchSkier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				searchSkier();
			}
		});
		btnSearchSkier.setBounds(258, 372, 230, 21);
		contentPane.add(btnSearchSkier);
		
		tfFirstnameSkier = new JTextField();
		tfFirstnameSkier.setColumns(10);
		tfFirstnameSkier.setBounds(378, 345, 110, 20);
		contentPane.add(tfFirstnameSkier);
		
		JLabel lblFirstnameSkier = new JLabel("Firstname skier:");
		lblFirstnameSkier.setBounds(258, 345, 110, 20);
		contentPane.add(lblFirstnameSkier);
		
		JLabel lblNameSkier = new JLabel("Name skier:");
		lblNameSkier.setBounds(258, 320, 110, 20);
		contentPane.add(lblNameSkier);
		
		tfNameSkier = new JTextField();
		tfNameSkier.setColumns(10);
		tfNameSkier.setBounds(378, 320, 110, 20);
		contentPane.add(tfNameSkier);
		
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
	
	private void searchInstructor() {
		filteredBooking.clear();
		for(Booking b:listBooking) {
			if(b.getLesson().getInstructor().getName().equals(tfNameInstructor.getText()) & b.getLesson().getInstructor().getFirstname().equals(tfFirstnameINstructor.getText())) {
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
	
	private void searchSkier() {
		filteredBooking.clear();
		for(Booking b:listBooking) {
			if(b.getSkier().getName().equals(tfNameSkier.getText()) & b.getSkier().getFirstname().equals(tfFirstnameSkier.getText())) {
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
	
	private void deleteLesson() {
		int selectedRow = table.getSelectedRow();
		if(selectedRow != -1) {
			Booking b = filteredBooking.get(selectedRow);
			if(b.deleteBooking()) {
				listBooking.remove(b);
				filteredBooking.remove(selectedRow);
				model.removeRow(selectedRow);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
