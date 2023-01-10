package org.example.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dao.UserDAO;
import org.example.domain.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final String USERS_JSON = "json/users.json";
    public static final File USERS_FILE = new File(USERS_JSON);

    public void registration(List<User> users) {
        try {
            OBJECT_MAPPER.writeValue(USERS_FILE, users);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> readData() throws IOException {
        return OBJECT_MAPPER.readValue(USERS_FILE, new TypeReference<>(){});
    }

}
