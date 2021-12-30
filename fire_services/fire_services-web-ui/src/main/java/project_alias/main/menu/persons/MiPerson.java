package project_alias.main.menu.persons;

import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.ui.menu.MiWithConfigurationSupport;
import project_alias.persons.Person;
/**
 * Main menu item representing an entity centre for {@link Person}.
 *
 * @author Project-alias team
 *
 */
@EntityType(Person.class)
public class MiPerson extends MiWithConfigurationSupport<Person> {

}
