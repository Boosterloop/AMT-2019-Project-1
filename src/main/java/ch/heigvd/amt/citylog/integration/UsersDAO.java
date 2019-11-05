package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.User;

/**
 * Interface to specify actions carried by users
 * and the types they use
 *
 * @author Luc Wachter, Alison Savary
 */
public interface UsersDAO extends DAO<String, User> {
}
