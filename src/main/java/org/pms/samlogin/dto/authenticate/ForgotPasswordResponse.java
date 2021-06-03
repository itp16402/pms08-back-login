package org.pms.samlogin.dto.authenticate;

import lombok.*;
import org.pms.samlogin.enums.ForgotPasswordStatus;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ForgotPasswordResponse {

    private ForgotPasswordStatus forgotPasswordStatus;

    private String resetKeyPassword;
}
