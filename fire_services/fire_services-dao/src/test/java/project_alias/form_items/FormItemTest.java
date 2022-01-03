package project_alias.form_items;

import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import project_alias.test_config.AbstractDaoTestCase;


/**
 * Basic testing of {@link FormItem}.
 * 
 * @author Project-alias Team
 *
 */
public class FormItemTest extends AbstractDaoTestCase {

    @Test
    public void form_item_can_be_created_and_saved() {
        final var fti = co(FormTypeItem.class).new_();
        fti.setTitle("CheckEngine").setDesc("Worker is obliged to check the engine of the vehicle he is attached to.");
        final var fi = co(FormItem.class).new_();
        final var savedFti = co(FormTypeItem.class).save(fti);
        fi.setFormTypeItem(savedFti).setAccepted(true);
        assertEquals(savedFti, fi.getFormTypeItem());
        assertEquals(true, fi.getAccepted());
        
        final var savedFi = co(FormItem.class).save(fi);
        assertNotNull(savedFi);
        assertEquals(savedFti, savedFi.getFormTypeItem());
        assertEquals(true, savedFi.getAccepted());
    }
    
    @Test
    public void form_accepted_status_can_be_updated() {
        final var fti = co(FormTypeItem.class).new_();
        fti.setTitle("CheckEngine").setDesc("Worker is obliged to check the engine of the vehicle he is attached to.");
        final var fi = co(FormItem.class).new_();
        final var savedFti = co(FormTypeItem.class).save(fti);
        fi.setFormTypeItem(savedFti).setAccepted(true);        
        final var savedFi = co(FormItem.class).save(fi);
        assertNotNull(savedFi);
        assertEquals(true, savedFi.getAccepted());
        savedFi.setAccepted(false);
        assertEquals(false, savedFi.getAccepted());
    }
    
    @Test
    public void form_type_item_is_required_for_form_item() {
        final var fti = co(FormTypeItem.class).new_();
        fti.setTitle("CheckEngine").setDesc("Worker is obliged to check the engine of the vehicle he is attached to.");
        final var savedFti = co(FormTypeItem.class).save(fti);
        
        final var saved_fi = co(FormItem.class).save(co(FormItem.class).new_().setFormTypeItem(savedFti).setAccepted(false));
        final var fiFormType = saved_fi.getProperty("formTypeItem"); 
        saved_fi.setFormTypeItem(null);
        assertFalse(fiFormType.isValid());
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