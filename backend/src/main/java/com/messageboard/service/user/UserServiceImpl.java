package com.messageboard.service.user;

import com.messageboard.model.User;
import com.messageboard.persistence.TransactionHandler;
import com.messageboard.repository.RepositoryFactory;
import com.messageboard.service.BaseService;
import com.messageboard.viewmodel.UserViewModel;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserServiceImpl extends BaseService implements UserService {

    public UserServiceImpl(RepositoryFactory repositoryFactory, TransactionHandler transactionHandler) {
        super(repositoryFactory, transactionHandler);
    }

    @Override
    public User getOrCreateUser(UserViewModel model) throws Exception {
        return transactionHandler.encapsulateTransaction(() -> {
            User user = repositoryFactory.buildUserRepository().findUserByEmail(model.getEmail());
            if (user == null) {
                user = createUser(model);
            }
            return user;
        });
    }

    @Override
    public List<User> getUsers() {
        List<User> result = repositoryFactory.buildUserRepository().findAll();
        return CollectionUtils.isEmpty(result) ? new ArrayList<>() : result;
    }

    @Override
    public User getUser(long id) throws Exception {
        Optional<User> optionalUser = repositoryFactory.buildUserRepository().findById(id);
        if (!optionalUser.isPresent()) throw new Exception("User not founded");
        return optionalUser.get();
    }

    @Override
    public User createUser(UserViewModel model) throws Exception {
        return transactionHandler.encapsulateTransaction(() -> {
            User user = new User();
            user.setEmail(model.getEmail());
            return repositoryFactory.buildUserRepository().save(user);
        });
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        transactionHandler.encapsulateTransaction(() -> {
            repositoryFactory.buildUserRepository().deleteById(userId);
            return null;
        });
    }
}
