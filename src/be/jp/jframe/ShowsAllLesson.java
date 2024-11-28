package be.jp.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jp.pojo.Accreditation;
import be.jp.pojo.Instructor;
import be.jp.pojo.Lesson;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ShowsAllLesson extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Lesson> listLesson;
	private JTextField tfName;

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
		
		JButton button = new JButton("New button");
		button.setBounds(678, 332, -120, -29);
		contentPane.add(button);
		
		JButton btnDeleteLesson = new JButton("Delete");
		btnDeleteLesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteLesson();
			}
		});
		btnDeleteLesson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteLesson.setBounds(660, 289, 103, 64);
		contentPane.add(btnDeleteLesson);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				showsAllLesson();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(547, 289, 103, 64);
		contentPane.add(btnNewButton);
		
		ArrayList<Accreditation> listAccreditation = Accreditation.getAllAcreditation();
		String[] sAcs = new String[listAccreditation.size()];
		for(int i = 0; i < listAccreditation.size(); i++) {
			sAcs[i] = listAccreditation.get(i).getName();
		}
		
		JComboBox cbAccreditation = new JComboBox();
		cbAccreditation.setModel(new DefaultComboBoxModel<>(sAcs));
		cbAccreditation.setBounds(10, 289, 280, 30);
		contentPane.add(cbAccreditation);
		
		JButton btnResearch = new JButton("Research");
		btnResearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				researchAccreditation(listAccreditation.get(cbAccreditation.getSelectedIndex()));
			}
		});
		btnResearch.setBounds(91, 325, 100, 30);
		contentPane.add(btnResearch);
		
		tfName = new JTextField();
		tfName.setBounds(365, 289, 170, 30);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JButton btnResearchInstructor = new JButton("Research an instructor");
		btnResearchInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				researchInstructor(tfName.getText());
			}
		});
		btnResearchInstructor.setBounds(300, 325, 235, 30);
		contentPane.add(btnResearchInstructor);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(309, 298, 45, 13);
		contentPane.add(lblNewLabel);
		
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
	
	private void deleteLesson() {
		int selectedRow = table.getSelectedRow();
		if(selectedRow != -1) {
			Lesson l = listLesson.get(selectedRow);
			if(l.deleteLesson()) {
				listLesson.remove(selectedRow);
				model.removeRow(selectedRow);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void researchAccreditation(Accreditation accreditation) {
		for(Lesson l:listLesson) {
			if(l.getLessonType().getAccreditation().equals(accreditation)) {
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
	
	private void researchInstructor(String nameInstructor) {
		for(Lesson l:listLesson) {
			if(l.getInstructor().getName().equals(nameInstructor)) {
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
}
