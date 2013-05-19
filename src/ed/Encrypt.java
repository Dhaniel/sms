package ed;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * Created by IntelliJ IDEA.
 * User: dhaniel
 * Date: 4/27/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Encrypt {

    private static Signature signature;
    private static KeyPair keyPair;

    public Encrypt() {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchProviderException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

        try {
            keyGen.initialize(ecSpec, new SecureRandom());
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Encrypt.keyPair = keyGen.generateKeyPair();
        try {
            Encrypt.signature = Signature.getInstance("ECDSA", "BC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchProviderException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // generate a signature
        try {
            signature.initSign(keyPair.getPrivate(), Utils.createFixedRandom());
        } catch (InvalidKeyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    
    public String encrypt(byte[] message) {
        try {
            Encrypt.signature.update(message);
        } catch (SignatureException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            return Encrypt.signature.sign().toString();
        } catch (SignatureException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

     public boolean checkSignature(byte[] message, byte[] signature) {
         try {
             Encrypt.signature.initVerify(Encrypt.keyPair.getPublic());
         } catch (InvalidKeyException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         try {
             Encrypt.signature.update(message);
         } catch (SignatureException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         try {
             return Encrypt.signature.verify(signature);
         } catch (SignatureException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         return false;
     }
}

