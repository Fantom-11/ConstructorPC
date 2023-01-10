package org.example.service;

import org.example.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void registration(User user) throws IOException;

    boolean checkExistingUserByLogin(List<User> users, User user);

    boolean checkExistingUserByLoginAndPassword(User user) throws IOException;

    boolean checkIsAdmin(String login) throws IOException;

}
