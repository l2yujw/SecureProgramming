

import java.math.BigInteger;

public class DiffieHellman {

    private final String hexStrOfP = "FFFFFFFF FFFFFFFF ADF85458 A2BB4A9A AFDC5620 273D3CF1\n"
        + "D8B9C583 CE2D3695 A9E13641 146433FB CC939DCE 249B3EF9\n"
        + "7D2FE363 630C75D8 F681B202 AEC4617A D3DF1ED5 D5FD6561\n"
        + "2433F51F 5F066ED0 85636555 3DED1AF3 B557135E 7F57C935\n"
        + "984F0C70 E0E68B77 E2A689DA F3EFE872 1DF158A1 36ADE735\n"
        + "30ACCA4F 483A797A BC0AB182 B324FB61 D108A94B B2C8E3FB\n"
        + "B96ADAB7 60D7F468 1D4F42A3 DE394DF4 AE56EDE7 6372BB19\n"
        + "0B07A7C8 EE0A6D70 9E02FCE1 CDF7E2EC C03404CD 28342F61\n"
        + "9172FE9C E98583FF 8E4F1232 EEF28183 C3FE3B1B 4C6FAD73\n"
        + "3BB5FCBC 2EC22005 C58EF183 7D1683B2 C6F34A26 C1B2EFFA\n"
        + "886B4238 61285C97 FFFFFFFF FFFFFFFF";
    private final BigInteger p;
    private final Integer g;

    public BigInteger getX() {
        return x;
    }

    private final BigInteger x;
    private final BigInteger y;
    private BigInteger key;

    public DiffieHellman() {
        this.p = new BigInteger(hexStrOfP.replace("\n", "").replace(" ", ""), 16);
        this.g = 2;
        this.x = getRandomPrime();
        this.y = generatePublicKey();
    }

    private BigInteger getRandomPrime() {
        int random = (int) (Math.random()*100);
        return new BigInteger(String.valueOf(random)).nextProbablePrime();
    }

    private BigInteger generatePublicKey() {
        return BigInteger.valueOf(g).modPow(x, p);
    }

    private void generateKey(BigInteger peerPublicKey) {
        this.key = peerPublicKey.modPow(this.x, this.p);
    }

    public BigInteger getY() {
        return y;
    }

    public BigInteger getKey() {
        return key;
    }

    public void setPeerPublicKey(BigInteger peerPublicKey) {
        generateKey(peerPublicKey);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append(
                "========================================================================================\n")
            .append("DeffieHellman (ffdhe2048)\n")
            .append(
                "The 2048-bit group has registry value 256 and is calculated from the following formula: \n")
            .append("The modules is: \n")
            .append("p = 2^2048 - 2^1984 + {[2^1918 * e] + 560316 } * 2^64 - 1\n\n")
            .append("The hexadecimal representation of p is: \n").append(hexStrOfP).append("\n\n")
            .append("The generator is: g = ").append(this.g).append("\n\n")
            .append("The group size is: q = (p-1)/2\n")
            .append("The hexadecimal representation of q is:\n")
            .append(getHexString(this.p.subtract(BigInteger.valueOf(1))
                .divide(BigInteger.valueOf(2)))).append("\n\n")
            .append("The estimated symmetric-equivalent strength of this group is 103 bits.\n")
            .append("Peers using ffdhe2048 that want to optimize their key exchange with a\n"
                + "short exponent (Section 5.2) should choose a secret key of at least 225 bits.");
        return builder.toString();
    }

    public String getHexString(BigInteger intValue) {
        StringBuilder builder = new StringBuilder();
        String hexString = intValue.toString(16);
        int lineCount = 0;
        for (int i = 0; i < hexString.length() / 8; i++) {
            builder.append(hexString.substring(i * 8, (i * 8) + 8)).append(" ");
            if (++lineCount == 6) {
                builder.append("\n");
                lineCount = 0;
            }
        }
        return builder.toString();
    }
}
