module spring.certification {
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.expression;
    requires jakarta.jakartaee.api;
    requires org.aspectj.weaver;
    requires spring.aop;
    opens com.ftm.vcp.beanmode.config;
    opens com.ftm.vcp.callback;
    opens com.ftm.vcp.profile;
    opens com.ftm.vcp.properties;
    opens com.ftm.vcp.resources;
    opens resource;
    opens com.ftm.vcp.spel;
    opens com.ftm.vcp.spelbean;
    opens spelbean;
    opens com.ftm.vcp.aop.config;
    opens com.ftm.vcp.aop.aspect;
    opens com.ftm.vcp.aop;
    opens com.ftm.vcp.factorybeans;
}
