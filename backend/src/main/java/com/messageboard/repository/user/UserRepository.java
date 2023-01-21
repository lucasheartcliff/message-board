package com.messageboard.repository.user;

import com.messageboard.model.User;
import com.messageboard.repository.EntityRepository;

public interface UserRepository extends EntityRepository<User, Long> {

    User findUserByEmail(String email);
}
