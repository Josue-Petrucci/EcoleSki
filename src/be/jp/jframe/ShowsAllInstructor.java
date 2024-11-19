package be.jp.jframe;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jp.pojo.Instructor;
import be.jp.pojo.Skier;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ShowsAllInstructor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Instructor> listInstructor;
	private int selectedRow;
	private JTextField tfNameS;
	private JTextField tfPhoneS;

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
		
		JButton btnSelect = new JButton("Select the Instructor");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectInstructor();
			}
		});
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSelect.setBounds(466, 290, 197, 64);
		contentPane.add(btnSelect);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(20, 289, 90, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Phone:");
		lblNewLabel_1.setBounds(20, 328, 90, 25);
		contentPane.add(lblNewLabel_1);
		
		tfNameS = new JTextField();
		tfNameS.setBounds(120, 290, 150, 25);
		contentPane.add(tfNameS);
		tfNameS.setColumns(10);
		
		tfPhoneS = new JTextField();
		tfPhoneS.setColumns(10);
		tfPhoneS.setBounds(120, 328, 150, 25);
		contentPane.add(tfPhoneS);
		
		JButton btnNewButton = new JButton("Search a instructor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchInstructor();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(283, 289, 162, 64);
		contentPane.add(btnNewButton);
		
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
	
	private void selectInstructor() {
		selectedRow = table.getSelectedRow();
		if(selectedRow != -1) {
			Instructor in = listInstructor.get(selectedRow);
			showInstructor sin = new showInstructor(this, in, selectedRow);
			sin.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void searchInstructor() {
		String message = "";
		String nameSearch = tfNameS.getText();
		String phoneSearch = tfPhoneS.getText();
		
		if(nameSearch.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Name is required!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		} else if(phoneSearch.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Firstname is required!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (!nameSearch.matches("[a-zA-ZÀ-ÿ]+")) {
			JOptionPane.showMessageDialog(this, "The name must contain only letters!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
        } else if(!phoneSearch.matches("^(\\+\\d{1,3}|00\\d{1,3})?[- ]?(0?\\d{1,2})?([- ]?\\d{2}){4}$")) {
        	JOptionPane.showMessageDialog(this, "The firstname must contain only letters!", "ERROR", JOptionPane.ERROR_MESSAGE);
        	return;
        }
		
		if(message.isEmpty()) {
			boolean instructorFound = false;
			int row = 0;
			for(Instructor in:listInstructor) {
				if(in.getName().equalsIgnoreCase(nameSearch) && in.getPhone().equalsIgnoreCase(phoneSearch)) {
					showInstructor sin = new showInstructor(this, in, row);
					sin.setVisible(true);
					instructorFound = true;
				}
				row++;
			}
			if(!instructorFound) {
				JOptionPane.showMessageDialog(this, "Skier don't found !", "ERROR", JOptionPane.ERROR_MESSAGE);
        		return;
			}
		}
	}
}
