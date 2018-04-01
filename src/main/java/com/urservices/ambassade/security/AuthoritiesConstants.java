package com.urservices.ambassade.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String CAISSE_VIEWER = "ROLE_CAISSE_VIEWER";

    public static final String CAISSE_MANAGER = "ROLE_CAISSE_MANAGER";

    public static final String CATEGORIE_VIEWER = "ROLE_CATEGORIE_VIEWER";

    public static final String CATEGORIE_MANAGER = "ROLE_CATEGORIE_MANAGER";

    public static final String DONNEES_ACTE_VIEWER = "ROLE_DONNEES_ACTE_VIEWER";

    public static final String DONNEES_ACTE_MANAGER = "ROLE_DONNEES_ACTE_MANAGER";

    public static final String LIVRE_MANAGER = "ROLE_LIVRE_MANAGER";

    public static final String LIVRE_VIEWER = "ROLE_LIVRE_VIEWER";

    public static final String MONNAIE_MANAGER = "ROLE_MONNAIE_MANAGER";

    public static final String MONNAIE_VIEWER = "ROLE_MONNAIE_VIEWER";

    public static final String MONTANT_MANAGER = "ROLE_MONTANT_MANAGER";

    public static final String MONTANT_VIEWER = "ROLE_MONTANT_VIEWER";

    public static final String PASSEPORT_MANAGER = "ROLE_PASSEPORT_MANAGER";

    public static final String PASSEPORT_VIEWER = "ROLE_PASSEPORT_VIEWER";

    public static final String PRODUIT_MANAGER = "ROLE_PRODUIT_MANAGER";

    public static final String PRODUIT_VIEWER = "ROLE_PRODUIT_VIEWER";

    public static final String RAPATRIEMENT_MANAGER = "ROLE_RAPATRIEMENT_MANAGER";

    public static final String RAPATRIEMENT_VIEWER = "ROLE_RAPATRIEMENT_VIEWER";

    public static final String VISA_MANAGER = "ROLE_VISA_MANAGER";

    public static final String VISA_VIEWER = "ROLE_VISA_VIEWER";

    private AuthoritiesConstants() {
    }
}
