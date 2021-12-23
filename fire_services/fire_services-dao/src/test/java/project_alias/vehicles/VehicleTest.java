package project_alias.vehicles;

import static org.junit.Assert.*;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetch;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.from;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.orderBy;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

import java.math.BigDecimal;

import org.junit.Test;

import ua.com.fielden.platform.dao.QueryExecutionModel;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.query.fluent.fetch;
import ua.com.fielden.platform.entity.query.model.EntityResultQueryModel;
import ua.com.fielden.platform.entity.query.model.OrderingModel;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import project_alias.personnel.Person;
import project_alias.test_config.AbstractDaoTestCase;
import project_alias.validators.VehicleNumberValidator;


/**
 * Basic testing of {@link Vehicle}
 * 
 * @author Generated
 *
 */
public class VehicleTest extends AbstractDaoTestCase {

    /**
     * The names of the test method should be informative. 
     * It is recommended to make the method name sound like a sentence stating the expected behaviour.
     * In this case, the test method name indicates that it is expected to find person with initials RDM and that it has an active status.
     * <p> 
     * Each test method should be related to exactly one concern, which facilitates creation of unit tests that address a single concern.
     */
    @Test
    public void vehicle_can_be_created_and_saved() {
        final var vehicleType = co(VehicleType.class).save(co(VehicleType.class).new_().
                setTitle("Fire truck").setDesc("A regular fire truck"));

        final var vehicle = co(Vehicle.class).new_();
        vehicle.setNumber("AA9999AA").setModel("MAN (TGM12.240)").setVehicleType(vehicleType);
        final var savedVehicle = co(Vehicle.class).save(vehicle);
        assertNotNull(savedVehicle);
        assertEquals("AA9999AA", savedVehicle.getNumber());
        assertEquals("MAN (TGM12.240)", savedVehicle.getModel());
        assertTrue(savedVehicle.isActive());
    }
    
    @Test
    public void can_access_prop_active_with_default_fetch_model() {
        final var vehicleType = co(VehicleType.class).save(co(VehicleType.class).new_().
                setTitle("Fire truck").setDesc("A regular fire truck"));
        co(Vehicle.class).save(co(Vehicle.class).new_().
                setNumber("AA9999AA").setModel("MAN (TGM12.240)").setVehicleType(vehicleType));
        final var vehicle = co(Vehicle.class).findByKeyAndFetch(VehicleCo.FETCH_PROVIDER.fetchModel(), "AA9999AA");
        assertTrue(vehicle.isActive());
    }
    
    @Test
    public void required_properties_are_actually_required() {
        final var vehicle = co(Vehicle.class).new_();
        final MetaProperty<String> mpNumber = vehicle.getProperty("number");
        final MetaProperty<String> mpModel = vehicle.getProperty("model");
        final MetaProperty<String> mpVehicleType = vehicle.getProperty("vehicleType");
        
        assertTrue(mpNumber.isRequired());
        assertTrue(mpModel.isRequired());
        assertTrue(mpVehicleType.isRequired());
    }
    
    @Test
    public void number_should_be_in_the_specified_format() {
        final var vehicleType = co(VehicleType.class).save(co(VehicleType.class).new_().
                setTitle("Fire truck").setDesc("A regular fire truck"));
        final var vehicle = co(Vehicle.class).new_().setNumber("AB1234BC").setModel("MAN (TGM12.240)").setVehicleType(vehicleType);
        vehicle.setNumber("A1234DCC");
        final MetaProperty<String> mpNumber = vehicle.getProperty("number");
        assertFalse(mpNumber.isValid());
        final Result validationResult = mpNumber.getFirstFailure();
        assertEquals(VehicleNumberValidator.ERR_INCORRECT_NUMBER_FORMAT, validationResult.getMessage());
        assertEquals("AB1234BC", vehicle.getNumber());
    }
    
    @Test
    public void number_should_be_eight_characters_long() {
        final var vehicleType = co(VehicleType.class).save(co(VehicleType.class).new_().
                setTitle("Fire truck").setDesc("A regular fire truck"));
        final var vehicle = co(Vehicle.class).new_().setNumber("AB1234BC").setModel("MAN (TGM12.240)").setVehicleType(vehicleType);
        vehicle.setNumber("AA123BC");
        final MetaProperty<String> mpNumber = vehicle.getProperty("number");
        assertFalse(mpNumber.isValid());
        final Result validationResult = mpNumber.getFirstFailure();
        assertEquals(VehicleNumberValidator.ERR_INCORRECT_NUMBER_FORMAT, validationResult.getMessage());
        assertEquals("AB1234BC", vehicle.getNumber());
        
        vehicle.setNumber("AA12345BC");
        assertFalse(mpNumber.isValid());
        assertEquals(VehicleNumberValidator.ERR_INCORRECT_NUMBER_FORMAT, validationResult.getMessage());
        assertEquals("AB1234BC", vehicle.getNumber());
    }
    
    @Test
    public void number_should_not_contain_lower_case_letters() {
        final var vehicleType = co(VehicleType.class).save(co(VehicleType.class).new_().
                setTitle("Fire truck").setDesc("A regular fire truck"));
        final var vehicle = co(Vehicle.class).new_().setNumber("AB1234BC").setModel("MAN (TGM12.240)").setVehicleType(vehicleType);
        vehicle.setNumber("Aa1234BC");
        final MetaProperty<String> mpNumber = vehicle.getProperty("number");
        assertFalse(mpNumber.isValid());
        final Result validationResult = mpNumber.getFirstFailure();
        assertEquals(VehicleNumberValidator.ERR_INCORRECT_NUMBER_FORMAT, validationResult.getMessage());
        assertEquals("AB1234BC", vehicle.getNumber());
    }

    @Override
    public boolean saveDataPopulationScriptToFile() {
        return false;
    }

    @Override
    public boolean useSavedDataPopulationScript() {
        return false;
    }

    @Override
    protected void populateDomain() {
        // Need to invoke super to create a test user that is responsible for data population 
        super.populateDomain();

        // Here is how the Test Case universal constants can be set.
        // In this case the notion of now is overridden, which makes it possible to have an invariant system-time.
        // However, the now value should be after AbstractDaoTestCase.prePopulateNow in order not to introduce any date-related conflicts.
        final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
        constants.setNow(dateTime("2019-10-01 11:30:00"));

        // If the use of saved data population script is indicated then there is no need to proceed with any further data population logic.
        if (useSavedDataPopulationScript()) {
            return;
        }
    }

}
