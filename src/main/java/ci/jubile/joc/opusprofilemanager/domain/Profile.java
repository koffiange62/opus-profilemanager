package ci.jubile.joc.opusprofilemanager.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.List;

public class Profile extends model {
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String phoneNumber;
    @Getter @Setter
    private String password; // mot de passe
    @Getter @Setter
    private String country; // pays
    @Getter @Setter
    private String province; // region
    @Getter @Setter
    private String city; // ville
    @Getter @Setter
    private String district; // quartier
    @Getter @Setter
    private String street; // rue
    @Getter @Setter
    private String address; // adresse

    @Getter @Setter
    private List<Formation> formations;
    @Getter @Setter
    private List<Experience> experiences;
    @Getter @Setter
    private List<Competence> competences;

    public Profile(String lastName, String firstName, String email){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    @PersistenceConstructor
    public Profile(String id, String lastName, String firstName, String email, String phoneNumber, String password, String country, String province, String city, String district, String street, String address) {
        this.setId(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
        this.address = address;
    }


    @Override
    public String toString() {
        return "Profile{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", address='" + address + '\'' +
                ", formations=" + formations +
                ", experiences=" + experiences +
                ", competences=" + competences +
                '}';
    }
}