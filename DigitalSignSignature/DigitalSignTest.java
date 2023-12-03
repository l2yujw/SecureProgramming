import java.math.BigInteger;
import java.util.Random;

public class DigitalSignTest {

    private static final String a = "FFFFFFFF 00000001 00000000 00000000 00000000 FFFFFFFF FFFFFFFF FFFFFFFC".replace(
        " ", "");
    private static final String b = "5AC635D8 AA3A93E7 B3EBBD55 769886BC 651D06B0 CC53B0F6 3BCE3C3E 27D2604B".replace(
        " ", "");
    private static final String p = "FFFFFFFF 00000001 00000000 00000000 00000000 FFFFFFFF FFFFFFFF FFFFFFFF".replace(
        " ", "");
    private static final String gX = "6B17D1F2 E12C4247 F8BCE6E5 63A440F2 77037D81 2DEB33A0 F4A13945 D898C296".replace(
        " ", "");
    private static final String gY = "4FE342E2 FE1A7F9B 8EE7EB4A 7C0F9E16 2BCE3357 6B315ECE CBB64068 37BF51F5".replace(
        " ", "");
    private static final String n = "FFFFFFFF 00000000 FFFFFFFF FFFFFFFF BCE6FAAD A7179E84 F3B9CAC2 FC632551".replace(
        " ", "");

    public static void main(String[] args) {
        System.out.println("[Step01] Elliptic Curve를 선택합니다.");
        EllipticCurve ellipticCurve = new EllipticCurve(new BigInteger(a, 16), new BigInteger(b, 16), new BigInteger(p, 16));
        System.out.println(ellipticCurve);
        System.out.println("===================================================================================================================================================================================================================================================================");

        System.out.println("[Step02] 임의의 자연수 d를 비밀키로 선택합니다.");
        BigInteger d = BigInteger.probablePrime(32, new Random());
        System.out.println("[Private Key] d: " + d);
        System.out.println("===================================================================================================================================================================================================================================================================");

        System.out.println("[Step03] 비밀키 d와 Generator Point (G)를 곱해 공개키 Q를 생성합니다.");
        ECPoint G = new ECPoint(new BigInteger(gX, 16), new BigInteger(gY, 16), ellipticCurve);
        ECPoint Q = G.multiplyOperation(d);
        System.out.println("[Generator Point] G: " + G);
        System.out.println("[Public Key] Q = dG = " + Q);
        System.out.println("===================================================================================================================================================================================================================================================================");

        System.out.println("ECDSA");
        ECDSA ecdsa = new ECDSA(G, new BigInteger(n, 16));
        System.out.println(ecdsa);
        System.out.println("===================================================================================================================================================================================================================================================================");

        System.out.println("[Step07] Hash를 수행한 메시지를 z라고 합니다.");
        String message = "Hello";
        String z = ecdsa.hash(message);
        System.out.println("[Message] m: " + message);
        System.out.println("[Hash Message] z: " + z);
        System.out.println("===================================================================================================================================================================================================================================================================");

        System.out.println("[Step07] 서명을 수행합니다.");
        Point sign = ecdsa.sign(new BigInteger(z, 16), d);
        System.out.println("[Signature] " + sign);
        System.out.println("r: " + sign.getX());
        System.out.println("s: " + sign.getY());
        System.out.println("===================================================================================================================================================================================================================================================================");

        boolean verify = ecdsa.verify(new BigInteger(z, 16), sign, Q);
        System.out.println("[Verify]");
        System.out.println(verify);
    }
}
