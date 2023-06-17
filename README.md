# SecureWildFlyWebApplication
A web application with security restrictions (admin/user), in addition to user registration by the administrator.

## Important!

WildFly requires Elytron and Java Authentication Service Provider Interface (SPI) for Containers (JASPI) activation.

If the project doesn't work, follow the steps int the ``jboss-cli``:

- ``/subsystem=elytron/policy=jacc:add(jacc-policy={})``
    > Enable a default JACC policy with WildFly Elytron
- ``/subsystem=undertow/application-security-domain=other:write-attribute(name=integrated-jaspi, value=false)``
    > Map the default (``other``) security domain to WildFly Elytron
- ``:reload``
    > Reload the settings

References

[8. Elytron and Java Authentication SPI for Containers (JASPI)](https://docs.wildfly.org/26.1/WildFly_Elytron_Security.html#Elytron_and_Java_Authentication_SPI_for_Containers-JASPI)
[The ee-security quickstart does not work with WildFly 25](https://issues.redhat.com/browse/WFLY-15671)

## Important!
This repository is an adaptation of [Sample Secured JSF/JPA Web Application](https://github.com/fturizo/SecureWebApplication).
> This repository contains the code for this [short tutorial](https://www.youtube.com/watch?v=odJIoyFeAbk) on how to create a secure web application using multiple APIs in the Jakarta EE specification and how to deploy this application on Payara Server 5.x
