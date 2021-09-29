package com.avaliation.poo.service;

import com.avaliation.poo.model.User;
import com.avaliation.poo.repository.UserRepository;
import com.avaliation.poo.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService {
    @Autowired
    private UserRepository userRepo;

    public List<User> getUsers() {
        List<User> result = userRepo.findAll();
        return CollectionUtils.isEmpty(result) ? new ArrayList<>() : result;
    }

    public User createUser(UserViewModel model) throws Exception {
        return encapsulateTransaction(() -> {
            User user = new User();
            user.setEmail(model.getEmail());
            return userRepo.save(user);
        });
    }

    public void deleteUser(Long userId) throws Exception {
        encapsulateTransaction(() -> {
            userRepo.deleteById(userId);
            return null;
        });
    }
}
