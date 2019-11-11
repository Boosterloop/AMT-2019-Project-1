package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.City;
import ch.heigvd.amt.citylog.model.User;
import ch.heigvd.amt.citylog.model.Visit;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stateless EJB implementing CRUD operations for visits
 *
 * @author Luc Wachter, Alison Savary
 */
@Stateless
public class VisitsDAOImpl implements VisitsDAO {
    // Payara resource lookup
    @Resource(lookup = "jdbc/citylogdb")
    private DataSource dataSource;

    @EJB
    CitiesDAO cities;

    @EJB
    UsersDAO users;

    @Override
    public List<Visit> findByUserAndCityId(String userId, Integer cityId) {
        List<Visit> visits = new LinkedList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM Visit WHERE fk_username = ? AND fk_idCity = ?");
            statement.setString(1, userId);
            statement.setString(2, cityId.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idVisit = Integer.parseInt(resultSet.getString("idVisit"));
                String name = resultSet.getString("fk_username");
                int idCity = Integer.parseInt(resultSet.getString("fk_idCity"));
                String startDate = resultSet.getString("startDate");
                String endDate = resultSet.getString("endDate");

                // Get city and user from resulting ids
                City city = cities.findById(idCity);
                User user = users.findById(name);

                visits.add(new Visit(idVisit, user, city, startDate, endDate));
            }

            return visits;
        } catch (SQLException ex) {
            Logger.getLogger(VisitsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public Visit create(Visit entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("INSERT INTO Visit (fk_username, fk_idCity, startDate, endDate) VALUES " +
                    "(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getUser().getUsername());
            statement.setString(2, String.valueOf(entity.getCity().getId()));
            statement.setString(3, entity.getStartDate());
            statement.setString(4, entity.getEndDate());
            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return entity;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public Visit findById(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM Visit WHERE idVisit = ?");
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            int idVisit = Integer.parseInt(resultSet.getString("idVisit"));
            String name = resultSet.getString("fk_username");
            int idCity = Integer.parseInt(resultSet.getString("fk_idCity"));
            String startDate = resultSet.getString("startDate");
            String endDate = resultSet.getString("endDate");

            // Get city and user from resulting ids
            City city = cities.findById(idCity);
            User user = users.findById(name);

            return new Visit(idVisit, user, city, startDate, endDate);
        } catch (SQLException ex) {
            Logger.getLogger(VisitsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }


    @Override
    public void update(Visit entity) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("UPDATE Visit SET fk_username=?, fk_idCity=?, startDate=?, endDate=? " +
                    "WHERE idVisit=?");
            statement.setString(1, entity.getUser().getUsername());
            statement.setString(2, String.valueOf(entity.getCity().getId()));
            statement.setString(3, entity.getStartDate());
            statement.setString(4, entity.getEndDate());
            statement.setString(5, String.valueOf(entity.getId()));
            int nbrUpdated = statement.executeUpdate();

            if (nbrUpdated != 1) {
                throw new Exception("Could not find visit with id = " + entity.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("DELETE FROM Visit WHERE idVisit = ?");
            statement.setString(1, id.toString());
            int nbrDeleted = statement.executeUpdate();

            if (nbrDeleted != 1) {
                throw new Exception("Could not find visit with id = " + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }
}
