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



    @Transactional(rollbackFor = SQLException.class)
    public void newownerandproblems(sd_owners newowner, List<sd_problems> newproblems){
        uRepository.save(newowner);
        for (sd_problems newproblem : newproblems) {
            newproblem.setOwner(newowner);
            newowner.getProblems().add(newproblem);
        }
        uRepository.save(newowner);

    }

}
