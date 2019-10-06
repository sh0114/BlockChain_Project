package ajou.embedded;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;

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

import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setTitle("server");
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
	public GUI() throws Exception {

		Web3j web3 = Web3j.build(new HttpService()); // defaults to
		// http://localhost:8545/
		Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
		String clientVersion = web3ClientVersion.getWeb3ClientVersion();
		// System.out.println(clientVersion);
		EthAccounts ethaccounts = web3.ethAccounts().sendAsync().get();
		// System.out.println(ethaccounts.getAccounts());

		// CoapResponse response = client.get();

		Credentials credentials = WalletUtils.loadCredentials("ACC1",
				"C:\\Users\\b8goal\\Desktop\\dir\\keystore\\UTC--2018-06-12T13-22-22.090334900Z--fe6864d93aacaffad9d77497af17140cf8470895");

		BigInteger gasPrice = BigInteger.valueOf(0);
		BigInteger gasLimit = BigInteger.valueOf(3000000);
		BigInteger initialWeiValue = BigInteger.valueOf(0);

		Integrity integrity = Integrity.deploy(web3, credentials, gasPrice, gasLimit).send();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 1000, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFirmwareIntegrity = new JLabel("Firmware Integrity");
		lblFirmwareIntegrity.setForeground(new Color(100, 149, 237));
		lblFirmwareIntegrity.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 45));
		lblFirmwareIntegrity.setBounds(262, 25, 452, 75);
		contentPane.add(lblFirmwareIntegrity);

		textField_1 = new JTextField();
		textField_1.setBounds(374, 161, 563, 41);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(485, 723, 176, 29);
		contentPane.add(textField_2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP,
						null, new Color(0, 0, 0)),
				"coap message", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 191, 255)));
		panel.setBounds(31, 227, 906, 295);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP,
						null, new Color(0, 0, 0)),
				"smart contract", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 20, 147)));
		panel_1.setBounds(31, 534, 906, 164);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(14, 39, 878, 226);
		panel.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(14, 41, 875, 110);
		panel_1.add(textArea_1);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Microsoft Yi Baiti", Font.BOLD, 25));
		textArea_2.setTabSize(10);
		textArea_2.setEditable(false);
		textArea_2.setBounds(31, 723, 250, 75);
		contentPane.add(textArea_2);
		textArea_2.setText("integrity contract is " + integrity.isValid());

		textField = new JTextField();
		textField.setBounds(485, 767, 176, 29);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\uD38C\uC6E8\uC5B4 \uC5C5\uB370\uC774\uD2B8");
		btnNewButton.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 30));
		btnNewButton.setBackground(new Color(250, 128, 114));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URI uri = null;

				try {
					uri = new URI(textField_1.getText());
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					uri = new URI(textField_1.getText());
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				CoapClient client = new CoapClient(uri);
				CoapResponse response;

				if (uri != null) {
					String str = null;
					str = textField.getText();

					String ver = null;
					ver = textField_2.getText();
					BigInteger b = new BigInteger(ver.getBytes());

					try {
						integrity.addFirmware(b, str).send();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					str = "1" + str;
					try {
						response = client.put(str, MediaTypeRegistry.TEXT_PLAIN);
						if (response != null) {

							textArea.setText(Utils.prettyPrint(response));

							try {
								String ret = integrity.checkFirmware(response.getResponseText()).send();
								textArea_1.setText(ret);
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
		btnNewButton.setBounds(675, 723, 262, 75);
		contentPane.add(btnNewButton);

		JButton button = new JButton("\uD38C\uC6E8\uC5B4 \uBB34\uACB0\uC131 \uAC80\uC0AC");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URI uri = null;

				try {
					uri = new URI(textField_1.getText());
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					uri = new URI(textField_1.getText());
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				CoapClient client = new CoapClient(uri);
				CoapResponse response;

				if (uri != null) {

					try {
						response = client.put("2", MediaTypeRegistry.TEXT_PLAIN);
						if (response != null) {

							textArea.setText(Utils.prettyPrint(response));

							try {
								String ret = integrity.checkFirmware(response.getResponseText()).send();
								textArea_1.setText(ret);
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
		button.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 30));
		button.setBackground(new Color(250, 250, 210));
		button.setBounds(31, 812, 438, 75);
		contentPane.add(button);

		JButton button_1 = new JButton("\uD604\uC7AC \uD38C\uC6E8\uC5B4 \uBC84\uC804 \uD655\uC778");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger str = null;
				try {
					str = integrity.checkVersion().send();
					String a = new String(str.toByteArray());
					textArea_1.setText(a);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 30));
		button_1.setBackground(new Color(250, 250, 210));
		button_1.setBounds(485, 812, 452, 75);
		contentPane.add(button_1);

		JLabel lblMoteAddress = new JLabel("MOTE address");
		lblMoteAddress.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 45));
		lblMoteAddress.setBounds(31, 141, 323, 75);
		contentPane.add(lblMoteAddress);

		JLabel lblNewLabel = new JLabel("\uD38C\uC6E8\uC5B4 \uBC84\uC804");
		lblNewLabel.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 30));
		lblNewLabel.setBounds(312, 719, 137, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uD38C\uC6E8\uC5B4 \uC815\uBCF4");
		lblNewLabel_1.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(312, 763, 137, 35);
		contentPane.add(lblNewLabel_1);

	}
}