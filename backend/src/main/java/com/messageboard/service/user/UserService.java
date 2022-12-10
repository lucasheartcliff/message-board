package com.messageboard.service.user;

import com.messageboard.model.User;
import com.messageboard.viewmodel.UserViewModel;

import java.util.List;

public interface UserService {
    User getOrCreateUser(UserViewModel model) throws Exception;

    List<User> getUsers();

    User getUser(long id) throws Exception;

    User createUser(UserViewModel model) throws Exception;

    void deleteUser(Long userId) throws Exception;
}
