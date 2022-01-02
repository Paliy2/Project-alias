package project_alias.main.menu.persons;

import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.ui.menu.MiWithConfigurationSupport;
import project_alias.persons.PersonRole;
/**
 * Main menu item representing an entity centre for {@link PersonRole}.
 *
 * @author Project-alias Team
 *
 */
@EntityType(PersonRole.class)
public class MiPersonRole extends MiWithConfigurationSupport<PersonRole> {

}
