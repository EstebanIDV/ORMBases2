package com.example.demo;

import javax.persistence.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

@Table
public class sd_owners{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private BigInteger ownerid;

    @Column
    private String firstname;
    
    @Column
    private String lastname;
    
    @Column
    private String email;
    
    @Column
    private byte[] password;

    @Column
    private Boolean enabled;

    @Column
    private Date creationdate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<sd_problems> problems = new ArrayList<>();

    public sd_owners(String Firstname, String Lastname, String Email, String Password, Boolean Enabled, String Creationdate){
        firstname = Firstname;
        lastname = Lastname;
        email = Email;
        password = Password.getBytes(StandardCharsets.UTF_8); 
        enabled = Enabled;
        creationdate = new Date(System.currentTimeMillis());
    }

    public sd_owners(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password.getBytes(StandardCharsets.UTF_8);
        this.creationdate = new Date(System.currentTimeMillis());
        this.enabled=true;
    }

    public sd_owners(){}


    public String getfirstname(){
        return firstname;
    }

    public String getlastname(){
        return lastname;
    }

    public List<sd_problems> getProblems() {
        return problems;
    }
}


