package com.michael.service.impl;

import com.michael.entity.User;
import com.michael.entity.UserModel;
import com.michael.exception.ItemAlreadyExistException;
import com.michael.exception.ResourseNotFoundException;
import com.michael.repository.UserRepository;
import com.michael.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserModel userModel) {
        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new ItemAlreadyExistException("User is already register with email:" + userModel.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserProfile() {
        Long userId = getLoggedInUser().getId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User with id:" + userId + " not found"));
    }

    @Override
    public User updateUser(UserModel userModel) {
        User user = getUserProfile();
        user.setName(userModel.getName() != null ? userModel.getName() : user.getName());
        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new ItemAlreadyExistException("User is already register with email:" + userModel.getEmail());
        }
        user.setEmail(userModel.getEmail() != null ? userModel.getEmail() : user.getEmail());
        user.setPassword(userModel.getPassword() != null ? passwordEncoder.encode(userModel.getPassword()) : user.getPassword());
        user.setAge(userModel.getAge() != null ? userModel.getAge() : user.getAge());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser() {
        User user = getUserProfile();
        userRepository.delete(user);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for the email: " + email));
    }
}
