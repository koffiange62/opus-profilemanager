package ci.jubile.joc.opusprofilemanager.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

public class Velo extends model{
    @Getter @Setter
    private Integer number;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String address;

    @PersistenceConstructor
    public Velo(Integer number, String name, String address) {
        this.number = number;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return null;
    }
}
