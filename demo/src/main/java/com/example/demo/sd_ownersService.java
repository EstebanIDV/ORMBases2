package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class sd_ownersService {
    @Autowired
    private final sd_ownersRepository uRepository;

    public sd_ownersService(sd_ownersRepository uRepository) {
        this.uRepository = uRepository;
    }

    public void addNew(sd_owners Owner){
        uRepository.save(Owner);
    }

    public List<sd_owners> findAll(){
        return uRepository.findAll();
    }

    public List<sd_owners> getbyFirstname(String Firstname){
        return uRepository.findByFirstname(Firstname);
    }
    public List<sd_owners> getbyEmail(String Email){
        return uRepository.findByEmail(Email);
    }



    @Transactional(rollbackFor = SQLException.class) // Etiqueta de JPA que indica que la función es Transaccional, rollbackFor es utilizado para especificar a cuál tipo de operación debe reaccionar con un rollback (en este caso excepciones de SQL)
    public void newownerandproblems(sd_owners newowner, List<sd_problems> newproblems){
        /*
            Punto #3
            -Como implementar una transacción que afecte a más de una tabla
            -Referirse a sd_owners y sd_problems para entidades y relaciones
            -Referirse a sd_ownersControllers para ver la función la cual la utiliza.
         */
        //Función transaccional que añade un Owner y múltiples problems
        //Conexion a la tabla de Owner con un repositorio JPA a esa entidad (owner)
        uRepository.save(newowner); // Se guarda el nuevo owner, se puede eliminar y serviría igual pero al dejarlo es una prueba más de que aunque se guarda en la base previamente la transacción aún le puede hacer rollback
        //si existiera algún error en el insert de este usuario se hace rollback pero afecta únicamente a la tabla owners
        for (sd_problems newproblem : newproblems) { //iteración sobre múltiples problemas
            newproblem.setOwner(newowner); //se le asigna el nuevo owner a cada problema
            newowner.getProblems().add(newproblem); //se añade el problema a la lista de problemas producto de la relación con Owners
            // sin importar cuántos problemas se ingresen, todos se hace rollback si se topa con alguna excepción de sql
        }
        uRepository.save(newowner); //se guarda las actualizaciones del owner, corroborando que los objetos agregados cumplan con lo especificado en la base de datos, sino recibe una excepción y hace rollback

    }

}
