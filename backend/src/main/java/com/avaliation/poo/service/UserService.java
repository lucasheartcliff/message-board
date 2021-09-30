package com.avaliation.poo.service;

import com.avaliation.poo.model.User;
import com.avaliation.poo.repository.UserRepository;
import com.avaliation.poo.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends BaseService {
    @Autowired
    private UserRepository userRepo;

    public User getOrCreateUser(UserViewModel model) throws Exception {
        return encapsulateTransaction(() -> {
            User user = userRepo.findUserByEmail(model.getEmail());
            if (user == null) {
                user = createUser(model);
            }
            return user;
        });
    }

    public List<User> getUsers() {
        List<User> result = userRepo.findAll();
        return CollectionUtils.isEmpty(result) ? new ArrayList<>() : result;
    }

    public User getUser(long id) throws Exception {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()) throw new Exception("User not founded");
        return optionalUser.get();
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
