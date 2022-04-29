package ci.jubile.joc.opusprofilemanager.domain;

import lombok.Getter;
import lombok.Setter;

public class Formation extends model{
    @Setter @Getter
    public String diploma;
    @Setter @Getter
    public String school;
    @Setter @Getter
    public String year;

    public Formation(String id, String diploma, String school, String year) {
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
