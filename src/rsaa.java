/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author infosba
 */


import java.math.BigInteger;
import java.security.SecureRandom;

public class rsaa {
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    public rsaa(int bitLength) {
        generateKeys(bitLength);
    }

    // Generate public and private keys
    private void generateKeys(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);
        modulus = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        publicKey = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(publicKey).compareTo(BigInteger.ONE) > 0 && publicKey.compareTo(phi) < 0) {
            publicKey = publicKey.add(BigInteger.ONE);
        }
        privateKey = publicKey.modInverse(phi);
    }

    // Encrypt a message using public key
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    // Decrypt a message using private key
    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        rsaa rsaa = new rsaa(1024);
        BigInteger plaintext = new BigInteger("1234567899999999");
        System.out.println("Plaintext: " + plaintext);

        // Encryption
        BigInteger ciphertext = rsaa.encrypt(plaintext);
        System.out.println("Encrypted: " + ciphertext);

        // Decryption
        BigInteger decryptedText = rsaa.decrypt(ciphertext);
        System.out.println("Decrypted: " + decryptedText);
    }
}
