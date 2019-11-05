package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.Country;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stateless EJB implementing CRUD operations for countries
 *
 * @author Luc Wachter, Alison Savary
 */
@Stateless
public class CountriesDAOImpl implements CountriesDAO {
    // Payara resource lookup
    @Resource(lookup = "jdbc/citylogdb")
    private DataSource dataSource;

    // TODO: Remove
    @Override
    public List<Country> findAll() {
        List<Country> countries = new LinkedList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Country");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String code = resultSet.getString("countryCode");
                String name = resultSet.getString("name");
                countries.add(new Country(code, name));
            }
            connection.close();

            return countries;
        } catch (SQLException ex) {
            Logger.getLogger(CountriesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public Country create(Country entity) {
        // TODO exception
        return null;
    }

    @Override
    public Country findById(String id) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM Country WHERE countryCode = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                // TODO: throw more specific exception
                throw new Exception("Could not find country with country code = " + id);
            }

            return new Country(resultSet.getString(1), resultSet.getString(2));
        } catch (SQLException ex) {
            Logger.getLogger(CountriesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Error(ex);
        }
    }

    @Override
    public void update(Country entity) {
        // TODO exception
    }

    @Override
    public void deleteById(String id) {
        // TODO exception
    }
}
