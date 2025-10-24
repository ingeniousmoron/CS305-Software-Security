package com.snhu.sslserver;

import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpToHttpsConfig { //Configuration class to redirect http to https automatically
    @Bean
    public WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> containerCustomizer() {
        return factory -> ((TomcatServletWebServerFactory) factory).addAdditionalTomcatConnectors(httpToHttpsRedirectConnector()); //create a tomcat server factory
    }

    private org.apache.catalina.connector.Connector httpToHttpsRedirectConnector() {
        org.apache.catalina.connector.Connector connector = new org.apache.catalina.connector.Connector(org.apache.coyote.http11.Http11NioProtocol.class.getName());
        connector.setScheme("http");
        connector.setPort(8080); //initial http port
        connector.setSecure(false);
        connector.setRedirectPort(8443); //redirect to https port
        return connector;
    }
}