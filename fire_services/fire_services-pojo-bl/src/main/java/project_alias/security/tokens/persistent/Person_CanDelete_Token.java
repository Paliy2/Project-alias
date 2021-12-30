package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.persons.Person;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link Person} to guard Delete.
 *
 * @author Project-alias team
 *
 */
public class Person_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Person.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Person.ENTITY_TITLE);
}