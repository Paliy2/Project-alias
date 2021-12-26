package project_alias.form_items;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import project_alias.test_config.AbstractDaoTestCase;
import project_alias.validators.NoWhiteSpacesValidator;


/**
 * Basic testing of {@link FormTypeItem}.
 * 
 * @author Project-alias Team
 *
 */
public class FormTypeItemTest extends AbstractDaoTestCase {

    @Test
    public void form_type_item_can_be_created_and_saved() {
    	final var fti = co(FormTypeItem.class).new_();
    	fti.setTitle("CheckEngine").setDesc("Worker is obliged to check the engine of the vehicle he is attached to.");
    	
    	assertEquals("CheckEngine", fti.getTitle());
    	assertEquals("Worker is obliged to check the engine of the vehicle he is attached to.", fti.getDesc());
    	final var savedFti = co(FormTypeItem.class).save(fti);
    	assertNotNull(savedFti);
    	assertEquals("CheckEngine", savedFti.getTitle());
    	assertEquals("Worker is obliged to check the engine of the vehicle he is attached to.", savedFti.getDesc());
    }

    @Test
    public void form_type_item_title_can_not_contain_whitespaces() {
    	final var fti = co(FormTypeItem.class).new_();
    	fti.setTitle("CheckEngine").setDesc("Worker is obliged to check the engine of the vehicle he is attached to.");
    	
    	// No whitespace between words.
    	fti.setTitle("Check  Engine");
        final MetaProperty<String> ftiTitle = fti.getProperty("title");
        assertFalse(ftiTitle.isValid());
        final Result validationResult = ftiTitle.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResult.getMessage()); 
        assertEquals("CheckEngine", fti.getTitle());

        //No spaces at the beginning of the title.
        fti.setTitle(" CheckEngine");
        final MetaProperty<String> ftiTitleStart = fti.getProperty("title");
        assertFalse(ftiTitleStart.isValid());
        final Result validationResultStart = ftiTitleStart.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultStart.getMessage()); 
        assertEquals("CheckEngine", fti.getTitle());

        //No spaces at the end of the title.
        fti.setTitle("CheckEngine ");
        final MetaProperty<String> ftiTitleEnd = fti.getProperty("title");
        assertFalse(ftiTitleEnd.isValid());
        final Result validationResultEnd = ftiTitleEnd.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultEnd.getMessage()); 
        assertEquals("CheckEngine", fti.getTitle());
    }

    @Test
    public void title_is_required_for_form_type_item() {
    	final var fti = co(FormTypeItem.class).new_();
    	fti.setTitle("CheckEngine").setDesc("Worker is obliged to check the engine of the vehicle he is attached to.");
    	final var savedFti = co(FormTypeItem.class).save(fti);
        final var ftiTitle = savedFti.getProperty("title"); 
    	
    	savedFti.setTitle(null);
    	assertFalse(ftiTitle.isValid());
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