package com.project.weatherApp.service;

import com.project.weatherApp.dto.LoginUserDto;
import com.project.weatherApp.dto.RegistrationUserDto;
import com.project.weatherApp.dto.UserDto;
import com.project.weatherApp.exception.UserAuthenticationException;
import com.project.weatherApp.model.Session;
import com.project.weatherApp.model.User;
import com.project.weatherApp.repository.UserRepository;
import com.project.weatherApp.util.EncodePasswordUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SessionService sessionService;

    @Transactional
    public void registrationUser(RegistrationUserDto regUserDto) {
        String newPassword = EncodePasswordUtils.mapEncodePassword(regUserDto.getPassword());
        User user = new User(regUserDto.getLogin(), newPassword);
        userRepository.save(user);
    }

    @Transactional
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public Optional<User> findUserById(int id) {
        return Optional.of(userRepository.findById(id));
    }

    @Transactional
    public boolean isUniqueLogin(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        return user.isEmpty();
    }

    @Transactional
    public boolean isCorrectPassword(LoginUserDto user) {
        String rawPassword = user.getPassword();
        User existingUser = userRepository.findByLogin(user.getLogin())
                .orElseThrow(() -> new UserAuthenticationException("User with login " + user.getLogin() + " is not found"));
        String hashPassword = existingUser.getPassword();
        return BCrypt.checkpw(rawPassword, hashPassword);
    }
    @Transactional
    public UserDto getUserDtoBySession(Session session) {
        User user = findUserById(session.getUserId()).get();
        return new UserDto(user.getLogin());
    }

    @Transactional
    public int getUserIdByLogin(String login) {
        return userRepository.findByLogin(login).get().getId();
    }

}