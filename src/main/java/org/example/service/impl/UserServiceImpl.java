package org.example.service.impl;

import org.example.dao.UserDAO;
import org.example.dao.impl.UserDAOImpl;
import org.example.domain.User;
import org.example.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
    }

    @Override
    public void registration(User user) throws IOException {
        List<User> users = userDAO.readData();
        if (checkExistingUserByLogin(users, user)) {
            throw new IllegalArgumentException("The user with login " + user.getLogin() + " already exists");
        }

        users.add(user);

        userDAO.registration(users);
    }

    public boolean checkExistingUserByLogin(List<User> users, User user) {
        return users.stream().anyMatch(u -> u.getLogin().equals(user.getLogin()));
    }

    @Override
    public boolean checkExistingUserByLoginAndPassword(User user) throws IOException {

        List<User> users = userDAO.readData();

        Predicate<User> loginPredicate = u -> u.getLogin().equals(user.getLogin());
        Predicate<User> passwordPredicate = u -> u.getPassword().equals(user.getPassword());
        return users.stream().anyMatch(loginPredicate.and(passwordPredicate));

    }


    @Override
    public boolean checkIsAdmin(String login) throws IOException {
        List<User> users = userDAO.readData();

        return users
                .stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .get()
                .isAdmin();
    }
}
