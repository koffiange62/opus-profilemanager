package ci.jubile.joc.opusprofilemanager.enumeration;

import lombok.Getter;
import lombok.Setter;

public enum CompetenceType {
    PRO("Aptitude Professionnelle"),
    TALENT("Talent");

    @Getter @Setter
    private final String libelle;

    CompetenceType(String libelle) {
        this.libelle = libelle;
    }
}
