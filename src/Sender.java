/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author infosba
 */

import java.math.BigInteger;
import java.security.SecureRandom;

public class Sender {
    private BigInteger publicKey;
    private BigInteger modulus;

    public Sender(BigInteger publicKey, BigInteger modulus) {
        this.publicKey = publicKey;
        this.modulus = modulus;
    }

    // Encrypt a message using the receiver's public key
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Sender <publicKey> <modulus>");
            return;
        }

        String publicKeyStr = args[0];
        String modulusStr = args[1];

        BigInteger publicKey = new BigInteger(publicKeyStr);
        BigInteger modulus = new BigInteger(modulusStr);

        Sender sender = new Sender(publicKey, modulus);
        BigInteger plaintext = new BigInteger("12345678901234567890");
        System.out.println("Plaintext: " + plaintext);

        // Encryption
        BigInteger ciphertext = sender.encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);
    }
}
