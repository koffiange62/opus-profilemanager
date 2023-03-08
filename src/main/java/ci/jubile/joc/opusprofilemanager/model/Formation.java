package ci.jubile.joc.opusprofilemanager.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

public class Formation extends model{
    @Setter @Getter
    public String diploma;
    @Setter @Getter
    public String school;
    @Setter @Getter
    public Year year;
    @Setter @Getter
    public Domain domain;

    public Formation(String id, String diploma, String school, Year year) {
        this.setId(id);
        this.diploma = diploma;
        this.school = school;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "diploma='" + diploma + '\'' +
                ", school='" + school + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
