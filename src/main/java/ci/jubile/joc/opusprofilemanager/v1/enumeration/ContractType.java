package ci.jubile.joc.opusprofilemanager.v1.enumeration;

import lombok.Getter;
import lombok.Setter;

public enum ContractType {
    STAGE("Stage"),
    CDD("Contrat à Durée Déterminée"),
    CDI("Contrat à Durée Indéterminée");

    @Getter
    private String libelle;

    ContractType(String libelle) {
        this.libelle = libelle;
    }
}
