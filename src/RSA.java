import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger privateKey; // d
    public BigInteger publicKey; // (n, e)
    public BigInteger modulus; // the n number

    // Constructor to generate public and private keys
    public RSA(int bitLength) {
        // Generate two distinct large prime numbers p and q
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);

        // Compute modulus n = p * q
        modulus = p.multiply(q);

        // Compute Euler's totient function phi(n) = (p-1) * (q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Choose public exponent e such that 1 < e < phi(n) and gcd(e, phi(n)) = 1
        publicKey = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(publicKey).compareTo(BigInteger.ONE) > 0 && publicKey.compareTo(phi) < 0) {
            publicKey = publicKey.add(BigInteger.ONE);
        }

        // Compute private exponent d such that (d * e) % phi(n) = 1
        privateKey = publicKey.modInverse(phi);
    }


    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }
    public BigInteger encrypt2(BigInteger message, BigInteger key, BigInteger mod) {
        return message.modPow(key, mod);
    }


    // Decrypt a cipher text using private key
    public BigInteger decrypt(BigInteger cipherText) {
        return cipherText.modPow(privateKey, modulus);
    }
 

    
}
