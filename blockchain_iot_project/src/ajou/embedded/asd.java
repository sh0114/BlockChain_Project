package ajou.embedded;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class asd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					asd frame = new asd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public asd() throws Exception {

		Web3j web3 = Web3j.build(new HttpService()); // defaults to
		// http://localhost:8545/
		Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
		String clientVersion = web3ClientVersion.getWeb3ClientVersion();
		// System.out.println(clientVersion);
		EthAccounts ethaccounts = web3.ethAccounts().sendAsync().get();
		// System.out.println(ethaccounts.getAccounts());

		// CoapResponse response = client.get();

		Credentials credentials = WalletUtils.loadCredentials("ACC1",
				"C:\\Users\\b8goal\\Desktop\\dir\\keystore\\UTC--2018-06-13T15-10-15.952089600Z--2a5b462a24103a0a7912eb72f785e864ba7fe259");

		BigInteger gasPrice = BigInteger.valueOf(0);
		BigInteger gasLimit = BigInteger.valueOf(3000000);
		BigInteger initialWeiValue = BigInteger.valueOf(0);

		Integrity integrity = Integrity.deploy(web3, credentials, gasPrice, gasLimit).send();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 900);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Firmware Integrity");
		lblNewLabel.setForeground(new Color(0, 191, 255));
		lblNewLabel.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 50));
		lblNewLabel.setBounds(211, 29, 446, 82);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 20));
		textField.setBounds(288, 151, 552, 39);
		contentPane.add(textField);
		textField.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.BOLD, 16));
		textArea.setEditable(false);
		textArea.setBounds(57, 236, 773, 185);
		contentPane.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Arial", Font.BOLD, 25));
		textArea_1.setEditable(false);
		textArea_1.setBounds(57, 482, 773, 72);
		contentPane.add(textArea_1);

		textField_1 = new JTextField();
		textField_1.setBounds(473, 597, 88, 39);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(473, 640, 88, 39);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("펌웨어 업데이트");
		btnNewButton.setBackground(new Color(255, 160, 122));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URI uri = null;

				try {
					uri = new URI(textField.getText());
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					uri = new URI(textField.getText());
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				CoapClient client = new CoapClient(uri);
				CoapResponse response;

				if (uri != null) {
					String s = null;
					s = textField_2.getText();

					String ver = null;
					ver = textField_1.getText();
					BigInteger b = new BigInteger(ver.getBytes());
					try {
						integrity.addFirmware(b, s).send();
						textArea_1.setText("Firmware update");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					s = "1" + s;
					try {
						response = client.put(s, MediaTypeRegistry.TEXT_PLAIN);
						if (response != null) {

							textArea.setText(Utils.prettyPrint(response));

						} else {
							textArea.setText("No response received.");
						}
					} catch (ConnectorException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}

		});
		btnNewButton.setBounds(569, 597, 271, 82);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("펌웨어 무결성검사");
		btnNewButton_1.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 30));
		btnNewButton_1.setBackground(new Color(255, 250, 240));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URI uri = null;

				try {
					uri = new URI(textField.getText());
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					uri = new URI(textField.getText());
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				CoapClient client = new CoapClient(uri);
				CoapResponse response;

				BigInteger a = new BigInteger("2");

				try {
					integrity.addFirmware(a, "0FFE").send();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				if (uri != null) {
					String str = null;
					str = textField_2.getText();

					String ver = null;
					ver = textField_1.getText();
					BigInteger b1 = new BigInteger("1");
					BigInteger b2 = new BigInteger("2");
					BigInteger b3 = new BigInteger("3");
					CoapResponse response1;
					try {
						String temp = "0FFE";
						integrity.addFirmware(b1, temp).send();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					str = "1" + str;
					try {
						response = client.put("2", MediaTypeRegistry.TEXT_PLAIN);
						if (response != null) {

							textArea.setText(Utils.prettyPrint(response));

							try {
								integrity.addFirmware(b1, "0FFE").send();

								String ret = integrity.checkFirmware("0FFE").send();
								textArea_1.setText(ret);

								if (ret.equals("your firmware is unsafe")) {
									JOptionPane.showMessageDialog(null, "안전하지않은 펌웨어. 연결을 종료합니다.", "warning",
											JOptionPane.PLAIN_MESSAGE);
									client.shutdown();
									response1 = client.put("1", MediaTypeRegistry.TEXT_PLAIN);
									if (response1 == null) {
										textArea.setText("This connection is disconnect");
										JOptionPane.showMessageDialog(null, "This connection is disconnect", "warning",
												JOptionPane.PLAIN_MESSAGE);
									}
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} else {
							textArea.setText("No response received.");
						}
					} catch (ConnectorException | IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}

			}

		});
		btnNewButton_1.setBounds(48, 715, 281, 90);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("펌웨어 버전 체크");
		btnNewButton_2.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 30));
		btnNewButton_2.setBackground(new Color(255, 250, 240));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = null;
				s = textField_2.getText();

				String ver = null;
				ver = textField_1.getText();
				// BigInteger b = new BigInteger(ver.getBytes());
				try {
					BigInteger b2 = new BigInteger("2");
					// integrity.addFirmware(b, s).send();
					// textArea_1.setText("Firmware update");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				BigInteger str = null;
				try {
					BigInteger b2 = new BigInteger("2");
					integrity.addFirmware(b2, "0456").send();
					str = integrity.checkVersion().send();
					String a = new String(str.toString());
					textArea_1.setText(a);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnNewButton_2.setBounds(343, 715, 282, 90);
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel_1 = new JLabel("MOTE address");
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(48, 137, 239, 61);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP,
						null, new Color(0, 0, 0)),
				"coap message", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225)));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(47, 210, 793, 220);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "smart contract message", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(47, 458, 793, 107);
		contentPane.add(panel_1);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setFont(new Font("Arial", Font.PLAIN, 24));
		textArea_2.setBounds(48, 610, 271, 60);
		contentPane.add(textArea_2);
		textArea_2.setText("Integrity contract is " + integrity.isValid());

		JButton btnNewButton_3 = new JButton("Information");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Developer\n201320876 배현성\n201620945 박준혁\n201620986 채성희\n☆★☆",
						"information", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnNewButton_3.setBackground(new Color(255, 250, 240));
		btnNewButton_3.setFont(new Font("Arial", Font.BOLD, 30));
		btnNewButton_3.setBounds(639, 715, 201, 90);
		contentPane.add(btnNewButton_3);

		JLabel label = new JLabel("펌웨어 버전");
		label.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 25));
		label.setBounds(343, 597, 116, 39);
		contentPane.add(label);

		JLabel lblNewLabel_2 = new JLabel("펌웨어 정보");
		lblNewLabel_2.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(343, 640, 116, 39);
		contentPane.add(lblNewLabel_2);
	}
}
