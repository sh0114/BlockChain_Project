package ajou.embedded;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionException;
//import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.eclipse.californium.core.*;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class GETClient {

  public static void main(String args[]) throws TransactionException, Exception {
    URI uri = null;

    if (args.length > 0) {

      // input URI from command line arguments
      try {
        uri = new URI(args[0]);
      } catch (URISyntaxException e) {
        System.err.println("Invalid URI: " + e.getMessage());
        System.exit(-1);
      }

      CoapClient client = new CoapClient(uri);
      CoapResponse response;

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

      System.out.println(integrity.isValid());

      Scanner scan = new Scanner(System.in);

      while (true) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("수행할 명령어를 입력하세요\n0. EXIT\n1. 펌웨어 업데이트\n2. 펌웨어 무결성검사\n3. 현재 펌웨어 버전 확인");
        int input = scan.nextInt();
        scan.nextLine();
        // RESPONSE res = new RESPONSE();
        if (input == 0) {
          System.out.println("바이바이\n");
          System.in.read();
          return;
        } else if (input == 1) {
          // BigInteger ver = integrity.checkVersion().send();
          // System.out.println("펌웨어 버전 입력(현재 " + ver + ")>");
          // String verIn = scan.nextLine();
          // ver = new BigInteger(verIn);

          System.out.print("펌웨어 대체 string 입력> ");
          int pay = scan.nextInt();
          scan.nextLine();
          String paystr = String.valueOf(pay);
          paystr = "1" + paystr;
          response = client.put(paystr, MediaTypeRegistry.TEXT_PLAIN);
          // res.setResponse(response.getResponseText());

          // 유사해쉬
          int serverHash = Encrypt(pay);
          String serverHashstr = String.valueOf(serverHash);
          System.out.println(serverHash);
          System.out.println(serverHashstr);

          BigInteger b = new BigInteger("1");
          integrity.addFirmware(b,serverHashstr).send();

          if (response != null) {
            System.out.println(Utils.prettyPrint(response));
            String ret = integrity.checkFirmware(response.getResponseText()).send();
            System.out.println(ret);

          } else {
            System.out.println("No response received.");
          }

        } else if (input == 2) {
          response = client.put("2", MediaTypeRegistry.TEXT_PLAIN);
          if (response != null) {
            String ret = integrity.checkFirmware(response.getResponseText()).send();
            System.out.println(ret);

          } else {
            System.out.println("No response received.");
          }
        } else {
          System.out.println("바르지 않은 입력입니다.\n");
        }
      }

    } else {
      // display help
      System.out.println("Californium (Cf) GET Client");
      System.out.println("(c) 2014, Institute for Pervasive Computing, ETH Zurich");
      System.out.println();
      System.out.println("Usage : " + GETClient.class.getSimpleName() + " URI [file]");
      System.out.println("  URI : The CoAP URI of the remote resource to GET");
      System.out.println("  file: optional filename to save the received payload");
    }
  }

  private static int Encrypt(int data) {
    int key = 0xA38C;
    int crptograph = 0;
    crptograph =  (data << key);
    return crptograph;
  }

}