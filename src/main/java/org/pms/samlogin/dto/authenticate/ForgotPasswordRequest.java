package org.pms.samlogin.dto.authenticate;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ForgotPasswordRequest {

    private String username;
    private String email;
}
