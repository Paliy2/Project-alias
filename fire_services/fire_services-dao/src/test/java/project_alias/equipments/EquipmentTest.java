package project_alias.equipments;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.keygen.KeyNumber;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import project_alias.test_config.AbstractDaoTestCase;
import project_alias.validators.NoWhiteSpacesValidator;
import project_alias.vehicles.Vehicle;
import project_alias.vehicles.VehicleCo;
import project_alias.vehicles.VehicleType;


/**
 * Basic testing of {@link Equipment}.
 * 
 * @author Project-alias Team
 *
 */
public class EquipmentTest extends AbstractDaoTestCase {

    @Test
    public void equipment_can_be_created_and_saved() {
        final var equipmentType = co(EquipmentType.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<EquipmentType>fetchFor("equipmentType").fetchModel(), "1m hose pipe");
        final var vehicle = co(Vehicle.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<Vehicle>fetchFor("vehicle").fetchModel(), "AA9999AA");
        final var equipment = co(Equipment.class).new_().setTitle("Pipe").setEquipmentType(equipmentType).setVehicle(vehicle).setDesc("A tube for conveying fluid substances.");
        assertEquals("Pipe", equipment.getTitle());
        assertEquals(equipmentType, equipment.getEquipmentType());
        assertEquals(vehicle, equipment.getVehicle());
        final var savedEquipment = co(Equipment.class).save(equipment);
        assertNotNull(savedEquipment);
        assertTrue(savedEquipment.isActive());
        assertEquals("Pipe", savedEquipment.getTitle());
        assertEquals(equipmentType, savedEquipment.getEquipmentType());
        assertEquals(vehicle, savedEquipment.getVehicle());
    }

    @Test
    public void new_equipment_gets_its_number_generated() {
        final var equipmentType = co(EquipmentType.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<EquipmentType>fetchFor("equipmentType").fetchModel(), "1m hose pipe");
        final var vehicle = co(Vehicle.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<Vehicle>fetchFor("vehicle").fetchModel(), "AA9999AA");
        final var equipment = co(Equipment.class).new_().setTitle("Pipe").setEquipmentType(equipmentType).setVehicle(vehicle).setDesc("A tube for conveying fluid substances.");
        assertEquals(EquipmentCo.DEFAULT_KEY_VALUE, equipment.getNumber());

        final var savedEquipment = co(Equipment.class).save(equipment);
        assertNotEquals(EquipmentCo.DEFAULT_KEY_VALUE, savedEquipment.getNumber());
        assertFalse(StringUtils.isEmpty(savedEquipment.getNumber()));
        assertEquals("1", savedEquipment.getNumber());
    }

    @Test
    public void existing_equipment_can_not_have_its_number_changed() {
        final var equipmentType = co(EquipmentType.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<EquipmentType>fetchFor("equipmentType").fetchModel(), "1m hose pipe");
        final var vehicle = co(Vehicle.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<Vehicle>fetchFor("vehicle").fetchModel(), "AA9999AA");
        final var equipment = co(Equipment.class).new_().setTitle("Pipe").setEquipmentType(equipmentType).setVehicle(vehicle).setDesc("A tube for conveying fluid substances.");

        final var savedEquipment = co(Equipment.class).save(equipment);
        savedEquipment.setNumber("2");
        assertEquals("1", savedEquipment.getNumber());
    }

    @Test
    public void title_can_not_contain_whitespaces() {
        final var equipmentType = co(EquipmentType.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<EquipmentType>fetchFor("equipmentType").fetchModel(), "1m hose pipe");
        final var vehicle = co(Vehicle.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<Vehicle>fetchFor("vehicle").fetchModel(), "AA9999AA");
        final var equipment = co(Equipment.class).new_().setTitle("Pipe").setEquipmentType(equipmentType).setVehicle(vehicle).setDesc("A tube for conveying fluid substances.");

        // No whitespace between words.
        equipment.setTitle("Pi  pe");
        final MetaProperty<String> mpTitle = equipment.getProperty("title");
        assertFalse(mpTitle.isValid());
        final Result validationResult = mpTitle.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResult.getMessage()); 
        assertEquals("Pipe", equipment.getTitle());

        //No spaces at the beginning of the title.
        equipment.setTitle(" Pipe");
        final MetaProperty<String> mpTitleStart = equipment.getProperty("title");
        assertFalse(mpTitleStart.isValid());
        final Result validationResultStart = mpTitleStart.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultStart.getMessage()); 
        assertEquals("Pipe", equipment.getTitle());

        //No spaces at the end of the title.
        equipment.setTitle("Pipe ");
        final MetaProperty<String> mpTitleEnd = equipment.getProperty("title");
        assertFalse(mpTitleEnd.isValid());
        final Result validationResultEnd = mpTitleEnd.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultEnd.getMessage()); 
        assertEquals("Pipe", equipment.getTitle());
    }

    @Test
    public void equipment_type_is_required_for_equipment() {
        final var equipmentType = co(EquipmentType.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<EquipmentType>fetchFor("equipmentType").fetchModel(), "1m hose pipe");
        final var vehicle = co(Vehicle.class).findByKeyAndFetch(EquipmentCo.FETCH_PROVIDER.<Vehicle>fetchFor("vehicle").fetchModel(), "AA9999AA");
        final var equipment = co(Equipment.class).new_().setTitle("Pipe").setVehicle(vehicle).setEquipmentType(equipmentType).setDesc("A tube for conveying fluid substances.");
        final var savedEquipment = co(Equipment.class).save(equipment);
        final var mpEquipmentType = savedEquipment.getProperty("equipmentType");  
        savedEquipment.setEquipmentType(null);
        assertFalse(mpEquipmentType.isValid());
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
        super.populateDomain();

        final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
        constants.setNow(dateTime("2019-10-01 11:30:00"));

        // If the use of saved data population script is indicated then there is no need to proceed with any further data population logic.
        if (useSavedDataPopulationScript()) {
            return;
        }

        save(new_(KeyNumber.class, EquipmentCo.EQUIPMENT_KEY_TITLE).setValue("0"));

        final var ec1 = save(new_(EquipmentClass.class).setTitle("Hose pipe").setDesc("High pressure hose for taking water to fire."));
        final var ec2 = save(new_(EquipmentClass.class).setTitle("Ladder").setDesc("Ladder for climbing up and down something."));

        save(new_(EquipmentType.class).setTitle("1m hose pipe").setDesc("1 meter long hose pipe.").setEquipmentClass(ec1));
        save(new_(EquipmentType.class).setTitle("Assault ladder").setDesc("Ladder for assaults.").setEquipmentClass(ec2));

        final VehicleType vehicleType = save(new_(VehicleType.class).setTitle("Fire truck").setDesc("A regular fire truck"));
        save(new_(Vehicle.class).setNumber("AA9999AA").setModel("MAN (TGM12.240)").setVehicleType(vehicleType));

    }

}