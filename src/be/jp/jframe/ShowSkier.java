package be.jp.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.jp.pojo.Skier;

import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowSkier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowSkier frame = new ShowSkier(null, null);
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
	public ShowSkier(ShowsAllSkier frame, Skier s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 66, 95, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstname.setBounds(10, 89, 95, 13);
		contentPane.add(lblFirstname);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 112, 95, 13);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhone.setBounds(10, 135, 95, 13);
		contentPane.add(lblPhone);
		
		JLabel lblDateOfBirht = new JLabel("Date of birht:");
		lblDateOfBirht.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDateOfBirht.setBounds(10, 158, 95, 13);
		contentPane.add(lblDateOfBirht);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStreet.setBounds(10, 181, 95, 13);
		contentPane.add(lblStreet);
		
		JLabel lblHouseNumber = new JLabel("House number:");
		lblHouseNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHouseNumber.setBounds(10, 204, 95, 13);
		contentPane.add(lblHouseNumber);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCity.setBounds(10, 227, 95, 13);
		contentPane.add(lblCity);
		
		JLabel lblIsInstructor = new JLabel("Postal code:");
		lblIsInstructor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIsInstructor.setBounds(10, 250, 95, 13);
		contentPane.add(lblIsInstructor);
		
		JLabel lblNewLabel_1 = new JLabel("Information Skier");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(44, 31, 162, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNameD = new JLabel("New label");
		lblNameD.setBounds(138, 67, 150, 13);
		contentPane.add(lblNameD);
		
		JLabel lblFirstnameD = new JLabel("New label");
		lblFirstnameD.setBounds(138, 90, 150, 13);
		contentPane.add(lblFirstnameD);
		
		JLabel lblEmailD = new JLabel("New label");
		lblEmailD.setBounds(138, 113, 150, 13);
		contentPane.add(lblEmailD);
		
		JLabel lblPhoneD = new JLabel("New label");
		lblPhoneD.setBounds(138, 136, 150, 13);
		contentPane.add(lblPhoneD);
		
		JLabel lblDobD = new JLabel("New label");
		lblDobD.setBounds(138, 159, 150, 13);
		contentPane.add(lblDobD);
		
		JLabel lblStreetD = new JLabel("New label");
		lblStreetD.setBounds(138, 182, 150, 13);
		contentPane.add(lblStreetD);
		
		JLabel lblHouseD = new JLabel("New label");
		lblHouseD.setBounds(138, 205, 150, 13);
		contentPane.add(lblHouseD);
		
		JLabel lblCityD = new JLabel("New label");
		lblCityD.setBounds(138, 228, 150, 13);
		contentPane.add(lblCityD);
		
		JLabel lblPostalCodeD = new JLabel("New label");
		lblPostalCodeD.setBounds(138, 251, 150, 13);
		contentPane.add(lblPostalCodeD);
		
		lblNameD.setText(s.getName());
		lblFirstnameD.setText(s.getFirstname());
		lblEmailD.setText(s.getEmail());
		lblPhoneD.setText(s.getPhone());
		lblDobD.setText(new SimpleDateFormat("dd/MM/yyyy").format(s.getDob()));
		lblStreetD.setText(s.getStreet());
		lblHouseD.setText(s.getHouseNbr());
		lblCityD.setText(s.getCity());
		lblPostalCodeD.setText(s.getPostalCode());
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 273, 278, 45);
		contentPane.add(btnNewButton);
	}

}
