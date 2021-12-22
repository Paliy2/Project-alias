package project_alias.equipments;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.Equipment_CanSave_Token;
import project_alias.security.tokens.persistent.Equipment_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.keygen.IKeyNumber;
import ua.com.fielden.platform.keygen.KeyNumber;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link EquipmentCo}.
 *
 * @author Developers
 *
 */
@EntityType(Equipment.class)
public class EquipmentDao extends CommonEntityDao<Equipment> implements EquipmentCo {

    @Inject
    public EquipmentDao(final IFilter filter) {
        super(filter);
    }

    @Override
    public Equipment new_() {
        return super.new_().setActive(true).setNumber(DEFAULT_KEY_VALUE);
    }

    @Override
    @SessionRequired
    @Authorise(Equipment_CanSave_Token.class)
    public Equipment save(final Equipment equipment) {
        final boolean wasPersisted = equipment.isPersisted();

        if (!wasPersisted) {
            final IKeyNumber keyNumberCo = co(KeyNumber.class);
            final String nextNumber = keyNumberCo.nextNumber(EQUIPMENT_KEY_TITLE).toString();
            equipment.setNumber(nextNumber);
        }

        try {
            return super.save(equipment); 
        } catch(final Exception ex) {
            if (!wasPersisted) {
                equipment.beginInitialising();
                equipment.setNumber(DEFAULT_KEY_VALUE);
                equipment.endInitialising();
            }
            throw ex;
        }
    }

    @Override
    @SessionRequired
    @Authorise(Equipment_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Equipment_CanDelete_Token.class)
    public int batchDelete(final List<Equipment> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Equipment> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}