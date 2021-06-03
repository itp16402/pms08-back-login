package org.pms.samlogin.dto.authenticate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.pms.samlogin.enums.AuthenticationStatus;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    private final String jwt;
    private String username;
    private String email;
    private Set<GrantedAuthority> authorities;
    private AuthenticationStatus authenticationStatus;
}
