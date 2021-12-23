package project_alias.dev_mod.util;


import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;

import project_alias.config.ApplicationDomain;
import project_alias.forms.Status;
import project_alias.personnel.Person;
import project_alias.vehicles.Vehicle;
import project_alias.vehicles.VehicleType;
import ua.com.fielden.platform.devdb_support.DomainDrivenDataPopulation;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.persistence.HibernateUtil;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.test.IDomainDrivenTestCaseConfiguration;
import ua.com.fielden.platform.utils.DbUtils;

/**
 * This is a convenience class for (re-)creation of the development database and its population.
 * 
 * It contains the <code>main</code> method and can be executed whenever the target database needs to be (re-)set.
 * <p>
 * 
 * <b>IMPORTANT: </b><i>One should be careful not to run this code against the deployment or production databases, which would lead to the loss of all data.</i>
 * 
 * <p>
 * 
 * @author Generated
 * 
 */
public class PopulateDb extends DomainDrivenDataPopulation {
    private static final Logger LOGGER = getLogger(PopulateDb.class);

    private final ApplicationDomain applicationDomainProvider = new ApplicationDomain();

    private PopulateDb(final IDomainDrivenTestCaseConfiguration config, final Properties props) {
        super(config, props);
    }

    public static void main(final String[] args) throws Exception {
        LOGGER.info("Initialising...");
        final String configFileName = args.length == 1 ? args[0] : "application.properties";
        final Properties props = new Properties();
        try (final FileInputStream in = new FileInputStream(configFileName)) {
            props.load(in);
        }

        LOGGER.info("Obtaining Hibernate dialect...");
        final Class<?> dialectType = Class.forName(props.getProperty("hibernate.dialect"));
        final Dialect dialect = (Dialect) dialectType.newInstance();
        LOGGER.info(format("Running with dialect %s...", dialect));
        final DataPopulationConfig config = new DataPopulationConfig(props);
        LOGGER.info("Generating DDL and running it against the target DB...");

        // use TG DDL generation or
        // Hibernate DDL generation final List<String> createDdl = DbUtils.generateSchemaByHibernate()
        final List<String> createDdl = config.getDomainMetadata().generateDatabaseDdl(dialect);
        final List<String> ddl = dialect instanceof H2Dialect ?
                DbUtils.prependDropDdlForH2(createDdl) :
                    DbUtils.prependDropDdlForSqlServer(createDdl);
        DbUtils.execSql(ddl, config.getInstance(HibernateUtil.class).getSessionFactory().getCurrentSession());

        final PopulateDb popDb = new PopulateDb(config, props);
        popDb.populateDomain();
    }

    @Override
    protected void populateDomain() {
        LOGGER.info("Creating and populating the development database...");

        setupUser(User.system_users.SU, "project.alias");
        setupPerson(User.system_users.SU, "project.alias");

        co(Status.class).save(co(Status.class).new_().setTitle("Approved").setDesc("The form is approved"));
        co(Status.class).save(co(Status.class).new_().setTitle("Disapproved").setDesc("The form is disapproved"));
        final var vehicleType = co(VehicleType.class).save(co(VehicleType.class).new_().setTitle("Fire truck").setDesc("The conventional fire"
                + " truck escorts firefighters along with essential tools like fire extinguishers, ladders, breathing apparatuses, "
                + "hydraulic rescue tools, and floodlights to the scene of a fire."));
        co(VehicleType.class).save(co(VehicleType.class).new_().setTitle("Heavy rescue truck").setDesc("Vehicles that get deployed to "
                + "traffic collisions, building collapses, and other disasters, as well as to fires."));
        co(VehicleType.class).save(co(VehicleType.class).new_().setTitle("Water tender").setDesc("A water tender (or tanker) is similar "
                + "to a fire truck, but comes with a weak pump and far less hoses."));
        co(VehicleType.class).save(co(VehicleType.class).new_().setTitle("Aerial truck").setDesc("Aerial trucks come equipped with "
                + "the iconic ladder extending from the top rear of the machine. The ladder extends telescopically to reach upper stories of buildings."));
        co(Vehicle.class).save(co(Vehicle.class).new_().
                setNumber("AA8888AA").setModel("MAN (TGM12.240)").setVehicleType(vehicleType));
        
        LOGGER.info("Completed database creation and population.");
    }

    private void setupPerson(final User.system_users defaultUser, final String emailDomain) {
        final User su = co(User.class).findByKey(defaultUser.name());
        save(new_(Person.class).setEmail(defaultUser + "@" + emailDomain).setDesc("Super Person").setUser(su));
    }

    @Override
    protected List<Class<? extends AbstractEntity<?>>> domainEntityTypes() {
        return applicationDomainProvider.entityTypes();
    }

}
