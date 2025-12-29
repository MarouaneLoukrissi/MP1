package com.testIC.pattern.factory;
import com.testIC.model.User;
public class UserFactory {
    public static User create(String username, String password) {
        return new User(username, password);
    }
}
