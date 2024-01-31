import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ECPoint {

    private BigInteger x;
    private BigInteger y;
    private EllipticCurve field;

    public ECPoint(BigInteger x, BigInteger y, EllipticCurve field) {
        this.x = x;
        this.y = y;
        this.field = field;
    }

    public ECPoint(int x, int y, EllipticCurve field) {
        this.x = BigInteger.valueOf(x);
        this.y = BigInteger.valueOf(y);
        this.field = field;
    }

    public boolean allPointSame(ECPoint r) {
        return getX().equals(r.getX()) && getY().equals(r.getY());
    }

    public ECPoint addOperation(ECPoint r) {
        if (r.getY().equals(field.getP())) {
            // System.out.println("P + O = P\n");
            return this;
        }
        if (this.getY().equals(field.getP())) {
            // System.out.println("O + P = P\n");
            return r;
        }
        BigInteger S;
        if (allPointSame(r)) {
            // [Case] P = Q
            if (this.getY().equals(BigInteger.ZERO)) {
                // [Wrong] return new ECPoint(getY(), field.getP(), field);
                return new ECPoint(getX(), field.getP(), field);
            }
            BigInteger modInverseResult = field.modInverse(BigInteger.valueOf(2).multiply(getY()));
            S = BigInteger.valueOf(3).multiply(getX().pow(2)).add(field.getA())
                .multiply(modInverseResult).mod(field.getP());
        } else {
            // [Case] P != Q
            if (this.getX().equals(r.getX())) {
                return new ECPoint(getX(), field.getP(), field);
            }
            BigInteger modInverseResult = field.modInverse(r.getX().subtract(getX()));
            S = r.getY().subtract(getY()).multiply(modInverseResult).mod(field.getP());
        }
        BigInteger newX = S.pow(2).subtract(getX()).subtract(r.getX()).mod(field.getP());
        BigInteger newY = S.multiply(getX().subtract(newX)).subtract(getY()).mod(field.getP());
        return new ECPoint(newX, newY, field);
    }


    public ECPoint slowMultiplyOperation(BigInteger i) {
        ECPoint newPoint = this;
        for (BigInteger bi = i; bi.compareTo(BigInteger.ONE) > 0;
            bi = bi.subtract(BigInteger.ONE)) {
            newPoint = addOperation(newPoint);
        }
        return newPoint;
    }

    public ECPoint multiplyOperation(BigInteger k) {
        List<Integer> bitInfo = new ArrayList<Integer>();
        for (int i = 0; i < k.bitLength(); i++) {
            if (k.testBit(i)) {
                bitInfo.add(1);
            } else {
                bitInfo.add(0);
            }
        }
        ECPoint X = this;
        ECPoint R = new ECPoint(BigInteger.ZERO, field.getP(), field);
        for (Integer bit : bitInfo) {
            if (bit == 1) {
                R = R.addOperation(X);
            }
            X = X.addOperation(X);
        }
        return R;
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public EllipticCurve getField() {
        return field;
    }

    public void setField(EllipticCurve field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "ECPoint{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}
