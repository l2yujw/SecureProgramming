import java.math.BigInteger;
import java.util.Random;

public class RSATest {
    public static void main(String[] args) {
        RSA rsa = new RSA(BigInteger.valueOf(11), BigInteger.valueOf(13));
        System.out.println(rsa.toString());

        BigInteger encryptionData = rsa.Encryption(BigInteger.valueOf(6));
        BigInteger decryptionData = rsa.Decryption(encryptionData);

        System.out.println("암호화된 Data: " + encryptionData);
        System.out.println("복호화된 Data: " + decryptionData);

//        RSA rsa2 = new RSA();
//        System.out.println(rsa2.toString());

        System.out.println();
        modularTest01(3, 7);
        modularTest02();
        System.out.println();
        modularTest03();
        System.out.println();
        modularTest04();
    }

    public static void modularTest01(int A, int C) {
        for (int B = 0; B <= C - 1; B++) {
            System.out.println("B: " + B);
            System.out.println("A X B mod C: " + (A * B % C));
            int modResult = (A * B % C);
            System.out.printf("%d x %d mod %d = %d\n", A, B, C, modResult);
            if (modResult == 1) {
                System.err.println("B: " + B);
            }
        }
    }

    public static void modularTest02() {
        BigInteger A = BigInteger.probablePrime(1024, new Random());
        BigInteger C = BigInteger.probablePrime(1024, new Random());
        System.out.println();
        System.out.println("A: " + A);
        System.out.println("C: " + C);
        System.out.println("B: " + A.modInverse(C));
    }

    public static void modularTest03() {
        BigInteger A = BigInteger.valueOf(3);
        BigInteger C = BigInteger.valueOf(7);
        System.out.println("A: " + A);
        System.out.println("C: " + C);
        System.out.println("B: " + A.modInverse(C));
    }

    public static void modularTest04() {
        BigInteger A = BigInteger.valueOf(2);
        BigInteger C = BigInteger.valueOf(6);
        System.out.println("A: " + A);
        System.out.println("C: " + C);

        BigInteger gcd = A.gcd(C);
        if (gcd.equals(BigInteger.ONE)) {
            System.out.println("B: " + A.modInverse(C));
        } else {
            System.out.println("서로소가 아닙니다");
        }
    }
}
