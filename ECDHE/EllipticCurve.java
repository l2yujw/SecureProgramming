import java.math.BigInteger;

public class EllipticCurve {

    private BigInteger a;
    private BigInteger b;
    private BigInteger p;

    public EllipticCurve(BigInteger a, BigInteger b, BigInteger p) {
        this.a = a;
        this.b = b;
        this.p = p;
    }

    public EllipticCurve(int a, int b, int p) {
        this.a = BigInteger.valueOf(a);
        this.b = BigInteger.valueOf(b);
        this.p = BigInteger.valueOf(p);
    }

    public BigInteger modInverse(BigInteger value) {
        return value.modInverse(p);
    }

    public BigInteger getA() {
        return a;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    public BigInteger getB() {
        return b;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "EllipticCurve{" +
            "a=" + a +
            ", b=" + b +
            ", p=" + p +
            '}';
    }
}
