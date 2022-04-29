package ci.jubile.joc.opusprofilemanager.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public abstract class model {
    @Id
    @Getter @Setter
    private String id;
    @Getter @Setter
    private LocalDateTime createdAt;
    @Getter @Setter
    private LocalDateTime updatedAt;

    public abstract String toString();
}
