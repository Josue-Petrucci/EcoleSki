package be.jp.jframe;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import be.jp.pojo.Accreditation;
import be.jp.pojo.Instructor;
import be.jp.pojo.Lesson;
import be.jp.pojo.LessonType;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class CreateLesson extends JFrame {

	private static final long serialVersionUID = 1L;
	private Instructor instructor = null;
	private Accreditation accreditation = null;
	private LessonType lessonType = null;
	private JPanel contentPane;
	private JTable tableInstructor;
	private DefaultTableModel modelInstructor;
	private JScrollPane scrollPane;
	private ArrayList<Instructor> listInstructor;
	private JButton btnSelectInstructor;
	private JPanel panelAccreditation;
	private JScrollPane scrollPane_1;
	private JButton btnSelectAccreditation;
	private JTable tableAccreditation;
	private DefaultTableModel modelAccreditation;
	private JPanel panelLessonType;
	private JScrollPane scrollPane_2;
	private JTable tableLessonType;
	private DefaultTableModel modelLessonType;
	private JButton btnCreate;
	private JButton btnCancele;
	private JCheckBox chckbxIsPrivate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateLesson frame = new CreateLesson();
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
	public CreateLesson() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelInstructor = new JPanel();
		panelInstructor.setBorder(new TitledBorder(null, "Instructor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInstructor.setBounds(10, 60, 350, 225);
		contentPane.add(panelInstructor);
		panelInstructor.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 15, 330, 150);
		panelInstructor.add(scrollPane);
		
		modelInstructor = new DefaultTableModel(new String[] { 
				"Name", 
				"Firstname", 
				"Email"} , 0);
		tableInstructor = new JTable(modelInstructor);
		scrollPane.setViewportView(tableInstructor);
		
		listInstructor = Instructor.getAllInstructor();
		for(Instructor i:listInstructor) {
			modelInstructor.addRow(new Object[] {
					i.getName(),
					i.getFirstname(),
					i.getEmail()});
		}
		
		btnSelectInstructor = new JButton("Select");
		btnSelectInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectInstructor();
				modelAccreditation.setRowCount(0);
				modelLessonType.setRowCount(0);
				for(Accreditation i:instructor.getListAccreditation()) {
					modelAccreditation.addRow(new Object[] {i.getName()});
				}
			}
		});
		btnSelectInstructor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSelectInstructor.setBounds(10, 175, 330, 44);
		panelInstructor.add(btnSelectInstructor);
		
		panelAccreditation = new JPanel();
		panelAccreditation.setLayout(null);
		panelAccreditation.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Accreditation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAccreditation.setBounds(370, 60, 250, 225);
		contentPane.add(panelAccreditation);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 15, 230, 150);
		panelAccreditation.add(scrollPane_1);
		
		modelAccreditation = new DefaultTableModel(new String[] {"Name"} , 0);
		tableAccreditation = new JTable(modelAccreditation);
		scrollPane_1.setViewportView(tableAccreditation);
		
		btnSelectAccreditation = new JButton("Select");
		btnSelectAccreditation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAccreditation();
				modelLessonType.setRowCount(0);
				for(LessonType i:accreditation.getListLessonType()) {
					modelLessonType.addRow(new Object[] {
							i.getLevelName(),
							i.getPrice()});
				}
			}
		});
		btnSelectAccreditation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSelectAccreditation.setBounds(10, 175, 230, 44);
		panelAccreditation.add(btnSelectAccreditation);
		
		panelLessonType = new JPanel();
		panelLessonType.setLayout(null);
		panelLessonType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Lesson type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelLessonType.setBounds(630, 60, 246, 190);
		contentPane.add(panelLessonType);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 15, 226, 165);
		panelLessonType.add(scrollPane_2);
		
		modelLessonType = new DefaultTableModel(new String[] {"Level" ,"Price"} , 0);
		tableLessonType = new JTable(modelLessonType);
		scrollPane_2.setViewportView(tableLessonType);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLessonType();
				createLesson();
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreate.setBounds(10, 295, 430, 55);
		contentPane.add(btnCreate);
		
		btnCancele = new JButton("Cancele");
		btnCancele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancele.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancele.setBounds(446, 295, 430, 55);
		contentPane.add(btnCancele);
		
		JLabel lblNewLabel = new JLabel("Create a lesson");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 876, 40);
		contentPane.add(lblNewLabel);
		
		chckbxIsPrivate = new JCheckBox("This lesson is private ?");
		chckbxIsPrivate.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxIsPrivate.setBounds(640, 264, 228, 21);
		contentPane.add(chckbxIsPrivate);
	}
	
	private void selectInstructor() {
		int selectedRow = tableInstructor.getSelectedRow();
		if(selectedRow != -1) {
			instructor = listInstructor.get(selectedRow);
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void selectAccreditation() {
		int selectedRow = tableAccreditation.getSelectedRow();
		if(selectedRow != -1) {
			accreditation = instructor.getListAccreditation().get(selectedRow);
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void selectLessonType() {
		int selectedRow = tableLessonType.getSelectedRow();
		if(selectedRow != -1) {
			lessonType = accreditation.getListLessonType().get(selectedRow);
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void createLesson() {
		if(instructor == null) {
			JOptionPane.showMessageDialog(null, "You must choose a instructor !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		} else if(accreditation == null) {
			JOptionPane.showMessageDialog(null, "You must choose a instructor !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			Lesson lesson = new Lesson(instructor, lessonType, chckbxIsPrivate.isSelected());
			if(lesson.isExist() == null) {
				if(lesson.createLesson())
					JOptionPane.showMessageDialog(null, "A lesson has been created !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Creation error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
			dispose();
			}
		}
	}
}
