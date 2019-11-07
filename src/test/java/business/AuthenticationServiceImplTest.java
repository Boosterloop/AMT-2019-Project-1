package business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the authentication service
 *
 * @author Alison Savary, Luc Wachter
 */
public class AuthenticationServiceImplTest {
    private AuthenticationService auth = new AuthenticationServiceImpl();
    private String password = "As3cr37ops";

    @Test
    public void hashedPasswordsShouldMatchWhenComparedToPlainTextPassword() {
        String hashed = auth.hashPassword(password);
        boolean match = auth.checkPassword(password, hashed);

        assertTrue(match);
    }

    @Test
    public void hashedPasswordsShouldNotMatchWhenComparedToOtherPassword() {
        String hashed = auth.hashPassword("otherPass");
        boolean match = auth.checkPassword(password, hashed);

        assertFalse(match);
    }
}
