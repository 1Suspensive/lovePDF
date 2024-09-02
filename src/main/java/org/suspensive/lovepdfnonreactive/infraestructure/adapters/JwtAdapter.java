package org.suspensive.lovepdfnonreactive.infraestructure.adapters;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.ports.output.JwtPort;
import org.suspensive.lovepdfnonreactive.infraestructure.utils.JwtUtils;

@Component
public class JwtAdapter implements JwtPort {

    private final JwtUtils jwtUtils;

    public JwtAdapter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String generateToken(UserDetails user) {
        return jwtUtils.generateToken(user);
    }
}
