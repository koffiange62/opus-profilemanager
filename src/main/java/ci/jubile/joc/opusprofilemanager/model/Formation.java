package ci.jubile.joc.opusprofilemanager.model;

import lombok.Getter;
import lombok.Setter;

public class Formation extends model{
    @Setter @Getter
    public String diploma;
    @Setter @Getter
    public String school;
    @Setter @Getter
    public Integer year;
    @Setter @Getter
    public Domain domain;

    public Formation() {
    }

    public Formation(String diploma, String school, Integer year, Domain domain) {
        this.diploma = diploma;
        this.school = school;
        this.year = year;
        this.domain = domain;
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
