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
        sd_owners ustoadd =(uService.getbyEmail(email)).get(0);
        sd_problems newp= new sd_problems(title,desc);
        newp.setOwner(ustoadd);
        ustoadd.getProblems().add(newp);
        uService.addNew(ustoadd);
    }
    @GetMapping(path = "/addproblems")
    public void addOwners(@RequestParam("fn") String ofirstname, @RequestParam("ln") String olastname,
                          @RequestParam("email") String oemail, @RequestParam("pass") String opassword,
                          @RequestParam("title") List<String> title,
                          @RequestParam("desc") List<String> desc){
        sd_owners owner = new sd_owners(ofirstname, olastname, oemail, opassword);
        List<sd_problems> problems = new ArrayList<>();
        for(int i=0; i<title.size();i++){
            problems.add(new sd_problems(title.get(i),desc.get(i)));
        }
        //problems.add(new sd_problems(null, null));
        uService.newownerandproblems(owner, problems);
    }

}
