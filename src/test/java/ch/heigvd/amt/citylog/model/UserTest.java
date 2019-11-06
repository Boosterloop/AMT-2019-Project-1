package ch.heigvd.amt.citylog.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the User model
 *
 * @author Alison Savary, Luc Wachter
 */
public class UserTest {
    @Test
    public void userShouldBeCreatable() {
        User user = new User("laykel", "ZüR1chB3rn");

        assertEquals(user.getUsername(), "laykel");
        assertEquals(user.getPasswordHash(), "ZüR1chB3rn");
    }
}
