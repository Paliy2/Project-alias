package project_alias.persons;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import project_alias.roles.Role;
import project_alias.test_config.AbstractDaoTestCase;


/**
 * Basic testing of {@link PersonRole}.
 * 
 * @author Project-alias Team
 *
 */
public class PersonRoleTest extends AbstractDaoTestCase {

    @Test
    public void role_can_be_created_and_saved() {
    	final var person = co(Person.class).new_();
        person.setName("Vasyl").setSurname("Nechyporenko").setEmail("vasylnechyporenko@firemail.com").setPhoneNumber("+38 (098) 765 4321");
        final var savedPerson = co(Person.class).save(person);
    	
    	final var role = co(Role.class).new_();
    	role.setTitle("KerivnykZahonu").setDesc("Vidpovidae za usi stantsii v zahoni. Ye holovnym i pidporyadkovue sobi usih inshyhk pratsivnykiv stantsii.");
    	final var savedRole = co(Role.class).save(role);
        
        final var date = new Date();
    	
        final var personRole = co(PersonRole.class).new_();
        personRole.setPerson(savedPerson).setAssignedRole(savedRole).setAssignedDate(date);
        
    	assertEquals(date, personRole.getAssignedDate());
    	assertEquals(savedPerson, personRole.getPerson());
    	assertEquals(savedRole, personRole.getAssignedRole());
    	final var savedPersonRole = co(PersonRole.class).save(personRole);
    	assertNotNull(savedPersonRole);
    	assertEquals(date, savedPersonRole.getAssignedDate());
    	assertEquals(savedPerson, personRole.getPerson());
    	assertEquals(savedRole, personRole.getAssignedRole());
    }

    @Test
    public void role_is_required_for_person_role() {
    	final var person = co(Person.class).new_();
        person.setName("Vasyl").setSurname("Nechyporenko").setEmail("vasylnechyporenko@firemail.com").setPhoneNumber("+38 (098) 765 4321");
        final var savedPerson = co(Person.class).save(person);
    	
    	final var role = co(Role.class).new_();
    	role.setTitle("KerivnykZahonu").setDesc("Vidpovidae za usi stantsii v zahoni. Ye holovnym i pidporyadkovue sobi usih inshyhk pratsivnykiv stantsii.");
    	final var savedRole = co(Role.class).save(role);
        
        final var date = new Date();
    	
        final var personRole = co(PersonRole.class).new_();
        personRole.setPerson(savedPerson).setAssignedRole(savedRole).setAssignedDate(date);
        final var savedPersonRole = co(PersonRole.class).save(personRole);
        final var personRoleRole = savedPersonRole.getProperty("assignedRole");
        
        savedPersonRole.setAssignedRole(null);
        assertFalse(personRoleRole.isValid());
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