import java.math.BigInteger;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        System.out.println("[Basic Test]");
        sampleTest();
        System.out.println("[Secp256k1 Test]");
        Secp256k1Test();
        System.out.println("[secp256r1 Test]");
        Secp256r1Test();
    }

    public static void sampleTest() {
        long beforeTime = System.currentTimeMillis();
        EllipticCurve field = new EllipticCurve(2, 2, 17);
        ECPoint G = new ECPoint(5, 1, field);

        BigInteger privateA = BigInteger.valueOf(117);
        BigInteger privateB = BigInteger.valueOf(7);

        System.out.println("[Alice Private Key]");
        System.out.println(privateA);
        System.out.println("[Bob Private Key]");
        System.out.println(privateB);
        System.out.println();

        ECPoint xA = G.multiplyOperation(privateA);
        ECPoint xB = G.multiplyOperation(privateB);

        System.out.println("[Alice Public Key]");
        System.out.println(xA);
        System.out.println("[Bob Public Key]");
        System.out.println(xB);
        System.out.println();

        ECPoint keyA = xB.multiplyOperation(privateA);
        ECPoint keyB = xA.multiplyOperation(privateB);

        System.out.println("[Alice Key]");
        System.out.println(keyA);
        System.out.println("[Bob Key]");
        System.out.println(keyB);
        System.out.println();

        long afterTime = System.currentTimeMillis();
        System.out.println("Operating Time: " + (afterTime - beforeTime) + "ms");
        System.out.println();
    }

    public static void Secp256k1Test() {
        long beforeTime = System.currentTimeMillis();
        String pStr = "FFFFFFFF FFFFFFFF FFFFFFFF FFFFFFFF FFFFFFFF FFFFFFFF FFFFFFFE FFFFFC2F".replaceAll(
            " ", "");
        BigInteger p = new BigInteger(pStr, 16);
        String aStr = "00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000".replaceAll(
            " ", "");
        BigInteger a = new BigInteger(aStr, 16);
        String bStr = "00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000007".replaceAll(
            " ", "");
        BigInteger b = new BigInteger(bStr, 16);
        String gXStr = "79BE667E F9DCBBAC 55A06295 CE870B07 029BFCDB 2DCE28D9 59F2815B 16F81798".replaceAll(
            " ", "");
        BigInteger gX = new BigInteger(gXStr, 16);
        String gYStr = "483ADA77 26A3C465 5DA4FBFC 0E1108A8 FD17B448 A6855419 9C47D08F FB10D4B8".replaceAll(
            " ", "");
        BigInteger gY = new BigInteger(gYStr, 16);

        EllipticCurve field = new EllipticCurve(a, b, p);
        ECPoint G = new ECPoint(gX, gY, field);

        BigInteger privateA = BigInteger.probablePrime(256, new Random());
        BigInteger privateB = BigInteger.probablePrime(256, new Random());

        System.out.println("[Alice Private Key]");
        System.out.println(privateA);
        System.out.println("[Bob Private Key]");
        System.out.println(privateB);
        System.out.println();

        ECPoint xA = G.multiplyOperation(privateA);
        ECPoint xB = G.multiplyOperation(privateB);

        System.out.println("[Alice Public Key]");
        System.out.println(xA);
        System.out.println("[Bob Public Key]");
        System.out.println(xB);
        System.out.println();

        ECPoint keyA = xB.multiplyOperation(privateA);
        ECPoint keyB = xA.multiplyOperation(privateB);

        System.out.println("[Alice Key]");
        System.out.println(keyA);
        System.out.println("[Bob Key]");
        System.out.println(keyB);
        System.out.println();

        long afterTime = System.currentTimeMillis();
        System.out.println("Total Operating Time: " + (afterTime - beforeTime) + "ms");
        System.out.println();
    }

    public static void Secp256r1Test() {
        long beforeTime = System.currentTimeMillis();
        String pStr = "FFFFFFFF 00000001 00000000 00000000 00000000 FFFFFFFF FFFFFFFF FFFFFFFF".replaceAll(
            " ", "");
        BigInteger p = new BigInteger(pStr, 16);
        String aStr = "FFFFFFFF 00000001 00000000 00000000 00000000 FFFFFFFF FFFFFFFF FFFFFFFC".replaceAll(
            " ", "");
        BigInteger a = new BigInteger(aStr, 16);
        String bStr = "5AC635D8 AA3A93E7 B3EBBD55 769886BC 651D06B0 CC53B0F6 3BCE3C3E 27D2604B".replaceAll(
            " ", "");
        BigInteger b = new BigInteger(bStr, 16);
        String gXStr = "6B17D1F2 E12C4247 F8BCE6E5 63A440F2 77037D81 2DEB33A0 F4A13945 D898C296".replaceAll(
            " ", "");
        BigInteger gX = new BigInteger(gXStr, 16);
        String gYStr = "4FE342E2 FE1A7F9B 8EE7EB4A 7C0F9E16 2BCE3357 6B315ECE CBB64068 37BF51F5".replaceAll(
            " ", "");
        BigInteger gY = new BigInteger(gYStr, 16);

        EllipticCurve field = new EllipticCurve(a, b, p);
        ECPoint G = new ECPoint(gX, gY, field);

        BigInteger privateA = BigInteger.probablePrime(256, new Random());
        BigInteger privateB = BigInteger.probablePrime(256, new Random());

        System.out.println("[Alice Private Key]");
        System.out.println(privateA);
        System.out.println("[Bob Private Key]");
        System.out.println(privateB);
        System.out.println();

        ECPoint xA = G.multiplyOperation(privateA);
        ECPoint xB = G.multiplyOperation(privateB);

        System.out.println("[Alice Public Key]");
        System.out.println(xA);
        System.out.println("[Bob Public Key]");
        System.out.println(xB);
        System.out.println();

        ECPoint keyA = xB.multiplyOperation(privateA);
        ECPoint keyB = xA.multiplyOperation(privateB);

        System.out.println("[Alice Key]");
        System.out.println(keyA);
        System.out.println("[Bob Key]");
        System.out.println(keyB);
        System.out.println();

        long afterTime = System.currentTimeMillis();
        System.out.println("Total Operating Time: " + (afterTime - beforeTime) + "ms");
        System.out.println();
    }
}
