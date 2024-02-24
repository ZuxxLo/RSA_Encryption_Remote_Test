/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author infosba
 */
import java.math.BigInteger;

public class Receiver {
    private BigInteger privateKey;
    private BigInteger modulus;

    public Receiver(BigInteger privateKey, BigInteger modulus) {
        this.privateKey = privateKey;
        this.modulus = modulus;
    }

    // Decrypt a message using the receiver's private key
    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java Receiver <senderPublicKey> <modulus> <privateKey> <ciphertext>");
            return;
        }

        String senderPublicKeyStr = args[0];
        String modulusStr = args[1];
        String privateKeyStr = args[2];
        String ciphertextStr = args[3];

        BigInteger senderPublicKey = new BigInteger(senderPublicKeyStr);
        BigInteger modulus = new BigInteger(modulusStr);
        BigInteger privateKey = new BigInteger(privateKeyStr);

        Receiver receiver = new Receiver(privateKey, modulus);

        BigInteger ciphertext = new BigInteger(ciphertextStr);

        // Decryption
        BigInteger decryptedText = receiver.decrypt(ciphertext);
        System.out.println("Decrypted: " + decryptedText);
    }
}
