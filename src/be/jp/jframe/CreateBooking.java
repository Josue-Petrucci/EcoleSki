package be.jp.jframe;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jp.pojo.Accreditation;
import be.jp.pojo.Booking;
import be.jp.pojo.Lesson;
import be.jp.pojo.Skier;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;

public class CreateBooking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableLesson;
	private DefaultTableModel modelLesson;
	private ArrayList<Lesson> listLesson = Lesson.getAllLesson();;
	private List<Lesson> filteredLessons = new ArrayList<>();
	private JTable tableSkier;
	private DefaultTableModel modelSkier;
	private ArrayList<Skier> listSkier = Skier.findsAllSkier();
	private List<Skier> filteredSkiers = new ArrayList<>();
	private JTextField tfName;
	private JTextField tfFirstname;
	private JComboBox cbSchedule;
	private Lesson lesson;
	private Skier skier;
	private JCheckBox chckbxInsurance;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateBooking frame = new CreateBooking();
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
	public CreateBooking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create a Booking");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 799, 41);
		contentPane.add(lblNewLabel);
		
		modelLesson = new DefaultTableModel(new String[] { 
				"Name instructor", 
				"Category", 
				"Level",
				"Type of lesson"} , 0);
		
		ArrayList<Accreditation> listAccreditation = Accreditation.getAllAcreditation();
		String[] sAcs = new String[listAccreditation.size()];
		for(int i = 0; i < listAccreditation.size(); i++) {
			sAcs[i] = listAccreditation.get(i).getName();
		}
		
		modelSkier = new DefaultTableModel(new String[] { 
				"Name",
				"Firstname",
				"Phone"} , 0);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Lesson", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 49, 472, 460);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnResearchLesson = new JButton("Research");
		btnResearchLesson.setBounds(375, 381, 85, 30);
		panel.add(btnResearchLesson);
		btnResearchLesson.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JButton btnRefreshLesson = new JButton("Refresh");
		btnRefreshLesson.setBounds(292, 381, 80, 30);
		panel.add(btnRefreshLesson);
		btnRefreshLesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelLesson.setRowCount(0);
				showsLesson();
			}
		});
		btnRefreshLesson.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JComboBox cbAccreditation = new JComboBox();
		cbAccreditation.setBounds(10, 381, 270, 30);
		panel.add(cbAccreditation);
		cbAccreditation.setModel(new DefaultComboBoxModel<>(sAcs));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 450, 351);
		panel.add(scrollPane);
		tableLesson = new JTable(modelLesson);
		scrollPane.setViewportView(tableLesson);
		
		JButton btnChoseLesson = new JButton("Chose the lesson");
		btnChoseLesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configSchedule();
			}
		});
		btnChoseLesson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChoseLesson.setBounds(10, 421, 452, 29);
		panel.add(btnChoseLesson);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Skier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(492, 49, 339, 339);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 24, 319, 245);
		panel_1.add(scrollPane_1);
		tableSkier = new JTable(modelSkier);
		scrollPane_1.setViewportView(tableSkier);
		
		JButton btnRefreshSkier = new JButton("Refresh");
		btnRefreshSkier.setBounds(245, 309, 85, 20);
		panel_1.add(btnRefreshSkier);
		btnRefreshSkier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelSkier.setRowCount(0);
				showsSkier();
			}
		});
		btnRefreshSkier.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		tfFirstname = new JTextField();
		tfFirstname.setBounds(80, 309, 155, 20);
		panel_1.add(tfFirstname);
		tfFirstname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Firstname:");
		lblNewLabel_1_1.setBounds(10, 309, 70, 20);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(10, 279, 70, 20);
		panel_1.add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setBounds(80, 279, 155, 20);
		panel_1.add(tfName);
		tfName.setColumns(10);
		
		JButton btnResearchSkier = new JButton("Research");
		btnResearchSkier.setBounds(244, 279, 85, 20);
		panel_1.add(btnResearchSkier);
		btnResearchSkier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelSkier.setRowCount(0);
				searchSkier();
			}
		});
		btnResearchSkier.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblNewLabel_2 = new JLabel("Start Date: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(502, 398, 106, 30);
		contentPane.add(lblNewLabel_2);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(618, 398, 200, 30);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_2_1 = new JLabel("Insurance:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(502, 439, 106, 30);
		contentPane.add(lblNewLabel_2_1);
		
		chckbxInsurance = new JCheckBox("");
		chckbxInsurance.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxInsurance.setBounds(618, 439, 200, 30);
		contentPane.add(chckbxInsurance);
		
		cbSchedule = new JComboBox();
		cbSchedule.setModel(new DefaultComboBoxModel(new String[] {"Select a lesson !!"}));
		cbSchedule.setBounds(618, 479, 203, 30);
		contentPane.add(cbSchedule);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Schedule:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(502, 479, 106, 30);
		contentPane.add(lblNewLabel_2_1_1);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createBooking();
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreate.setBounds(10, 519, 821, 40);
		contentPane.add(btnCreate);
		
		JButton btnCalcule = new JButton("Calculate the price");
		btnCalcule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculetePrice();
			}
		});
		btnCalcule.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCalcule.setBounds(10, 569, 400, 40);
		contentPane.add(btnCalcule);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(431, 569, 400, 40);
		contentPane.add(btnCancel);
		btnResearchLesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelLesson.setRowCount(0);
				researchAccreditation(listAccreditation.get(cbAccreditation.getSelectedIndex()));
			}
		});
		
		showsLesson();
		showsSkier();
	}
	
	private void showsLesson() {
		filteredLessons.clear();
		for(Lesson l:listLesson) {
			filteredLessons.add(l);
			boolean isprivate = l.getIsPrivate();
			String m = "Public";
			if(isprivate) {
				m = "Private";
			}			
			modelLesson.addRow(new Object[] {
					l.getInstructor().getName() + " " + l.getInstructor().getFirstname(),
					l.getLessonType().getAccreditation().getName(),
					l.getLessonType().getLevelName(),
					m
			});
		}
	}
	
	private void showsSkier() {
		filteredSkiers.clear();
		for(Skier s:listSkier) {
			filteredSkiers.add(s);
			modelSkier.addRow(new Object[] {
					s.getName(), 
					s.getFirstname(),
					s.getPhone()});
		}
	}
	
	private void researchAccreditation(Accreditation accreditation) {
		filteredLessons.clear();
		for(Lesson l:listLesson) {
			if(l.getLessonType().getAccreditation().equals(accreditation)) {
				filteredLessons.add(l);
				modelLesson.addRow(new Object[] {
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
	
	private void searchSkier() {
		String message = "";
		String nameSearch = tfName.getText();
		String firstnameSearch = tfFirstname.getText();
		
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
		
		filteredSkiers.clear();
		for(Skier s:listSkier) {
			if(tfName.getText().toUpperCase().equals(s.getName().toUpperCase()) & tfFirstname.getText().toUpperCase().equals(s.getFirstname().toUpperCase())) {
				filteredSkiers.add(s);
				modelSkier.addRow(new Object[] {
						s.getName(), 
						s.getFirstname(),
						s.getPhone()});
			}
		}
	}
	
	private void configSchedule() {
		int selectedRow = tableLesson.getSelectedRow();
		if(selectedRow != -1) {
			lesson = filteredLessons.get(selectedRow);
			if(!lesson.getIsPrivate()) {
				cbSchedule.setModel(new DefaultComboBoxModel(new String[] {
						"Matin : de 9:00 à 12:00",
						"Après-midi : de 14:00 à 17:00",
						"Les deux : de 9:00 à 12:00 et de 14:00 à 17:00"}));
			} else {
				cbSchedule.setModel(new DefaultComboBoxModel(new String[] {
						"de 12:00 à 13:00",
						"de 13:00 à 14:00",
						"de 12:00 à 14:00"}));
			}
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line lesson !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void calculetePrice() {
		if(lesson != null) {
			Booking booking = new Booking((String)cbSchedule.getSelectedItem(), lesson, chckbxInsurance.isSelected());
			JOptionPane.showMessageDialog(null, "The price of the lesson : " + booking.calculatePrice() + "€", "Price", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line lesson !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void createBooking() {
		if(lesson == null) {
			JOptionPane.showMessageDialog(null, "You must choose a line lesson !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int selectedRow = tableSkier.getSelectedRow();
		if(selectedRow != -1) {
			skier = filteredSkiers.get(selectedRow);
		} else {
			JOptionPane.showMessageDialog(null, "You must choose a line Skier !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
		    Date minDate = sdf.parse("06/12/2024");
		    Date maxDate = sdf.parse("03/05/2025");
		    Date selectedDate = dateChooser.getDate();

		    if (selectedDate.before(minDate) || selectedDate.after(maxDate)) {
		        JOptionPane.showMessageDialog(null, 
		            "The date must be between 06/12/2024 to 03/05/2025!", 
		            "ERROR", 
		            JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		Booking booking = new Booking(
				chckbxInsurance.isSelected(),
				(String)cbSchedule.getSelectedItem(),
				dateChooser.getDate(),
				lesson,
				skier);
		if(booking.hasPlace()) {
			if(booking.createBooking())
				JOptionPane.showMessageDialog(null, "A lesson has been created !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Creation error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "There are too many registered to this lesson !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
