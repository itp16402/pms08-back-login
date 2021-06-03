package org.pms.samlogin.dto.authenticate;

import lombok.*;
import org.pms.samlogin.enums.ChangePasswordStatus;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ChangePasswordResponse {

    private ChangePasswordStatus changePasswordStatus;
}
