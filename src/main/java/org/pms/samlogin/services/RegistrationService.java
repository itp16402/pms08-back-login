package org.pms.samlogin.services;

import org.pms.samlogin.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationService {

    void registerMember(UserDto userDto);
}
