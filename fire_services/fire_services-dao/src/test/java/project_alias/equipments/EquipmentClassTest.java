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
 * Basic testing of {@link EquipmentClass}.
 * 
 * @author Project-alias Team
 *
 */
public class EquipmentClassTest extends AbstractDaoTestCase {

    @Test
    public void equipment_class_can_be_created_and_saved() {
        final var equipmentClass = co(EquipmentClass.class).new_().setTitle("Hose pipe").setDesc("High pressure hose for taking water to fire.");
        assertEquals("Hose pipe", equipmentClass.getTitle());
        final var savedEquipmentClass = co(EquipmentClass.class).save(equipmentClass);
        assertNotNull(savedEquipmentClass);
        assertTrue(savedEquipmentClass.isActive());
        assertEquals("Hose pipe", savedEquipmentClass.getTitle());
    }

    @Test
    public void title_can_not_contain_whitespaces() {
        final var equipmentClass = co(EquipmentClass.class).new_().setTitle("Hose pipe").setDesc("High pressure hose for taking water to fire.");

        // No whitespace between words.
        equipmentClass.setTitle("Hose  pipe");
        final MetaProperty<String> mpTitle = equipmentClass.getProperty("title");
        assertFalse(mpTitle.isValid());
        final Result validationResult = mpTitle.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResult.getMessage()); 
        assertEquals("Hose pipe", equipmentClass.getTitle());

        //No spaces at the beginning of the title.
        equipmentClass.setTitle(" Hose pipe");
        final MetaProperty<String> mpTitleStart = equipmentClass.getProperty("title");
        assertFalse(mpTitleStart.isValid());
        final Result validationResultStart = mpTitleStart.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultStart.getMessage()); 
        assertEquals("Hose pipe", equipmentClass.getTitle());

        //No spaces at the end of the title.
        equipmentClass.setTitle("Hose pipe ");
        final MetaProperty<String> mpTitleEnd = equipmentClass.getProperty("title");
        assertFalse(mpTitleEnd.isValid());
        final Result validationResultEnd = mpTitleEnd.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultEnd.getMessage()); 
        assertEquals("Hose pipe", equipmentClass.getTitle());
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