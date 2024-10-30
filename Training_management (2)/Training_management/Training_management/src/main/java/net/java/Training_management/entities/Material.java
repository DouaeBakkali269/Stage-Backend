package net.java.Training_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Material {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String serialNumber;
    private String description;
    private String status; //available, unavailable reserved disposed
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private MaterialType type;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    @ManyToOne
    @JoinColumn(name = "discharge_id")
    private Discharge discharge;

}

