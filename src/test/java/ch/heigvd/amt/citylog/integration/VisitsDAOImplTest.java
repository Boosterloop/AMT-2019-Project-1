package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.City;
import ch.heigvd.amt.citylog.model.Country;
import ch.heigvd.amt.citylog.model.User;
import ch.heigvd.amt.citylog.model.Visit;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class VisitsDAOImplTest {
    @EJB
    private VisitsDAO visits;

    private User user = new User("admin", "zdasz7da8s7dz");
    private Country country = new Country("CH", "Schweiz");;
    private City city = new City(23, "Bern", country);

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void visitsShouldBeCreatable() {
        Visit visit = new Visit(0, user, city, "2012-04-12");
        visits.create(visit);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void visitsShouldBeRetrievableViaDAO() {
        Visit visit = new Visit(0, user, city, "2012-04-12");
        Visit createdVisit = visits.create(visit);
        visit.setId(createdVisit.getId());
        Visit retrievedVisit = visits.findById(createdVisit.getId());

        assertEquals(visit, createdVisit);
        assertSame(visit, createdVisit);
        assertNotSame(visit, retrievedVisit);
    }

    @Test
    public void visitsShouldBeRemovable() throws Exception {
        Visit visit = new Visit(0, user, city, "2012-04-12");
        Visit createdVisit = visits.create(visit);
        visit.setId(createdVisit.getId());

        assertEquals(visit, createdVisit);

        visits.deleteById(createdVisit.getId());

        Visit retrievedVisit = visits.findById(createdVisit.getId());
        assertNull(retrievedVisit);
    }

    @Test
    public void visitsShouldBeUpdatable() throws Exception {
        Visit visit = new Visit(0, user, city, "2012-04-12");
        Visit createdVisit = visits.create(visit);
        visit.setId(createdVisit.getId());
        // Update visit
        visit.setStartDate("1970-12-03");
        visits.update(visit);

        Visit modifiedVisitInDB = visits.findById(visit.getId());
        assertNotEquals(createdVisit, modifiedVisitInDB);
    }
}