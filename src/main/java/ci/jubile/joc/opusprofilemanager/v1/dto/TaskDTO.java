package ci.jubile.joc.opusprofilemanager.v1.dto;

import ci.jubile.joc.opusprofilemanager.domain.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

public class TaskDTO extends model {
    @Getter @Setter
    private String libelle;
    @Getter @Setter
    private String outil;

    @PersistenceConstructor
    public TaskDTO(String libelle, String outil) {
        this.libelle = libelle;
        this.outil = outil;
    }

    @Override
    public String toString() {
        return "Task{" +
                "libelle='" + libelle + '\'' +
                ", outil='" + outil + '\'' +
                '}';
    }
}
