package com.dhruvil.auth_service.secretKeyGenerator;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();

        byte[] key = new byte[32];
        secureRandom.nextBytes(key);

        String secret = Base64.getEncoder().encodeToString(key);
        System.out.println("Jwt Secret Key: " + secret);
    }
}
