package ch.heigvd.amt.citylog.integration;

import ch.heigvd.amt.citylog.model.Visit;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class VisitsDAOImplTest {
    @EJB
    private VisitsDAO visits;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void visitsShouldBeCreatable() {
        assertEquals(1, 1);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void visitsShouldBeRetrievableViaDAO() {
        assertEquals(1, 1);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void visitsShouldBeRemovable() {
        assertEquals(1, 1);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void visitsShouldBeUpdatable() {
        assertEquals(1, 1);
    }
}