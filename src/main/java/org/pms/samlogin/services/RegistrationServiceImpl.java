package org.pms.samlogin.services;

import lombok.extern.slf4j.Slf4j;
import org.pms.samlogin.domain.User;
import org.pms.samlogin.dto.UserDto;
import org.pms.samlogin.exceptions.UnacceptableActionException;
import org.pms.samlogin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;
    private ConversionService conversionService;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    public void registerMember(UserDto userDto) {

        log.info("Registration process for user {} begins", userDto.getUsername());

        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent())
            throw new UnacceptableActionException("There is already a user with username" + userDto.getUsername());

        User user = conversionService.convert(userDto, User.class);

        userRepository.save(user);

        log.info("Registration process for user {} end", userDto.getUsername());
    }
}
