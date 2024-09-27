package org.suspensive.lovepdfnonreactive.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    private String id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
    private boolean status;
    private Map<String,byte[]> pdfs;

    private User(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleName()))));
        roles.forEach(role -> role.getPermissionList().forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission))));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
