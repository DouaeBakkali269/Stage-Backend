package net.java.Training_management.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Discharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dischargeId;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String signature;
    private String status;


    @ManyToOne
    @JoinColumn(name = "organization_unit_id")
    private OrganizationUnit organizationUnit;

    @ManyToOne
    @JoinColumn(name = "selected_child_id")
    private OrganizationUnit selectedChild;

    @OneToMany(mappedBy = "discharge", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Material> materials = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur user;
}
