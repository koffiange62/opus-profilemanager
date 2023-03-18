package ci.jubile.joc.opusprofilemanager.v1.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
public class ProfileResource {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private LocalDateTime createdAt;
    @Getter @Setter
    private LocalDateTime updatedAt;
    @Getter @Setter
    @NotBlank(message = "This field is mandatory")
    @Size(min = 3, max = 16, message = "Must have at least 3 and less than 16 caracters")
    private String lastName;
    @Getter @Setter
    @NotBlank(message = "This field is mandatory")
    @Size(min = 3, max = 16, message = "Must have at least 3 and less than 20 caracters")
    private String firstName;
    @Getter @Setter
    @Email(message = "Must be an email address")
    private String email;
    @Getter @Setter
    @Size(min = 8, max = 12, message = "Must have at least 8 and less than 12 caracters")
    private String phoneNumber;
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caracters")
    private String country; // pays
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caracters")
    private String province; // region
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caracters")
    private String city; // ville
    @Getter @Setter
    @Size(min = 3, max = 20, message = "Must have at least 3 and less than 20 caracters")
    private String district; // quartier
    @Getter @Setter
    @Size(max = 20, message = "Must have at least 3 and less than 20 caracters")
    private String street; // rue
    @Getter @Setter
    @Size(max = 20, message = "Must have at least 3 and less than 20 caracters")
    private String address; // adresse
    @Getter @Setter
    private String password;

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
                '}';
    }
}