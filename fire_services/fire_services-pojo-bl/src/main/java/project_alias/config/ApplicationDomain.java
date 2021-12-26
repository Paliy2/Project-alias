package project_alias.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import project_alias.personnel.Person;
import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.domain.PlatformDomainTypes;
import ua.com.fielden.platform.entity.AbstractEntity;
import project_alias.equipments.EquipmentClass;
import project_alias.forms.Status;
import project_alias.vehicles.VehicleType;
import project_alias.equipments.EquipmentType;
import project_alias.equipments.Equipment;
import project_alias.vehicles.Vehicle;
import project_alias.roles.Role;
import project_alias.equipments.ui_actions.OpenEquipmentClassMasterAction;
import project_alias.equipments.master.menu.actions.EquipmentClassMaster_OpenMain_MenuItem;
import project_alias.equipments.master.menu.actions.EquipmentClassMaster_OpenEquipmentType_MenuItem;
import project_alias.form_items.FormTypeItem;

/**
 * A class to register domain entities.
 * 
 * @author Generated
 * 
 */
public class ApplicationDomain implements IApplicationDomainProvider {
	private static final Set<Class<? extends AbstractEntity<?>>> entityTypes = new LinkedHashSet<>();
	private static final Set<Class<? extends AbstractEntity<?>>> domainTypes = new LinkedHashSet<>();

	static {
		entityTypes.addAll(PlatformDomainTypes.types);
		add(Person.class);
		add(EquipmentClass.class);
		add(Status.class);
		add(VehicleType.class);
		add(EquipmentType.class);
		add(Equipment.class);
		add(Vehicle.class);
		add(Role.class);
		add(OpenEquipmentClassMasterAction.class);
		add(EquipmentClassMaster_OpenMain_MenuItem.class);
		add(EquipmentClassMaster_OpenEquipmentType_MenuItem.class);
		add(FormTypeItem.class);
	}

	private static void add(final Class<? extends AbstractEntity<?>> domainType) {
		entityTypes.add(domainType);
		domainTypes.add(domainType);
	}

	@Override
	public List<Class<? extends AbstractEntity<?>>> entityTypes() {
		return Collections.unmodifiableList(entityTypes.stream().collect(Collectors.toList()));
	}

	public List<Class<? extends AbstractEntity<?>>> domainTypes() {
		return Collections.unmodifiableList(domainTypes.stream().collect(Collectors.toList()));
	}
}