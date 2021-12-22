package project_alias.equipments;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import project_alias.test_config.AbstractDaoTestCase;
import project_alias.validators.NoWhiteSpacesValidator;


/**
 * Basic testing of {@link EquipmentType}.
 * 
 * @author Project-alias Team
 *
 */
public class EquipmentTypeTest extends AbstractDaoTestCase {

    @Test
    public void equipment_type_can_be_created_and_saved() {
        final var equipmentClass = save(new_(EquipmentClass.class).setTitle("Hose pipe").setDesc("High pressure hose for taking water to fire."));
        final var equipmentType = co(EquipmentType.class).new_().setTitle("1m hose pipe").setDesc("1 meter long hose pipe.").setEquipmentClass(equipmentClass);
        assertEquals("1m hose pipe", equipmentType.getTitle());
        assertEquals(equipmentClass, equipmentType.getEquipmentClass());
        final var savedEquipmentType = co(EquipmentType.class).save(equipmentType);
        assertNotNull(savedEquipmentType);
        assertTrue(savedEquipmentType.isActive());
        assertEquals("1m hose pipe", savedEquipmentType.getTitle());
        assertEquals(equipmentClass, savedEquipmentType.getEquipmentClass());
    }

    @Test
    public void equipment_class_is_reuquired_for_equipment_type() {
        final var equipmentClass = save(new_(EquipmentClass.class).setTitle("Hose pipe").setDesc("High pressure hose for taking water to fire."));
        final var equipmentType = co(EquipmentType.class).new_().setTitle("1m hose pipe").setDesc("1 meter long hose pipe.").setEquipmentClass(equipmentClass);
        final var savedEquipmentType = co(EquipmentType.class).save(equipmentType);
        final var mpEquipmentClass = savedEquipmentType.getProperty("equipmentClass");  
        savedEquipmentType.setEquipmentClass(null);
        assertFalse(mpEquipmentClass.isValid());
    }

    @Test
    public void title_can_not_contain_whitespaces() {
        final var equipmentClass = save(new_(EquipmentClass.class).setTitle("Hose pipe").setDesc("High pressure hose for taking water to fire."));
        final var equipmentType = co(EquipmentType.class).new_().setTitle("1m hose pipe").setDesc("1 meter long hose pipe.").setEquipmentClass(equipmentClass);

        // No whitespace between words.
        equipmentType.setTitle("1m  hose pipe");
        final MetaProperty<String> mpTitle = equipmentType.getProperty("title");
        assertFalse(mpTitle.isValid());
        final Result validationResult = mpTitle.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResult.getMessage()); 
        assertEquals("1m hose pipe", equipmentType.getTitle());

        //No spaces at the beginning of the title.
        equipmentType.setTitle(" 1m hose pipe");
        final MetaProperty<String> mpTitleStart = equipmentType.getProperty("title");
        assertFalse(mpTitleStart.isValid());
        final Result validationResultStart = mpTitleStart.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultStart.getMessage()); 
        assertEquals("1m hose pipe", equipmentType.getTitle());

        //No spaces at the end of the title.
        equipmentType.setTitle("1m hose pipe ");
        final MetaProperty<String> mpTitleEnd = equipmentType.getProperty("title");
        assertFalse(mpTitleEnd.isValid());
        final Result validationResultEnd = mpTitleEnd.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultEnd.getMessage()); 
        assertEquals("1m hose pipe", equipmentType.getTitle());
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
    }

}