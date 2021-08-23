package com.example.demo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table


public class sd_problems {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private Integer problemid;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private Date creationdate;

    @Column
    private boolean active;

    @ManyToOne (cascade = CascadeType.ALL) //Etiqueta de Java la cual indica que esta columna es propoiedad de
    @JoinColumn (name = "ownerid") // indica la columna en la base de datos la cual es la llave foranea para relacionarse con un owner
    private sd_owners owner; // atributo que guarda un owner para relacionar este problema con un owner

    public sd_problems() {
    }

    public sd_problems(String title, String description, Date creationdate, boolean active, sd_owners ownerid) {
        this.title = title;
        this.description = description;
        this.creationdate = creationdate;
        this.active = active;
        this.owner = ownerid;
    }

    public sd_problems(String title, String description, Date creationdate, boolean active) {
        this.title = title;
        this.description = description;
        this.creationdate = creationdate;
        this.active = active;
    }

    public sd_problems(String title, String description) {
        this.active = true;
        this.title = title;
        this.description = description;
        this.creationdate = new Date(System.currentTimeMillis());
    }

    public void setOwner(sd_owners owner) {
        this.owner = owner;
    }
}
