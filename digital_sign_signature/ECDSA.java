import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static java.lang.Math.max;

public class ECDSA {

    private ECPoint G;
    private BigInteger n;
    private int nOfBit;

    public ECDSA(ECPoint g, BigInteger n) {
        G = g;
        this.n = n;
        this.nOfBit = n.bitLength();
    }

    public BigInteger modInverse(BigInteger value) {
        // mod n에 대한 나머지 역원을 구한다.
        return value.modInverse(n);
    }

    public String hash(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public Point sign(BigInteger m, BigInteger d) {
        int mBit = m.bitLength();
        BigInteger z = m.shiftRight(max(mBit - nOfBit, 0));
        System.out.println("mBit: " + mBit);
        System.out.println("Shift Right: " + max(mBit - nOfBit, 0));
        BigInteger k, s, r;
        ECPoint P;
        do {
            do {
                k = BigInteger.probablePrime(32, new Random());
                P = G.multiplyOperation(k);
                r = P.getX().mod(n);
            } while (r.equals(BigInteger.ZERO));
            s = modInverse(k).multiply(r.multiply(d).add(z));
            s = s.mod(n);
        } while (s.equals(BigInteger.ZERO));
        return new Point(r, s);
    }

    public boolean verify(BigInteger m, Point sig, ECPoint Q) {
        BigInteger r = sig.getX();
        BigInteger s = sig.getY();
        if (r.compareTo(BigInteger.ONE) < 0 || r.compareTo(n) >= 0
            || s.compareTo(BigInteger.ONE) < 0 || s.compareTo(n) >= 0) {
            return false;
        }
        int mBit = m.bitLength();
        BigInteger z = m.shiftRight(max(mBit - nOfBit, 0));
        BigInteger u1 = m.multiply(modInverse(s)).mod(n);
        BigInteger u2 = r.multiply(modInverse(s)).mod(n);
        ECPoint P = G.multiplyOperation(u1).addOperation(Q.multiplyOperation(u2));
        if (P.getY().equals(G.getField().getP())) {
            System.out.println("P is O");
            return false;
        }
        return P.getX().subtract(r).mod(n).equals(BigInteger.ZERO);
    }

    @Override
    public String toString() {
        return "ECDSA{" +
            "G=" + G +
            ", n=" + n +
            ", nOfBit=" + nOfBit +
            '}';
    }

    public int mul(int num1, int num2){
        return num1*num2;
    }
}
