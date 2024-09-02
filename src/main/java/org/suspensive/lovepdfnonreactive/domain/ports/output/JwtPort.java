package org.suspensive.lovepdfnonreactive.domain.ports.output;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtPort {
    String generateToken(UserDetails user);
}
