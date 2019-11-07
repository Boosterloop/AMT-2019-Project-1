package ch.heigvd.amt.citylog.integration;

import business.AuthenticationService;
import ch.heigvd.amt.citylog.model.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stateless EJB implementing CRUD operations for users
 *
 * @author Luc Wachter, Alison Savary
 */
@Stateless
public class UsersDAOImpl implements UsersDAO {
    // Payara resource lookup
    @Resource(lookup = "jdbc/citylogdb")
    private DataSource dataSource;

    @EJB
    AuthenticationService auth;

    @Override
    public User create(User entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("INSERT INTO User (username, password) VALUES (?, ?)");
            statement.setString(1, entity.getUsername());
            // Hash password and then store
            statement.setString(2, auth.hashPassword(entity.getPasswordHash()));
            statement.execute();

            return entity;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public User findById(String id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM User WHERE username = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return new User(resultSet.getString(1), resultSet.getString(2));
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public void update(User entity) {
        // TODO exception
    }

    @Override
    public void deleteById(String id) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("DELETE FROM User WHERE username = ?");
            statement.setString(1, id);
            int nbrDeleted = statement.executeUpdate();

            if (nbrDeleted != 1) {
                throw new Exception("Could not find user with username = " + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }
}
