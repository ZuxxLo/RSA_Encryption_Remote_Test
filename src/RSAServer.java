import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class RSAServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Handle client communication in a separate thread
                new Thread(new RSAServerThread(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RSAServerThread implements Runnable {
    private Socket clientSocket;

    public RSAServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            // Generate RSA key pair
            RSAEncryption rsa = new RSAEncryption();
            rsa.generateKeyPair();

            // Send public key to client
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(rsa.publicKey);
            outputStream.writeObject(rsa.modulus);

            // Receive encrypted message from client
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            BigInteger encryptedMessage = (BigInteger) inputStream.readObject();

            // Decrypt and print the message
            BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
            System.out.println("encryptedMessage " + encryptedMessage);

            System.out.println("Decrypted Message: " + decryptedMessage);

            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
