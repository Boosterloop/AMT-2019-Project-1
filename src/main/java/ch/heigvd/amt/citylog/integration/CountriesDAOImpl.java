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
 */
@Stateless
public class CountriesDAOImpl implements CountriesDAO {
    // Payara resource lookup
    @Resource(lookup = "jdbc/citylogdb")
    private DataSource dataSource;

    @Override
    public List<Country> findAll() {
        List<Country> countries = new LinkedList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Country LIMIT 10");
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
        return null;
    }

    @Override
    public Country findById(String id) {
        return null;
    }

    @Override
    public void update(Country entity) {
    }

    @Override
    public void deleteById(String id) {
    }
}
