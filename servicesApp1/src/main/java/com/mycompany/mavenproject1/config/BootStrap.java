package com.mycompany.mavenproject1.config;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import org.eclipse.microprofile.auth.LoginConfig;

@SuppressWarnings({"EmptyClass", "SuppressionAnnotation"})
@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({"Admin", "User"})
@ApplicationPath("rest")
public class BootStrap extends javax.ws.rs.core.Application {
}
