package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class sd_ownersController {
    @Autowired
    private final sd_ownersService uService;

    public sd_ownersController(sd_ownersService uService) {
        this.uService = uService;
    }

    @GetMapping(path = "/add")
    public void addOwners(@RequestParam("ofirstname") String ofirstname, @RequestParam("olastname") String olastname,
        @RequestParam("oemail") String oemail, @RequestParam("opassword") String opassword, @RequestParam("oenabled") Boolean oenabled,
        @RequestParam("ocreationdate") String ocreationdate){
            sd_owners owner = new sd_owners(ofirstname, olastname, oemail, opassword, oenabled, ocreationdate);
            uService.addNew(owner);
        }

    @GetMapping(path = "/findAll")
    List<sd_owners> findAll(){
        return uService.findAll();
    }

    @GetMapping(path = "/getbyFirstname")
    public List<sd_owners> Nickname(@RequestParam("ofirstname") String ofirstname) {
        return uService.getbyFirstname(ofirstname);
    }
    @GetMapping(path = "/addproblem")
    public void addProblem(@RequestParam("email") String email, @RequestParam("title") String title,
                          @RequestParam("desc") String desc){
        /*
            Punto #1
            -Como implementar un objeto que representa una relacion 1 a N
            -Referirse a sd_owners y sd_problems para entidades y relaciones
         */
        sd_owners ustoadd =(uService.getbyEmail(email)).get(0); // Se recibe un email para buscar el usuario y almacenarlo en un objeto
        sd_problems newp= new sd_problems(title,desc); //Se recibe el problema a insertar a la base
        newp.setOwner(ustoadd); // El Owner encontrado por correo se asigna como atributo Owner del Problema
        ustoadd.getProblems().add(newp); //Se agrega el problema la lista de problemas que es atributo del owner
        uService.addNew(ustoadd); // Se guarda en la base de datos de nuevo el usuario ( referirse a sdOwnersService para la función de JPA)
    }
    @GetMapping(path = "/addproblems")
    public void addOwners(@RequestParam("fn") String ofirstname, @RequestParam("ln") String olastname,
                          @RequestParam("email") String oemail, @RequestParam("pass") String opassword,
                          @RequestParam("title") List<String> title,
                          @RequestParam("desc") List<String> desc){
        /*
            Punto #3
            -Como implementar una transacción que afecte a más de una tabla
            -Referirse a sd_owners y sd_problems para entidades y relaciones
            -Referirse a sd_ownersService para ver la función transaccional
         */
        sd_owners owner = new sd_owners(ofirstname, olastname, oemail, opassword); //crea un nuevo owner con los parametros recibidos
        List<sd_problems> problems = new ArrayList<>(); // crea una lista de problemas
        for(int i=0; i<title.size();i++){ //
            problems.add(new sd_problems(title.get(i),desc.get(i))); //agrega cada uno de los problema con n cantidad de parametros que reciba (title,description) a la lista creada
        }
        //problems.add(new sd_problems(null, null)); //prueba rápida para demostrar que se hizo rollback al agregar al final de la lista un problem con atributos nulos
        uService.newownerandproblems(owner, problems); //se accede a la función transaccional en sd_ownersService la cual agrega los objetos que crea en esta sección
    }

}
