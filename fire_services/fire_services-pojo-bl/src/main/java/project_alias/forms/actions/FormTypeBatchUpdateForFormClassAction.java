package project_alias.forms.actions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import project_alias.forms.FormClass;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityWithCentreContext;
import ua.com.fielden.platform.entity.NoKey;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Project-alias team
 *
 */
@KeyType(NoKey.class)
@CompanionObject(FormTypeBatchUpdateForFormClassActionCo.class)
public class FormTypeBatchUpdateForFormClassAction extends AbstractFunctionalEntityWithCentreContext<NoKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(FormTypeBatchUpdateForFormClassAction.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    protected FormTypeBatchUpdateForFormClassAction() {
        setKey(NoKey.NO_KEY);
    }
    
    @IsProperty
    @Title(value="Title", desc="Extended_des")
    private FormClass formClass;
    
    @IsProperty(Long.class)
    @Title(value = "Selected forms", desc = "IDs of selected forms.")
    private Set<Long> selectedEntitiesIds = new HashSet<>();

    @Observable
    public FormTypeBatchUpdateForFormClassAction setSelectedEntityIds (final Set<Long> selectedEntitiesIds) {
        this.selectedEntitiesIds.clear();
        this.selectedEntitiesIds.addAll(selectedEntitiesIds);
        return this;
    }

    public Set<Long> getSelectedEntitiesIds () {
        return Collections.unmodifiableSet(selectedEntitiesIds);
    }
    
    @Observable
    public FormTypeBatchUpdateForFormClassAction setFormClass (final FormClass formClass) {
        this.formClass = formClass;
        return this;
    }
    
    public FormClass getFormClass () {
        return formClass;
    }
}
