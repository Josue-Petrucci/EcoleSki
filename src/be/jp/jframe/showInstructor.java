package be.jp.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.jp.pojo.Accreditation;
import be.jp.pojo.Instructor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class showInstructor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showInstructor frame = new showInstructor(null, null, -404);
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
	public showInstructor(ShowsAllInstructor saiFrame, Instructor instructor, int row) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Information Instructor");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 10, 566, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 45, 95, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNameD = new JLabel((String) null);
		lblNameD.setBounds(138, 46, 150, 13);
		contentPane.add(lblNameD);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstname.setBounds(10, 68, 95, 13);
		contentPane.add(lblFirstname);
		
		JLabel lblFirstnameD = new JLabel((String) null);
		lblFirstnameD.setBounds(138, 69, 150, 13);
		contentPane.add(lblFirstnameD);
		
		JLabel lblEmailD = new JLabel((String) null);
		lblEmailD.setBounds(138, 92, 150, 13);
		contentPane.add(lblEmailD);
		
		JLabel lblPhoneD = new JLabel((String) null);
		lblPhoneD.setBounds(138, 115, 150, 13);
		contentPane.add(lblPhoneD);
		
		JLabel lblDobD = new JLabel((String) null);
		lblDobD.setBounds(138, 138, 150, 13);
		contentPane.add(lblDobD);
		
		JLabel lblStreetD = new JLabel((String) null);
		lblStreetD.setBounds(138, 161, 150, 13);
		contentPane.add(lblStreetD);
		
		JLabel lblHouseD = new JLabel((String) null);
		lblHouseD.setBounds(138, 184, 150, 13);
		contentPane.add(lblHouseD);
		
		JLabel lblCityD = new JLabel((String) null);
		lblCityD.setBounds(138, 207, 150, 13);
		contentPane.add(lblCityD);
		
		JLabel lblPostalCodeD = new JLabel((String) null);
		lblPostalCodeD.setBounds(138, 230, 150, 13);
		contentPane.add(lblPostalCodeD);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 91, 95, 13);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhone.setBounds(10, 114, 95, 13);
		contentPane.add(lblPhone);
		
		JLabel lblDateOfBirht = new JLabel("Date of birht:");
		lblDateOfBirht.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDateOfBirht.setBounds(10, 137, 95, 13);
		contentPane.add(lblDateOfBirht);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStreet.setBounds(10, 160, 95, 13);
		contentPane.add(lblStreet);
		
		JLabel lblHouseNumber = new JLabel("House number:");
		lblHouseNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHouseNumber.setBounds(10, 183, 95, 13);
		contentPane.add(lblHouseNumber);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCity.setBounds(10, 206, 95, 13);
		contentPane.add(lblCity);
		
		JLabel lblIsInstructor = new JLabel("Postal code:");
		lblIsInstructor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIsInstructor.setBounds(10, 229, 95, 13);
		contentPane.add(lblIsInstructor);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 362, 278, 45);
		contentPane.add(btnNewButton);
		
		lblNameD.setText(instructor.getName());
		lblFirstnameD.setText(instructor.getFirstname());
		lblEmailD.setText(instructor.getEmail());
		lblPhoneD.setText(instructor.getPhone());
		lblDobD.setText(new SimpleDateFormat("dd/MM/yyyy").format(instructor.getDob()));
		lblStreetD.setText(instructor.getStreet());
		lblHouseD.setText(instructor.getHouseNbr());
		lblCityD.setText(instructor.getCity());
		lblPostalCodeD.setText(instructor.getPostalCode());
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Accreditation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(298, 48, 278, 224);
		contentPane.add(panel);
		panel.setLayout(null);
		
		int i = 15;
		for(Accreditation a: Accreditation.getAllAcreditation()) {
			JLabel lblAccreditation = new JLabel(a.getName());
			lblAccreditation.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblAccreditation.setBounds(10, i, 220, 25);
			panel.add(lblAccreditation);
		
			if(instructor.isAccreditate(a)) {
				JLabel lblYes = new JLabel("YES");
				lblYes.setHorizontalAlignment(SwingConstants.CENTER);
				lblYes.setBackground(new Color(0, 128, 0));
				lblYes.setBounds(240, i, 30, 25);
				panel.add(lblYes);
			} else {
				JLabel lblNo = new JLabel("NO");
				lblNo.setHorizontalAlignment(SwingConstants.CENTER);
				lblNo.setBackground(new Color(255, 0, 0));
				lblNo.setBounds(240, i, 30, 25);
				panel.add(lblNo);
			}
			i+=25;
		}
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateInstructor updateInstructor = new UpdateInstructor(saiFrame, instructor, row);
				updateInstructor.setVisible(true);
				dispose();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(10, 273, 278, 45);
		contentPane.add(btnUpdate);
	}
}
