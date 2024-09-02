package org.suspensive.lovepdfnonreactive.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class Role {
    public static final Role DEFAULT_ROLE = Role.builder()
            .id(1).roleName("DEFAULT_USER")
            .permissionList(Set.of("UPLOAD","DOWNLOAD"))
            .build();

    private Role(){}

    private int id;
    private String roleName;
    private Set<String> permissionList;
}
