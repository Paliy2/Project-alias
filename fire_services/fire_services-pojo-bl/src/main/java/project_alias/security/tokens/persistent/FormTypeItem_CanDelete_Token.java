package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.form_items.FormTypeItem;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link FormTypeItem} to guard Delete.
 *
 * @author Project-alias team
 *
 */
public class FormTypeItem_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), FormTypeItem.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), FormTypeItem.ENTITY_TITLE);
}