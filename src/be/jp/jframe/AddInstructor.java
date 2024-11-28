package be.jp.jframe;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.jp.pojo.Accreditation;
import be.jp.pojo.Instructor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddInstructor extends JFrame {

	private static final long serialVersionUID = 1L;
	ArrayList<Accreditation> acs = Accreditation.getAllAcreditation();
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfFirstname;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private JTextField tfStreet;
	private JTextField tfHouse;
	private JTextField tfCity;
	private JTextField tfPostalCode;
	private JDateChooser dcDob;
	private JComboBox cbAccreditation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddInstructor frame = new AddInstructor();
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
	public AddInstructor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 51, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Add a Instructor");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(0, 10, 270, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(10, 80, 65, 13);
		contentPane.add(lblFirstname);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(135, 48, 120, 19);
		contentPane.add(tfName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 109, 65, 13);
		contentPane.add(lblEmail);
		
		tfFirstname = new JTextField();
		tfFirstname.setColumns(10);
		tfFirstname.setBounds(135, 77, 120, 19);
		contentPane.add(tfFirstname);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(135, 106, 120, 19);
		contentPane.add(tfEmail);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(10, 135, 95, 13);
		contentPane.add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(135, 132, 120, 19);
		contentPane.add(tfPhone);
		
		JLabel lblDateOfBirht = new JLabel("Date of birht:");
		lblDateOfBirht.setBounds(10, 161, 86, 13);
		contentPane.add(lblDateOfBirht);
		
		dcDob = new JDateChooser();
		dcDob.setBounds(135, 158, 120, 19);
		contentPane.add(dcDob);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setBounds(10, 190, 45, 13);
		contentPane.add(lblStreet);
		
		tfStreet = new JTextField();
		tfStreet.setColumns(10);
		tfStreet.setBounds(135, 187, 120, 19);
		contentPane.add(tfStreet);
		
		JLabel lblHouseNumber = new JLabel("House number:");
		lblHouseNumber.setBounds(10, 216, 86, 13);
		contentPane.add(lblHouseNumber);
		
		tfHouse = new JTextField();
		tfHouse.setColumns(10);
		tfHouse.setBounds(135, 213, 120, 19);
		contentPane.add(tfHouse);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(10, 242, 86, 13);
		contentPane.add(lblCity);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(135, 239, 120, 19);
		contentPane.add(tfCity);
		
		JLabel lblIsInstructor = new JLabel("Postal code:");
		lblIsInstructor.setBounds(10, 268, 86, 13);
		contentPane.add(lblIsInstructor);
		
		tfPostalCode = new JTextField();
		tfPostalCode.setColumns(10);
		tfPostalCode.setBounds(135, 265, 120, 19);
		contentPane.add(tfPostalCode);
		
		JButton btnCancele = new JButton("Cancel");
		btnCancele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancele.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancele.setBounds(22, 366, 85, 40);
		contentPane.add(btnCancele);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInstructor();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.setBounds(158, 366, 85, 40);
		contentPane.add(btnAdd);
		
		JLabel lblNewLabel_2 = new JLabel("Accreditation");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(87, 306, 105, 13);
		contentPane.add(lblNewLabel_2);
		
		String[] sAcs = new String[acs.size()];
		for(int i = 0; i < acs.size(); i++) {
			sAcs[i] = acs.get(i).getName();
		}
		
		cbAccreditation = new JComboBox();
		cbAccreditation.setModel(new DefaultComboBoxModel<>(sAcs));
		cbAccreditation.setBounds(7, 329, 260, 20);
		contentPane.add(cbAccreditation);
	}
	
	private void addInstructor() {
		String name = tfName.getText();
		String firstname = tfFirstname.getText();
		String email = tfEmail.getText();
		String phone = tfPhone.getText();
		java.util.Date dob = dcDob.getDate();
		String street = tfStreet.getText();
		String house = tfHouse.getText();
		String city = tfCity.getText();
		String postalCode = tfPostalCode.getText();
		Accreditation a = acs.get(cbAccreditation.getSelectedIndex());
		
		Instructor i = new Instructor(name, firstname, email, phone, dob, street, house, city, postalCode, a);
		String message = i.creatInstructor();
		
		if(message != null) {
			if(!message.isEmpty()) {
				JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	            return;
			}
		}
		dispose();
	}
}
