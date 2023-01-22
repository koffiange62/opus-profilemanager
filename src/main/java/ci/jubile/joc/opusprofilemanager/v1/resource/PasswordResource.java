package ci.jubile.joc.opusprofilemanager.v1.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor @Builder
public class PasswordResource {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private LocalDateTime createdAt;
    @Getter @Setter
    private LocalDateTime updatedAt;
    @Getter @Setter
    private String profileId;
    @Getter @Setter
    private String currentPassword;
    @Getter @Setter
    private String newPassword;
}
