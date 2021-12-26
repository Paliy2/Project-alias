package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.form_items.FormTypeItem;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link FormTypeItem} to guard Save.
 *
 * @author Project-alias team
 *
 */
public class FormTypeItem_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), FormTypeItem.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), FormTypeItem.ENTITY_TITLE);
}