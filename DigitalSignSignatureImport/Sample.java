import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Sample {

    public static void main(String[] args) {
        try {
            KeyPairGenerator kg = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec kpgparams = new ECGenParameterSpec("secp256r1");
            kg.initialize(kpgparams);

            KeyPair kp = kg.generateKeyPair();
            PublicKey pubKey = kp.getPublic();
            PrivateKey pvtKey = kp.getPrivate();

            // sign
            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
            ecdsaSign.initSign(pvtKey);
            String message = "text ecdsa with sha256";//getSHA256(msg)
            ecdsaSign.update(message.getBytes(), 0, message.length());
            byte[] signature = ecdsaSign.sign();

            // Validation
            ecdsaSign.initVerify(pubKey);
            ecdsaSign.update(message.getBytes(), 0, message.length());
            boolean result = ecdsaSign.verify(signature);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
