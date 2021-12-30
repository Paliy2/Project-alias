package project_alias.persons;

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
import project_alias.test_config.AbstractDaoTestCase;
import project_alias.validators.NoWhiteSpacesValidator;
import project_alias.validators.PhoneNumberValidator;


/**
 * Basic testing of {@link Vehicle}
 * 
 * @author Project-alias Team
 *
 */
public class PersonTest extends AbstractDaoTestCase {

    /**
     * The names of the test method should be informative. 
     * It is recommended to make the method name sound like a sentence stating the expected behaviour.
     * In this case, the test method name indicates that it is expected to find person with initials RDM and that it has an active status.
     * <p> 
     * Each test method should be related to exactly one concern, which facilitates creation of unit tests that address a single concern.
     */
    @Test
    public void person_can_be_created_and_saved() {
        final var person = co(Person.class).new_();
        person.setName("Vasyl").setSurname("Nechyporenko").setEmail("vasylnechyporenko@firemail.com").setPhoneNumber("+38 (098) 765 4321");

        final var savedPerson = co(Person.class).save(person);
        assertNotNull(savedPerson);
        assertEquals("Vasyl", savedPerson.getName());
        assertEquals("Nechyporenko", savedPerson.getSurname());
        assertEquals("vasylnechyporenko@firemail.com", savedPerson.getEmail());
        assertEquals("+38 (098) 765 4321", savedPerson.getPhoneNumber());
    }
    
    @Test
    public void name_can_not_contain_whitespaces() {
    	final var person = co(Person.class).new_();
    	person.setName("Vasyl").setSurname("Nechyporenko").setEmail("vasylnechyporenko@firemail.com").setPhoneNumber("+38 (098) 765 4321");
    	
    	// No whitespace between words.
    	person.setName("Vasyl  Petro");
        final MetaProperty<String> personName = person.getProperty("name");
        assertFalse(personName.isValid());
        final Result validationResult = personName.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResult.getMessage()); 
        assertEquals("Vasyl", person.getName());

        //No spaces at the beginning of the title.
        person.setName(" Vasyl");
        final MetaProperty<String> personStartName = person.getProperty("name");
        assertFalse(personStartName.isValid());
        final Result validationResultStart = personStartName.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultStart.getMessage()); 
        assertEquals("Vasyl", person.getName());

        //No spaces at the end of the title.
        person.setName("Vasyl ");
        final MetaProperty<String> personEndName = person.getProperty("name");
        assertFalse(personEndName.isValid());
        final Result validationResultEnd = personEndName.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultEnd.getMessage()); 
        assertEquals("Vasyl", person.getName());
    }
    
    @Test
    public void surname_can_not_contain_whitespaces() {
    	final var person = co(Person.class).new_();
    	person.setName("Vasyl").setSurname("Nechyporenko").setEmail("vasylnechyporenko@firemail.com").setPhoneNumber("+38 (098) 765 4321");
    	
    	// No whitespace between words.
    	person.setSurname("Nechyporenko  Batih");
        final MetaProperty<String> personSurname = person.getProperty("surname");
        assertFalse(personSurname.isValid());
        final Result validationResult = personSurname.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResult.getMessage()); 
        assertEquals("Nechyporenko", person.getSurname());

        //No spaces at the beginning of the title.
        person.setSurname(" Nechyporenko");
        final MetaProperty<String> personStartSurname = person.getProperty("surname");
        assertFalse(personStartSurname.isValid());
        final Result validationResultStart = personStartSurname.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultStart.getMessage()); 
        assertEquals("Nechyporenko", person.getSurname());

        //No spaces at the end of the title.
        person.setSurname("Nechyporenko ");
        final MetaProperty<String> personEndSurname = person.getProperty("surname");
        assertFalse(personEndSurname.isValid());
        final Result validationResultEnd = personEndSurname.getFirstFailure();
        assertEquals(NoWhiteSpacesValidator.ERR_CONTAINS_WHITESPACES, validationResultEnd.getMessage()); 
        assertEquals("Nechyporenko", person.getSurname());
    }
  
    @Test
    public void phone_number_should_be_in_the_specified_format() {
    	final var person = co(Person.class).new_();
    	person.setName("Vasyl").setSurname("Nechyporenko").setEmail("vasylnechyporenko@firemail.com").setPhoneNumber("+38 (098) 765 4321");
    	
    	person.setPhoneNumber("0996210002");
        final MetaProperty<String> mpNumber = person.getProperty("phoneNumber");
        assertFalse(mpNumber.isValid());
        
        final Result validationResult = mpNumber.getFirstFailure();
        assertEquals(PhoneNumberValidator.ERR_INCORRECT_NUMBER_FORMAT, validationResult.getMessage());
        assertEquals("+38 (098) 765 4321", person.getPhoneNumber());
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
