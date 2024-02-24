import java.io.*;
import java.net.*;
import java.math.BigInteger;

public class RSAClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("INSERT HERE IP ADRESSE", 7777); //for local hosting just write "localhost"

            // Receive public key from server
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            BigInteger publicKey = (BigInteger) inputStream.readObject();
            BigInteger modulus = (BigInteger) inputStream.readObject();

            // Encrypt a message using the received public key
            RSAEncryption rsa = new RSAEncryption();
            rsa.publicKey = publicKey;
            rsa.modulus = modulus;

            BigInteger message = new BigInteger("67890");
            System.out.println("original " + message);
            BigInteger encryptedMessage = rsa.encrypt(message);
            System.out.println("encryptedMessage " + encryptedMessage);
            // Send the encrypted message to the server
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(encryptedMessage);

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
