package be.jp.jframe;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jp.pojo.Skier;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ShowsAllSkier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Skier> skiers;
	private int selectedRow;
	private JTextField tfNameS;
	private JTextField tfFirstnameS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowsAllSkier frame = new ShowsAllSkier();
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
	public ShowsAllSkier() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 766, 269);
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
		
		JButton btnSelect = new JButton("Select the skier");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectSkier();
			}
		});
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSelect.setBounds(476, 289, 162, 64);
		contentPane.add(btnSelect);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(20, 289, 90, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Firstname:");
		lblNewLabel_1.setBounds(20, 328, 90, 25);
		contentPane.add(lblNewLabel_1);
		
		tfNameS = new JTextField();
		tfNameS.setBounds(120, 290, 150, 25);
		contentPane.add(tfNameS);
		tfNameS.setColumns(10);
		
		tfFirstnameS = new JTextField();
		tfFirstnameS.setColumns(10);
		tfFirstnameS.setBounds(120, 328, 150, 25);
		contentPane.add(tfFirstnameS);
		
		JButton btnNewButton = new JButton("Search a skier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchSkier();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(283, 289, 162, 64);
		contentPane.add(btnNewButton);
		
		skiers = Skier.findsAllSkier();
		for(Skier s:skiers) {
			model.addRow(new Object[] {
					s.getId(),
					s.getName(),
					s.getFirstname(),
					s.getEmail(),
					s.getPhone()
			});
		}
	}
	
	private void selectSkier() {
		selectedRow = table.getSelectedRow();
		if(selectedRow != -1) {
			Skier s = skiers.get(selectedRow);
			ShowSkier showSkier = new ShowSkier(this, s, selectedRow);
			showSkier.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void searchSkier() {
		String message = "";
		String nameSearch = tfNameS.getText();
		String firstnameSearch = tfFirstnameS.getText();
		
		if(nameSearch.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Name is required!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		} else if(firstnameSearch.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Firstname is required!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (!nameSearch.matches("[a-zA-ZÀ-ÿ]+")) {
			JOptionPane.showMessageDialog(this, "The name must contain only letters!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
        } else if(!firstnameSearch.matches("[a-zA-ZÀ-ÿ]+")) {
        	JOptionPane.showMessageDialog(this, "The firstname must contain only letters!", "ERROR", JOptionPane.ERROR_MESSAGE);
        	return;
        }
		
		if(message.isEmpty()) {
			boolean skierFound = false;
			int row = 0;
			for(Skier s:skiers) {
				if(s.getName().equalsIgnoreCase(nameSearch) && s.getFirstname().equalsIgnoreCase(firstnameSearch)) {
					ShowSkier showSkier = new ShowSkier(this, s, row);
					showSkier.setVisible(true);
					skierFound = true;
					break;
				}
				row++;
			}
			if(!skierFound) {
				JOptionPane.showMessageDialog(this, "Skier don't found !", "ERROR", JOptionPane.ERROR_MESSAGE);
        		return;
			}
		}
	}
	
	public void deleteTableRow(int rowIndex) {
		skiers.remove(rowIndex);
		model.removeRow(rowIndex);
    }
	
	public void updateTableRow(Skier updatedSkier, int rowIndex) {
        model.setValueAt(updatedSkier.getName(), rowIndex, 1);
        model.setValueAt(updatedSkier.getFirstname(), rowIndex, 2);
        model.setValueAt(updatedSkier.getEmail(), rowIndex, 3);
        model.setValueAt(updatedSkier.getPhone(), rowIndex, 4);
    }
}
