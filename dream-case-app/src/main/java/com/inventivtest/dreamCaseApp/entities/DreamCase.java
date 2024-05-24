package com.inventivtest.dreamCaseApp.entities;

import com.inventivtest.dreamCaseApp.validroups.ValidationGroups;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DreamCase  implements Serializable {
    private static final   long serialialVersionID = 1L;

    @NotNull(groups = ValidationGroups.caseId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;
    private Date creationDate;
    private Date lastUpdateDate;
    @Size(max = 255)
    private String title;
    @Size(max = 2056)
    private String description;



}
