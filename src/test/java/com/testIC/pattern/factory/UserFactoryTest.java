package com.testIC.pattern.factory;

import com.testIC.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserFactoryTest {
    @Test
    public void shouldCreateUserCorrectly() {
        User user = UserFactory.create("user-1", "pass");
        assertEquals("user-1", user.getUsername());
    }
}