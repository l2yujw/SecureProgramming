import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger p, q, K, φ;
    private BigInteger e, d;

    public RSA(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        this.K = p.multiply(q);
        this.φ = LCM(p.subtract(BigInteger.ONE), q.subtract(BigInteger.ONE));
        this.e = BigInteger.valueOf(17);
        this.d = e.modInverse(φ);
    }

    public RSA() {
        this.p = generatePrimeNumber();
        this.q = generatePrimeNumber();
        this.K = p.multiply(q);
        this.φ = LCM(p.subtract(BigInteger.ONE), q.subtract(BigInteger.ONE));
        this.e = generateE();
        this.d = e.modInverse(φ);
    }

    public BigInteger LCM(BigInteger v1, BigInteger v2){
        BigInteger gcd = v1.gcd(v2);
        BigInteger lcm = v1.multiply(v2).divide(gcd);
        return lcm;
    }

    public BigInteger Encryption(BigInteger m){
        BigInteger c = m.pow(e.intValue()).mod(K);
        return c;
    }

    public BigInteger Decryption(BigInteger c){
        return c.pow(d.intValue()).mod(K);
    }

    public BigInteger generatePrimeNumber(){
        return BigInteger.probablePrime(1024, new Random());
    }

    public BigInteger generateE(){
        BigInteger newE = generatePrimeNumber();
        BigInteger gcdResult = newE.gcd(φ);
        while (gcdResult.equals(BigInteger.ONE)){
            newE = generatePrimeNumber();
            gcdResult = newE.gcd(φ);
        }
        return newE;
    }

    @Override
    public String toString() {
        return "RSA{" +
                "p=" + p +
                ", q=" + q +
                ", K=" + K +
                ", φ=" + φ +
                ", e=" + e +
                ", d=" + d +
                '}';
    }
}
