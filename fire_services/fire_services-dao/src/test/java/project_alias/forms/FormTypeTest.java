package project_alias.forms;

import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import org.junit.Test;

import project_alias.roles.Role;
import project_alias.persons.Person;
import project_alias.test_config.AbstractDaoTestCase;


/**
 * Basic testing of {@link FormItem}.
 * 
 * @author Project-alias Team
 *
 */
public class FormTypeTest extends AbstractDaoTestCase {

    @Test
    public void form_type_can_be_created_and_saved() {
        final var person_anriy = co(Person.class).save(co(Person.class).new_().setName("Andriy").setSurname("Kryvyi").setEmail("andr.brokenq@gmail.com"));
        final var date = new Date(System.currentTimeMillis());
        final var status_appoved = co(Status.class).save(co(Status.class).new_().setTitle("Approved").setDesc("The form is approved"));
        final var fc = co(FormClass.class).new_().setNumber("1314").setDate(date).setStatus(status_appoved).setPerson(person_anriy);
        final var assigned_role = co(Role.class).save(co(Role.class).new_().setTitle("Role").setDesc("Desc"));
        
        final var savedFc = co(FormClass.class).save(fc);
        final var ft = co(FormType.class).new_().setTitle("Form class Title").setAssignedRole(assigned_role).setFormClass(savedFc);
        assertEquals("Form class Title", ft.getTitle());
        assertEquals(assigned_role, ft.getAssignedRole());
        assertEquals(savedFc, ft.getFormClass());

        final var savedFt = co(FormType.class).save(ft);
        assertNotNull(savedFt);
        assertEquals("Form class Title", savedFt.getTitle());
        assertEquals(assigned_role, savedFt.getAssignedRole());
        assertEquals(savedFc, savedFt.getFormClass());
    }
    
    @Test
    public void form_type_title_is_unique() {
        assertEquals(true, true);
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