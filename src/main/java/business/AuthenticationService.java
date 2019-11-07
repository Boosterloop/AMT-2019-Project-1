package business;

import javax.ejb.Local;

/**
 * Interface providing hashing and testing services
 * for authentication purposes
 *
 * Source: SoftEng-HEIGVD/Teaching-HEIGVD-AMT-Example-Notes
 *
 * @author Luc Wachter, Alison Savary
 */
@Local
public interface AuthenticationService {
    String hashPassword(String password);
    boolean checkPassword(String password, String hashedPassword);
}
