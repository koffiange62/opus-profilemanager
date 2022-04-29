package ci.jubile.joc.opusprofilemanager.domain;

import ci.jubile.joc.opusprofilemanager.enumeration.CompetenceType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

public class Competence extends model{
    @Getter @Setter
    private String action;
    @Getter @Setter
    private String outil;
    @Getter @Setter
    private CompetenceType competenceType;

    @PersistenceConstructor
    public Competence(String id, String action, String outil, CompetenceType competenceType) {
        this.setId(id);
        this.action = action;
        this.outil = outil;
        this.competenceType = competenceType;
    }

    @Override
    public String toString() {
        return "Competence{" +
                "action='" + action + '\'' +
                ", outil='" + outil + '\'' +
                ", competenceType=" + competenceType +
                '}';
    }
}
