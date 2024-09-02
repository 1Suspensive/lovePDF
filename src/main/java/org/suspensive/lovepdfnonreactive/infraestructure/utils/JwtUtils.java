package org.suspensive.lovepdfnonreactive.infraestructure.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private final Algorithm algorithm;

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    public JwtUtils(final Algorithm algorithm){
        this.algorithm = algorithm;
    }

    public String generateToken(UserDetails userDetails){
        String authorities = userDetails.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withIssuer(jwtIssuer)
                .withSubject(userDetails.getUsername())
                .withClaim("authorities",authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 90000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(this.algorithm);
    }

    public DecodedJWT verifyToken(String token) throws SignatureVerificationException
            , AlgorithmMismatchException
            , TokenExpiredException
            , InvalidClaimException {
        return JWT.require(this.algorithm)
                .withIssuer(this.jwtIssuer)
                .build()
                .verify(token);
    }
}
