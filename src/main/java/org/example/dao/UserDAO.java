package org.example.dao;

import org.example.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserDAO {
    void registration(List<User> users);

    List<User> readData() throws IOException;
}
