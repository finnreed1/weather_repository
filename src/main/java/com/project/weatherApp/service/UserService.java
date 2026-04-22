package com.project.weatherApp.service;

import com.project.weatherApp.dto.LoginUserDto;
import com.project.weatherApp.dto.RegistrationUserDto;
import com.project.weatherApp.dto.UserDto;
import com.project.weatherApp.model.Session;
import com.project.weatherApp.model.User;
import com.project.weatherApp.repository.UserRepository;
import com.project.weatherApp.util.EncodePasswordMapper;
import com.project.weatherApp.util.UserUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SessionService sessionService;

    @Transactional
    public void registrationUser(RegistrationUserDto regUserDto) {
        String newPassword = EncodePasswordMapper.mapEncodePassword(regUserDto.getPassword());
        User user = new User(regUserDto.getLogin(), newPassword);
        userRepository.save(user);
    }

    @Transactional
    public Optional<User> findUserByLogin(String login) {
        return Optional.of(userRepository.findByLogin(login));
    }

    @Transactional
    public Optional<User> findUserById(int id) {
        return Optional.of(userRepository.findById(id));
    }

    @Transactional
    public boolean isUniqueLogin(String login) {
        User user = userRepository.findByLogin(login);
        return user == null;
    }

    @Transactional
    public boolean isCorrectPassword(LoginUserDto user) {
        String userPassword = userRepository.findByLogin(user.getLogin()).getPassword();
        String encodedInputPassword = EncodePasswordMapper.mapEncodePassword(user.getPassword());
        return encodedInputPassword.equals(userPassword);
    }
    @Transactional
    public UserDto getUserDtoBySession(Session session) {
        User user = findUserById(session.getUserId()).get();
        return new UserDto(user.getLogin());
    }

    public int getUserIdByLogin(String login) {
        return userRepository.findByLogin(login).getId();
    }

}