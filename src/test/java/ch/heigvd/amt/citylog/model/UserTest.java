package ch.heigvd.amt.citylog.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void userShouldBeCreatable() {
        User user = new User("laykel", "ZüR1chB3rn");

        assertEquals(user.getUsername(), "laykel");
        assertEquals(user.getPassword(), "ZüR1chB3rn");
    }
}
