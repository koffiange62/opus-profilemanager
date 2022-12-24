package ci.jubile.joc.opusprofilemanager.v1.enumeration;

import lombok.Getter;
import lombok.Setter;

public enum CompetenceType {
    PRO("Aptitude Professionnelle"),
    TALENT("Talent");

    @Getter
    private final String libelle;

    CompetenceType(String libelle) {
        this.libelle = libelle;
    }
}
