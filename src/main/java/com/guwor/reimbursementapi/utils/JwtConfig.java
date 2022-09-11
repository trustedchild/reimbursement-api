package com.guwor.reimbursementapi.utils;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Random;

public class JwtConfig {

    private static final Random RANDOM = new SecureRandom();
//    public static byte[] getNextSalt() {
//        byte[] salt = new byte[56];
//        RANDOM.nextBytes(salt);
//        return salt;
//    }

    private final String salt = "laskdjflasdkfsdkfjasdklfjhasdkfikjehfksjdhfklsadjhflskdf";

    private int expiration = 60 * 60 * 1000;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private final Key signingKey;

    public JwtConfig() {
        //byte[] salt = new byte[56];
        //RANDOM.nextBytes(salt);
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(String.valueOf(salt));
        signingKey = new SecretKeySpec(saltyBytes, signatureAlgorithm.getJcaName());
    }

    public int getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public Key getSigningKey() {
        return signingKey;
    }
}
