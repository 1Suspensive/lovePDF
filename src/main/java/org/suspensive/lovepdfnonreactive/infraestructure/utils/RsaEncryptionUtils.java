package org.suspensive.lovepdfnonreactive.infraestructure.utils;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class RsaEncryptionUtils {

    private final KeyPair keyPair;

    public RsaEncryptionUtils() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        this.keyPair = keyPairGenerator.generateKeyPair();
    }

    @Bean
    public Algorithm getAlgorithm(){
        return Algorithm.RSA256((RSAPublicKey) getPublicKey(), (RSAPrivateKey) getPrivateKey());
    }

    private PrivateKey getPrivateKey(){
        return keyPair.getPrivate();
    }

    private PublicKey getPublicKey(){
        return keyPair.getPublic();
    }
}
