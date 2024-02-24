The objective of this lab is to implement the RSA public key encryption in Java. This will be
done in three steps:
1. Implementation: write a Java program allowing you to (de)encrypt a number using the RSA
algorithm.
2. Local test: encrypt and decrypt a number on the same machine to verify that everything is
working properly.
3. Remote test (two machines): generate a private key/public key pair and transmit your public
key to a colleague. This will use it to encrypt a message (always a number...) that it will send to you.
Then decrypt this message and check with your colleague that the message obtained is correct.
Instructions for implementation
RSA works with very large numbers. It will therefore be appropriate to use the BigInteger class
from the java.math package to represent them.
Note that this class offers constants such as ONE or ZERO, as well as the arithmetic operations
add, subtract, etc.
Additionally, you might need the following methods of this same class:
- probablePrime, which allows you to generate a (large) number which has a high probability of
being prime.
- modInverse, which allows you to calculate the modular inverse of a number (i.e., given e and n,
find d such that ed = 1 (mod n)).
- modPow, which allows you to calculate the rise to modular power (a^b (mod n))
- gcd, which allows you to calculate the gdc of two numbers
- . . .
For more details about the BigInteger class and the functions it provides, refer to the official Java
API documentation: https://docs.oracle.com/en/java/javase/21/index.html .
During the implementation you will certainly be called upon to use a random number generator. For
security reasons you will need to use the generator java.security.SecureRandom and not
java.util.Random.
