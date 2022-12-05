module spring.certification {
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.expression;
    requires jakarta.jakartaee.api;
    requires org.aspectj.weaver;
    requires spring.aop;
    requires spring.plugin.core;
    requires java.logging;
    // requires spring.tr
    opens com.ftm.vcp.beanmode.config;
    opens com.ftm.vcp.beanmode;
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
    opens com.ftm.vcp.plugin;
    opens com.ftm.vcp.plugin.domain;
    opens com.ftm.vcp.plugin.infra;
    opens com.ftm.vcp.plugin.infra.db;
    opens com.ftm.vcp.plugin.infra.http;
    opens com.ftm.vcp.events;
}
