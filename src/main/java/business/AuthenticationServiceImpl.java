package business;

import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;

/**
 * Implementation providing hashing and testing services
 * for authentication purposes
 *
 * Source: SoftEng-HEIGVD/Teaching-HEIGVD-AMT-Example-Notes
 *
 * @author Luc Wachter, Alison Savary
 */
@Stateless
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String plainTextPassword, String hashedPassword) {
        try {
            return BCrypt.checkpw(plainTextPassword, hashedPassword);
        } catch (Exception e) {
            return false;
        }
    }
}
