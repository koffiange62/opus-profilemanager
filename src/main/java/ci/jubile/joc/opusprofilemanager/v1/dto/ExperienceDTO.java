package ci.jubile.joc.opusprofilemanager.v1.dto;

import ci.jubile.joc.opusprofilemanager.domain.Task;
import ci.jubile.joc.opusprofilemanager.domain.model;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ContractType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDate;
import java.util.List;

public class ExperienceDTO extends model {
    @Getter @Setter
    private LocalDate startedAt;
    @Getter @Setter
    private LocalDate endedAt;
    @Getter @Setter
    private String company;
    @Getter @Setter
    private String jobTitle;
    @Getter @Setter
    private ContractType contractType;
    @Getter @Setter
    private List<Task> tasks;

    @PersistenceConstructor
    public ExperienceDTO(String id, LocalDate startedAt, LocalDate endedAt, String company, String jobTitle, ContractType contractType, List<Task> tasks) {
        this.setId(id);
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.company = company;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", company='" + company + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", contractType=" + contractType +
                '}';
    }
}
