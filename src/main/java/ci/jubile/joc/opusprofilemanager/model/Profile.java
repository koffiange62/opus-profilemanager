package ci.jubile.joc.opusprofilemanager.model;

import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Profile extends model {
    @Getter @Setter
    @NotBlank(message = "Can not be blank or null")
    @Size(min = 3, max = 16, message = "Must have at least 3 and less than 17 caraters")
    private String lastName;
    @Getter @Setter
    @NotBlank(message = "Can not be blank or null")
    @Size(min = 3, max = 16, message = "Must have at least 3 and less than 17 caraters")
    private String firstName;
    @Getter @Setter
    @Email(message = "Must be qn emqil address")
    private String email;
    @Getter @Setter
    @Size(min = 8, max = 12, message = "Must have at least 8 and less than 12 caraters")
    private String phoneNumber;
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caraters")
    private String country; // pays
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caraters")
    private String province; // region
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caraters")
    private String city; // ville
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caraters")
    private String district; // quartier
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caraters")
    private String street; // rue
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caraters")
    private String address; // adresse
    @Getter @Setter
    private ProfileStatus status = ProfileStatus.ENABLE;

    @Getter @Setter
    private List<Formation> formations = new ArrayList<>();
    @Getter @Setter
    private List<Experience> experiences = new ArrayList<>();
    @Getter @Setter
    private List<Competence> competences = new ArrayList<>();

    public Profile() {
    }

    @PersistenceConstructor
    public Profile(String id, String lastName, String firstName, String email, String phoneNumber,
                   String country, String province, String city, String district, String street, String address) {
        this.setId(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
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