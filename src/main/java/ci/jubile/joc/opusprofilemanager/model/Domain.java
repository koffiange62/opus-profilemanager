package ci.jubile.joc.opusprofilemanager.model;

import lombok.Getter;
import lombok.Setter;

public class Domain extends model{
    @Setter @Getter
    public String code;
    @Setter @Getter
    public String libelle;

    public Domain() {
    }

    public Domain(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return null;
    }
}
