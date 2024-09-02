package org.suspensive.lovepdfnonreactive.infraestructure.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.suspensive.lovepdfnonreactive.domain.models.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Document(value = "users")
@Data
@AllArgsConstructor
public class UserDocument implements UserDetails {
    @Id
    private String id;
    private String username;
    private String password;
    @Indexed(unique = true)
    private String email;
    private Set<Role> roles;
    private boolean status;

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
