package project_alias.vehicles;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.VehicleType_CanSave_Token;
import project_alias.security.tokens.persistent.VehicleType_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link VehicleTypeCo}.
 *
 * @author Developers
 *
 */
@EntityType(VehicleType.class)
public class VehicleTypeDao extends CommonEntityDao<VehicleType> implements VehicleTypeCo {

	@Inject
	public VehicleTypeDao(final IFilter filter) {
		super(filter);
	}

	@Override
	public VehicleType new_() {
		return super.new_().setActive(true);
	}

	@Override
	@SessionRequired
	@Authorise(VehicleType_CanSave_Token.class)
	public VehicleType save(VehicleType entity) {
		return super.save(entity);
	}

	@Override
	@SessionRequired
	@Authorise(VehicleType_CanDelete_Token.class)
	public int batchDelete(final Collection<Long> entitiesIds) {
		return defaultBatchDelete(entitiesIds);
	}

	@Override
	@SessionRequired
	@Authorise(VehicleType_CanDelete_Token.class)
	public int batchDelete(final List<VehicleType> entities) {
		return defaultBatchDelete(entities);
	}

	@Override
	protected IFetchProvider<VehicleType> createFetchProvider() {
		return FETCH_PROVIDER;
	}
}