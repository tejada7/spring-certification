module spring.certification {
    requires spring.context;
    requires spring.beans;
    requires spring.core;
//    requires jakarta.jakartaee.api;
    requires javaee.api;
    opens com.ftm.vcp.beanmode.config;
    opens com.ftm.vcp.callback;
    opens com.ftm.vcp.profile;
    opens com.ftm.vcp.properties;
    opens com.ftm.vcp.resources;
    opens resource;
}
