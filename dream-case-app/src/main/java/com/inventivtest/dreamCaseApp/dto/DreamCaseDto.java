package com.inventivtest.dreamCaseApp.dto;

import com.inventivtest.dreamCaseApp.entities.DreamCase;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DreamCaseDto implements Serializable {
    private static final   long serialialVersionID = 1L;

    private Long caseId;
    private Date creationDate;
    private Date lastUpdateDate;
    @Size(max = 255)
    private String title;
    @Size(max = 2056)
    private String description;


    public DreamCaseDto(DreamCase entity) {
        caseId = entity.getCaseId();
        creationDate = entity.getCreationDate();
        lastUpdateDate = entity.getLastUpdateDate() ;
        title = entity.getTitle() ;
        description =entity.getDescription() ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DreamCaseDto that = (DreamCaseDto) o;
        return Objects.equals(caseId, that.caseId) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(lastUpdateDate, that.lastUpdateDate) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caseId, creationDate, lastUpdateDate, title, description);
    }
}
