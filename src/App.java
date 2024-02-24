import java.math.BigInteger;
import java.io.*;
import java.net.*;
import java.util.Scanner;
   // // Message to encrypt
                                    // BigInteger message = new BigInteger("999999999999");

                                    // // Encrypt the message
                                    // BigInteger encryptedMessage = rsa.encrypt(message);
                                    // System.out.println("Encrypted message: " + encryptedMessage);

                                    // // Decrypt the encrypted message
                                    // BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
                                    // System.out.println("Decrypted message: " + decryptedMessage);
public class App {
    public static void main(String[] args) throws IOException {
        RSA rsa = new RSA(1024);
                                 
                               

        try {
            ServerSocket server = new ServerSocket(7777); // 
            System.out.println("Created a connecttion on App class");
            Socket sock = server.accept();
            InputStream input = sock.getInputStream ();
            OutputStream output = sock.getOutputStream ();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true); 


            String messageText = reader.readLine();
            BigInteger publicKey2 = new BigInteger(messageText);
            String modulus = reader.readLine();
            BigInteger mod = new BigInteger(modulus);
            System.out.println("Recieved the App2 public key");
            writer.println(rsa.publicKey);
            writer.println(rsa.modulus);
            String cipherText = reader.readLine();
            BigInteger cipher = new BigInteger(cipherText);
            System.out.println("the cipher is " + cipherText);
            BigInteger decryptedMessage = rsa.decrypt(cipher);
            System.out.println("the decrypted message is: " + decryptedMessage);
            BigInteger message = new BigInteger("8888888888888");
            writer.println(rsa.encrypt2(message, publicKey2, mod));
            





            sock.close();
            } catch (IOException e) {
                System.err.println ("Erreur d'echanger les messages : " + e);
            }
    
    }
}
