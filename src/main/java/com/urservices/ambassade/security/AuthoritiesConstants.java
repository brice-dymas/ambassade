package com.urservices.ambassade.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String CATEGORIE_MANAGER = "ROLE_CATEGORIE_MANAGER";

    public static final String CATEGORIE_VIEWER = "ROLE_CATEGORIE_VIEWER";

    private AuthoritiesConstants() {
    }
}
