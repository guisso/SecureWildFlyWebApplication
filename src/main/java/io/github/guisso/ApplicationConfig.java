package io.github.guisso;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Named
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:/SecureDS",
        callerQuery = "select user_password from users "
        + "where username = ?",
        groupsQuery = "select user_group from users "
        + "where username = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = {
            "Pbkdf2PasswordHash.Iterations=3071",
            "${applicationConfig.dyna}"
        }
)
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "",
                useForwardToLogin = false
        )
)
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {

    public String[] getDyna() {
        return new String[]{
            "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", 
            "Pbkdf2PasswordHash.SaltSizeBytes=64"};
    }
}
