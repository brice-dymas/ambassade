package com.urservices.ambassade.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "fr";
    public static final Long UNITE_ORGANISATIONNELLE_VISA = 1L;
    public static final Long UNITE_ORGANISATIONNELLE_PASSEPORT = 2L;

    private Constants() {
    }
}
