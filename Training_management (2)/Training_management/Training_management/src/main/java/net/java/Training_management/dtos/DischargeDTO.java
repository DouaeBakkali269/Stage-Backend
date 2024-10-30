package net.java.Training_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DischargeDTO {

        private Integer dischargeId;
        private Date date;
        private String signature;
        private String username;
        private String status;
        private OrganizationUnitDTO organizationUnit;
        private OrganizationUnitDTO selectedChild;
        private List<MaterialDTO> materials;


}

