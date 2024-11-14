package be.jp.jframe;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jp.pojo.Instructor;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowsAllInstructor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Instructor> listInstructor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowsAllInstructor frame = new ShowsAllInstructor();
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
	public ShowsAllInstructor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 766, 270);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel(new String[] {
				"ID", 
				"Name", 
				"Firstname", 
				"Email", 
				"Phone"} , 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(673, 289, 103, 64);
		contentPane.add(btnCancel);
		
		listInstructor = Instructor.getAllInstructor();
		
		for(Instructor i:listInstructor) {
			model.addRow(new Object[] {
					i.getId(),
					i.getName(),
					i.getFirstname(),
					i.getEmail(),
					i.getPhone()
			});
		}
	}
}
