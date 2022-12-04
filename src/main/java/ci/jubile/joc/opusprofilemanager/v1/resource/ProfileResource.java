package ci.jubile.joc.opusprofilemanager.v1.resource;

import ci.jubile.joc.opusprofilemanager.domain.model;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuperBuilder
public class ProfileResource extends ModelResource {
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
    private String password; // mot de passe
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
    private ProfileStatus status;

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
                '}';
    }
}