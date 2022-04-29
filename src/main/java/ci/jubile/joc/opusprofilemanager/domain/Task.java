package ci.jubile.joc.opusprofilemanager.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

public class Task extends model{
    @Getter @Setter
    private String libelle;
    @Getter @Setter
    private String outil;

    @PersistenceConstructor
    public Task(String libelle, String outil) {
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
