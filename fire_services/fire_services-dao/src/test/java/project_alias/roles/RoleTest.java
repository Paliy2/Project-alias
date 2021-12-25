package project_alias.roles;

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
 * Basic testing of {@link Role}.
 * 
 * @author Project-alias Team
 *
 */
public class RoleTest extends AbstractDaoTestCase {

    @Test
    public void role_can_be_created_and_saved() {
    	final var role = co(Role.class).new_();
    	role.setTitle("KerivnykZahonu").setDesc("Vidpovidae za usi stantsii v zahoni. Ye holovnym i pidporyadkovue sobi usih inshyhk pratsivnykiv stantsii.");
    	
    	assertEquals("KerivnykZahonu", role.getTitle());
    	assertEquals("Vidpovidae za usi stantsii v zahoni. Ye holovnym i pidporyadkovue sobi usih inshyhk pratsivnykiv stantsii.", role.getDesc());
    	final var savedRole = co(Role.class).save(role);
    	assertNotNull(savedRole);
    	assertEquals("KerivnykZahonu", savedRole.getTitle());
    	assertEquals("Vidpovidae za usi stantsii v zahoni. Ye holovnym i pidporyadkovue sobi usih inshyhk pratsivnykiv stantsii.", savedRole.getDesc());
    }

    @Test
    public void title_can_not_contain_whitespaces() {
    	final var role = co(Role.class).new_();
    	role.setTitle("KerivnykZahonu").setDesc("Vidpovidae za usi stantsii v zahoni. Ye holovnym i pidporyadkovue sobi usih inshyhk pratsivnykiv stantsii.");
    	
    	// No whitespace between words.
    	role.setTitle("Kerivnyk  Zahonu");
        final MetaProperty<String> roleTitle = role.getProperty("title");
        assertFalse(roleTitle.isValid());
        final Result validationResult = roleTitle.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResult.getMessage()); 
        assertEquals("KerivnykZahonu", role.getTitle());

        //No spaces at the beginning of the title.
        role.setTitle(" KerivnykZahonu");
        final MetaProperty<String> roleTitleStart = role.getProperty("title");
        assertFalse(roleTitleStart.isValid());
        final Result validationResultStart = roleTitleStart.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultStart.getMessage()); 
        assertEquals("KerivnykZahonu", role.getTitle());

        //No spaces at the end of the title.
        role.setTitle("KerivnykZahonu ");
        final MetaProperty<String> roleTitleEnd = role.getProperty("title");
        assertFalse(roleTitleEnd.isValid());
        final Result validationResultEnd = roleTitleEnd.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultEnd.getMessage()); 
        assertEquals("KerivnykZahonu", role.getTitle());
    }

    @Test
    public void title_is_required_for_role() {
    	final var role = co(Role.class).new_();
    	role.setTitle("KerivnykZahonu").setDesc("Vidpovidae za usi stantsii v zahoni. Ye holovnym i pidporyadkovue sobi usih inshyhk pratsivnykiv stantsii.");
    	final var savedRole = co(Role.class).save(role);
        final var roleTitle = savedRole.getProperty("title"); 
    	
    	savedRole.setTitle(null);
    	assertFalse(roleTitle.isValid());
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