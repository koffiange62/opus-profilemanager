package ci.jubile.joc.opusprofilemanager.v1.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@SuperBuilder
public abstract class ModelResource {
    @Id
    @Getter @Setter
    private String id;
    @Getter @Setter
    private LocalDateTime createdAt;
    @Getter @Setter
    private LocalDateTime updatedAt;

    public abstract String toString();
}
