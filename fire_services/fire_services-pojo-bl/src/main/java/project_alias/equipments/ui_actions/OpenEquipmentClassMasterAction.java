package project_alias.equipments.ui_actions;

import project_alias.equipments.EquipmentClass;
import project_alias.equipments.EquipmentType;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityToOpenCompoundMaster;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Open Master Action entity object.
 *
 * @author Developers
 *
 */
@KeyType(EquipmentClass.class)
@CompanionObject(OpenEquipmentClassMasterActionCo.class)
@EntityTitle("Equipment Class Master")
public class OpenEquipmentClassMasterAction extends AbstractFunctionalEntityToOpenCompoundMaster<EquipmentClass> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(OpenEquipmentClassMasterAction.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    public static final String MAIN = "Main";
    public static final String EQUIPMENTTYPES = EquipmentType.ENTITY_TITLE + "s"; // Please adjust manually if the plural form is not standard
}
