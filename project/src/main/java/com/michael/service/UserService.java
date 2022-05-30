package com.michael.service;

import com.michael.entity.User;
import com.michael.entity.UserModel;

public interface UserService {
    User createUser(UserModel userModel);

    User getUserProfile();

    User updateUser(UserModel userModel);

    void deleteUser();

    User getLoggedInUser();

}
