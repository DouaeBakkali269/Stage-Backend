package net.java.Training_management.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrganizationUnit")
@Entity
public class OrganizationUnit {

   @Id
 //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer unitId;

    private String name;
    private String description;
    private OrganizationUnitType type;

    @ManyToOne//(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_unit_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private OrganizationUnit parentUnit;


    @OneToMany(mappedBy = "parentUnit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<OrganizationUnit> childUnits = new HashSet<>();

    @OneToMany(mappedBy = "organizationUnit")
    private List<Discharge> discharges = new ArrayList<>();

    @OneToMany(mappedBy = "selectedChild")
    private List<Discharge> child_discharges = new ArrayList<>();



}
