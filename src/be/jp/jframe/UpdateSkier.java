package be.jp.jframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import be.jp.pojo.Skier;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class UpdateSkier extends JFrame {

	private static final long serialVersionUID = 1L;
	private ShowsAllSkier frameAllSkier;
	private JPanel contentPane;
	private JTextField tfNameU;
	private JTextField tfFirstnameU;
	private JTextField tfEmailU;
	private JTextField tfPhoneU;
	private JTextField tfStreetU;
	private JTextField tfHouseU;
	private JTextField tfCityU;
	private JTextField tfPostalCodeU;
	private JDateChooser dcDobU;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateSkier frame = new UpdateSkier(null, null, -404);
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
	public UpdateSkier(ShowsAllSkier frameAllSkier, Skier s, int row) {
		this.frameAllSkier = frameAllSkier;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Edit a Skier");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 10, 251, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 51, 45, 13);
		contentPane.add(lblNewLabel);
		
		tfNameU = new JTextField();
		tfNameU.setText((String) null);
		tfNameU.setColumns(10);
		tfNameU.setBounds(135, 48, 120, 19);
		contentPane.add(tfNameU);
		
		tfFirstnameU = new JTextField();
		tfFirstnameU.setText((String) null);
		tfFirstnameU.setColumns(10);
		tfFirstnameU.setBounds(135, 77, 120, 19);
		contentPane.add(tfFirstnameU);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(10, 80, 65, 13);
		contentPane.add(lblFirstname);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 106, 65, 13);
		contentPane.add(lblEmail);
		
		tfEmailU = new JTextField();
		tfEmailU.setText((String) null);
		tfEmailU.setColumns(10);
		tfEmailU.setBounds(135, 103, 120, 19);
		contentPane.add(tfEmailU);
		
		tfPhoneU = new JTextField();
		tfPhoneU.setText((String) null);
		tfPhoneU.setColumns(10);
		tfPhoneU.setBounds(135, 129, 120, 19);
		contentPane.add(tfPhoneU);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(10, 132, 95, 13);
		contentPane.add(lblPhone);
		
		JLabel lblDateOfBirht = new JLabel("Date of birht:");
		lblDateOfBirht.setBounds(10, 158, 86, 13);
		contentPane.add(lblDateOfBirht);
		
		dcDobU = new JDateChooser();
		dcDobU.setBounds(135, 155, 120, 19);
		contentPane.add(dcDobU);
		
		tfStreetU = new JTextField();
		tfStreetU.setText((String) null);
		tfStreetU.setColumns(10);
		tfStreetU.setBounds(135, 184, 120, 19);
		contentPane.add(tfStreetU);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setBounds(10, 187, 45, 13);
		contentPane.add(lblStreet);
		
		JLabel lblHouseNumber = new JLabel("House number:");
		lblHouseNumber.setBounds(10, 213, 86, 13);
		contentPane.add(lblHouseNumber);
		
		tfHouseU = new JTextField();
		tfHouseU.setColumns(10);
		tfHouseU.setBounds(135, 210, 120, 19);
		contentPane.add(tfHouseU);
		
		tfCityU = new JTextField();
		tfCityU.setText((String) null);
		tfCityU.setColumns(10);
		tfCityU.setBounds(135, 236, 120, 19);
		contentPane.add(tfCityU);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(10, 239, 86, 13);
		contentPane.add(lblCity);
		
		JLabel lblIsInstructor = new JLabel("Postal code:");
		lblIsInstructor.setBounds(10, 265, 86, 13);
		contentPane.add(lblIsInstructor);
		
		tfPostalCodeU = new JTextField();
		tfPostalCodeU.setColumns(10);
		tfPostalCodeU.setBounds(135, 262, 120, 19);
		contentPane.add(tfPostalCodeU);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(s, row);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(156, 291, 85, 40);
		contentPane.add(btnUpdate);
		
		JButton btnCancele = new JButton("Cancel");
		btnCancele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancele.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancele.setBounds(20, 291, 85, 40);
		contentPane.add(btnCancele);
		
		tfNameU.setText(s.getName());
		tfFirstnameU.setText(s.getFirstname());
		tfEmailU.setText(s.getEmail());
		tfPhoneU.setText(s.getPhone());
		dcDobU.setDate(s.getDob());
		tfStreetU.setText(s.getStreet());
		tfHouseU.setText(s.getHouseNbr());
		tfCityU.setText(s.getCity());
		tfPostalCodeU.setText(s.getPostalCode());
	}
	
	private void update(Skier s, int row) {
		Skier updateSkier = new Skier(
				tfNameU.getText(),
				tfFirstnameU.getText(),
				tfEmailU.getText(),
				tfPhoneU.getText(),
				dcDobU.getDate(),
				tfStreetU.getText(),
				tfHouseU.getText(),
				tfCityU.getText(),
				tfPostalCodeU.getText());
		if(s.updateSkier(updateSkier)) {
			frameAllSkier.updateTableRow(s, row);
			dispose();
		}
	}

}
