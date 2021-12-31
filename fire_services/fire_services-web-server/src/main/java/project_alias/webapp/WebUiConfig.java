package project_alias.webapp;

import static ua.com.fielden.platform.reflection.TitlesDescsGetter.getEntityTitleAndDesc;

import org.apache.commons.lang3.StringUtils;

import project_alias.config.Modules;
import project_alias.equipments.Equipment;
import project_alias.equipments.EquipmentClass;
import project_alias.equipments.EquipmentType;
import project_alias.form_items.FormItem;
import project_alias.form_items.FormTypeItem;
import project_alias.forms.Status;
import project_alias.forms.FormType;
import project_alias.persons.Person;
import project_alias.roles.Role;
import project_alias.vehicles.Vehicle;
import project_alias.vehicles.VehicleType;
import project_alias.webapp.config.equipments.EquipmentClassWebUiConfig;
import project_alias.webapp.config.equipments.EquipmentTypeWebUiConfig;
import project_alias.webapp.config.equipments.EquipmentWebUiConfig;
import project_alias.webapp.config.form_items.FormItemWebUiConfig;
import project_alias.webapp.config.form_items.FormTypeItemWebUiConfig;
import project_alias.webapp.config.forms.FormTypeWebUiConfig;
import project_alias.webapp.config.forms.StatusWebUiConfig;
import project_alias.webapp.config.persons.PersonWebUiConfig;
import project_alias.webapp.config.roles.RoleWebUiConfig;
import project_alias.webapp.config.vehicles.VehicleTypeWebUiConfig;
import project_alias.webapp.config.vehicles.VehicleWebUiConfig;
import ua.com.fielden.platform.basic.config.Workflows;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.resources.webui.AbstractWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.SecurityMatrixWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserRoleWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserWebUiConfig;

/**
 * App-specific {@link IWebApp} implementation.
 *
 * @author Generated
 *
 */
public class WebUiConfig extends AbstractWebUiConfig {

    public static final String WEB_TIME_WITH_MILLIS_FORMAT = "HH:mm:ss.SSS";
    public static final String WEB_TIME_FORMAT = "HH:mm";
    public static final String WEB_DATE_FORMAT_JS = "DD/MM/YYYY";
    public static final String WEB_DATE_FORMAT_JAVA = fromJsToJavaDateFormat(WEB_DATE_FORMAT_JS);

    private final String domainName;
    private final String path;
    private final int port;

    public WebUiConfig(final String domainName, final int port, final Workflows workflow, final String path) {
        super("Fire services", workflow, new String[] { "project_alias/" });
        if (StringUtils.isEmpty(domainName) || StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Both the domain name and application binding path should be specified.");
        }
        this.domainName = domainName;
        this.port = port;
        this.path = path;
    }


    @Override
    public String getDomainName() {
        return domainName;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public int getPort() {
        return port;
    }

    /**
     * Configures the {@link WebUiConfig} with custom centres and masters.
     */
    @Override
    public void initConfiguration() {
        super.initConfiguration();

        final IWebUiBuilder builder = configApp();

        builder.setDateFormat(WEB_DATE_FORMAT_JS).setTimeFormat(WEB_TIME_FORMAT).setTimeWithMillisFormat(WEB_TIME_WITH_MILLIS_FORMAT)
        .setMinTabletWidth(600);

        // Users and Personnel Module    
        final UserWebUiConfig userWebUiConfig = UserWebUiConfig.register(injector(), builder);
        final UserRoleWebUiConfig userRoleWebUiConfig = UserRoleWebUiConfig.register(injector(), builder);
        final SecurityMatrixWebUiConfig securityConfig = SecurityMatrixWebUiConfig.register(injector(), configApp());

        // Forms Module
        final StatusWebUiConfig statusWebUiConfig = StatusWebUiConfig.register(injector(), builder);
        final FormTypeWebUiConfig formTypeWebUiConfig = FormTypeWebUiConfig.register(injector(), builder);
        
        // Equipments Module
        final EquipmentClassWebUiConfig equipmentClassWebUiConfig = EquipmentClassWebUiConfig.register(injector(), builder);
        final EquipmentTypeWebUiConfig equipmentTypeWebUiConfig = EquipmentTypeWebUiConfig.register(injector(), builder);
        final EquipmentWebUiConfig equipmentWebUiConfig = EquipmentWebUiConfig.register(injector(), builder);

        // Vehicles Module
        final VehicleTypeWebUiConfig vehicleTypeWebUiConfig = VehicleTypeWebUiConfig.register(injector(), builder);
        final VehicleWebUiConfig vehicleWebUiConfig = VehicleWebUiConfig.register(injector(), builder);
        
        // Roles Module
        final RoleWebUiConfig roleWebUiConfig = RoleWebUiConfig.register(injector(), builder);
        
        // Form items Module
        final FormTypeItemWebUiConfig formTypeItemWebUiConfig = FormTypeItemWebUiConfig.register(injector(), builder);
        final FormItemWebUiConfig formItemWebUiConfig = FormItemWebUiConfig.register(injector(), builder);

        // Persons Module
        final PersonWebUiConfig personWebUiConfig = PersonWebUiConfig.register(injector(), builder);

        // Add user-rated masters and centres to the configuration 
        configApp()
        .addMaster(userWebUiConfig.master)
        .addMaster(userWebUiConfig.rolesUpdater)
        .addMaster(userRoleWebUiConfig.master)
        .addMaster(userRoleWebUiConfig.tokensUpdater)
        .addCentre(userWebUiConfig.centre)
        .addCentre(userRoleWebUiConfig.centre);

        // Configure application menu
        configDesktopMainMenu()
        .addModule(Modules.USERS_AND_PERSONNEL.title)
        .description(Modules.USERS_AND_PERSONNEL.desc)
        .icon(Modules.USERS_AND_PERSONNEL.icon)
        .detailIcon(Modules.USERS_AND_PERSONNEL.icon)
        .bgColor(Modules.USERS_AND_PERSONNEL.bgColour)
        .captionBgColor(Modules.USERS_AND_PERSONNEL.captionBgColour)
        .menu()
        .addMenuItem(mkMenuItemTitle(Status.class)).description(mkMenuItemDesc(Status.class)).centre(statusWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(FormType.class)).description(mkMenuItemDesc(FormType.class)).centre(formTypeWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(EquipmentClass.class)).description(mkMenuItemDesc(EquipmentClass.class)).centre(equipmentClassWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(VehicleType.class)).description(mkMenuItemDesc(VehicleType.class)).centre(vehicleTypeWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(EquipmentType.class)).description(mkMenuItemDesc(EquipmentType.class)).centre(equipmentTypeWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(Equipment.class)).description(mkMenuItemDesc(Equipment.class)).centre(equipmentWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(Vehicle.class)).description(mkMenuItemDesc(Vehicle.class)).centre(vehicleWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(Role.class)).description(mkMenuItemDesc(Role.class)).centre(roleWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(FormTypeItem.class)).description(mkMenuItemDesc(FormTypeItem.class)).centre(formTypeItemWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(FormItem.class)).description(mkMenuItemDesc(FormItem.class)).centre(formItemWebUiConfig.centre).done()
        .addMenuItem(mkMenuItemTitle(Person.class)).description(mkMenuItemDesc(Person.class)).centre(personWebUiConfig.centre).done()
        .addMenuItem("System Users").description("Functionality for managing system users, athorisation, etc.")
        .addMenuItem("Users").description("User centre").centre(userWebUiConfig.centre).done()
        .addMenuItem("User Roles").description("User roles centre").centre(userRoleWebUiConfig.centre).done()
        .addMenuItem("Security Matrix").description("Security Matrix is used to manage application authorisations for User Roles.").master(securityConfig.master).done()
        .done()
        .done().done()
        .setLayoutFor(Device.DESKTOP, null, "[[[]]]")
        .setLayoutFor(Device.TABLET, null, "[[[]]]")
        .setLayoutFor(Device.MOBILE, null, "[[[]]]")
        .minCellWidth(100).minCellHeight(148).done();
    }

    private static String fromJsToJavaDateFormat(final String dateFormatJs) {
        return dateFormatJs.replace("DD", "dd").replace("YYYY", "yyyy"); // UPPERCASE "Y" is "week year" in Java, therefore we prefer lowercase "y"
    }

    public static String mkMenuItemTitle(final Class<? extends AbstractEntity<?>> entityType) {
        return getEntityTitleAndDesc(entityType).getKey();
    }

    public static final String CENTRE_SUFFIX = " Centre";
    public static String mkMenuItemDesc(final Class<? extends AbstractEntity<?>> entityType) {
        final Pair<String, String> titleDesc = TitlesDescsGetter.getEntityTitleAndDesc(entityType);
        // Some @EntityTitle desc are not specified, while the others are worded as whole sentence ending with "." - use value in both cases
        return titleDesc.getValue().isEmpty() || titleDesc.getValue().endsWith(".") ? titleDesc.getKey() + CENTRE_SUFFIX : titleDesc.getValue() + CENTRE_SUFFIX;
    }

}