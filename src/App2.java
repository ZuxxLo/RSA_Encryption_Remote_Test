import java.math.BigInteger;
import java.io.*;
import java.net.*;
import java.util.Scanner;



public class App2  {
    public static void main(String[] args) throws IOException {
        RSA rsa = new RSA(1024);



        try {
            System.out.println("Connection to the App class :");
            Socket client = new Socket ("localhost", 7777); // socket de connexion
            InputStream input = client.getInputStream ();
            OutputStream output = client.getOutputStream ();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);
            // Scanner scan = new Scanner(System.in);
            // String message = scan.nextLine();
            writer.println(rsa.publicKey);
            writer.println(rsa.modulus);
            String response = reader.readLine();
            BigInteger publicKey2 = new BigInteger(response);
            response = reader.readLine();
            BigInteger modulus2 = new BigInteger(response);
           
            BigInteger message = new BigInteger("999999999999");
            System.out.println("the message is: " + message);

            BigInteger encryptedMessage = rsa.encrypt2(message, publicKey2, modulus2);
            System.out.println("the encrypted message is: " + encryptedMessage);
            writer.println(encryptedMessage);
            
            String cipherText = reader.readLine();
            BigInteger cipher = new BigInteger(cipherText);
            BigInteger decryptedMessage = rsa.decrypt(cipher);
            System.out.println("the decrypted message is: " + decryptedMessage);


            client.close();
            } catch (UnknownHostException e) {}
            catch (IOException e) {
                System.err.println("the error is : " + e);
            }
        
    }
    
}
