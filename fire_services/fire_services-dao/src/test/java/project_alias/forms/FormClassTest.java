package project_alias.forms;

import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import project_alias.equipments.Equipment;
import project_alias.equipments.EquipmentCo;
import project_alias.equipments.EquipmentType;
import project_alias.persons.Person;
import project_alias.test_config.AbstractDaoTestCase;
import project_alias.vehicles.Vehicle;


/**
 * Basic testing of {@link Form}.
 * 
 * @author Project-alias Team
 *
 */
public class FormClassTest extends AbstractDaoTestCase {

    @Test
    public void form_class_can_be_created_and_saved() {
        final var person_anriy = co(Person.class).save(co(Person.class).new_().setName("Andriy").setSurname("Kryvyi").setEmail("andr.brokenq@gmail.com"));
        final var date = new Date(System.currentTimeMillis());
        final var status_appoved = co(Status.class).save(co(Status.class).new_().setTitle("Approved").setDesc("The form is approved"));

        final var fc = co(FormClass.class).new_().setNumber("1314").setDate(date).setStatus(status_appoved).setPerson(person_anriy);
        assertEquals(person_anriy, fc.getPerson());
        assertEquals(date, fc.getDate());
        assertEquals("1314", fc.getNumber());
        assertEquals(status_appoved, fc.getStatus());

        final var savedFc = co(FormClass.class).save(fc);
        assertNotNull(savedFc);
        assertEquals(person_anriy, savedFc.getPerson());
        assertEquals(date, savedFc.getDate());
        assertEquals("1314", savedFc.getNumber());
        assertEquals(status_appoved, savedFc.getStatus());
    }
    
    @Test
    public void person_is_required_for_form_class() {
        final var person_anriy = co(Person.class).save(co(Person.class).new_().setName("Andriy").setSurname("Kryvyi").setEmail("andr.brokenq@gmail.com"));
        final var date = new Date(System.currentTimeMillis());
        final var status_appoved = co(Status.class).save(co(Status.class).new_().setTitle("Approved").setDesc("The form is approved"));
        final var fc = co(FormClass.class).new_().setNumber("1314").setDate(date).setStatus(status_appoved).setPerson(person_anriy);
        
        final var savedFc = co(FormClass.class).save(fc);
        final var fcPerson = savedFc.getProperty("person"); 
        savedFc.setPerson(null);
        assertFalse(fcPerson.isValid());
    }
    
    @Test
    public void status_is_required_for_form_class() {
        final var person_anriy = co(Person.class).save(co(Person.class).new_().setName("Andriy").setSurname("Kryvyi").setEmail("andr.brokenq@gmail.com"));
        final var date = new Date(System.currentTimeMillis());
        final var status_appoved = co(Status.class).save(co(Status.class).new_().setTitle("Approved").setDesc("The form is approved"));
        final var fc = co(FormClass.class).new_().setNumber("1314").setDate(date).setStatus(status_appoved).setPerson(person_anriy);
        
        final var savedFc = co(FormClass.class).save(fc);
        final var fcStatus = savedFc.getProperty("status"); 
        savedFc.setStatus(null);
        assertFalse(fcStatus.isValid());
    }
    
    @Test
    public void existing_form_class_can_not_have_its_number_changed() {
        final var person_anriy = co(Person.class).save(co(Person.class).new_().setName("Andriy").setSurname("Kryvyi").setEmail("andr.brokenq@gmail.com"));
        final var date = new Date(System.currentTimeMillis());
        final var status_appoved = co(Status.class).save(co(Status.class).new_().setTitle("Approved").setDesc("The form is approved"));
        final var fc = co(FormClass.class).new_().setNumber("1314").setDate(date).setStatus(status_appoved).setPerson(person_anriy);
        
        final var savedFc = co(FormClass.class).save(fc);
        savedFc.setNumber("2");
        assertEquals("1314", savedFc.getNumber());
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