package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Person {
    private String id;
    HashMap<String,PairDateFromDateTo> projects;
    Person(String id){
        this.id=id;
        projects=new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public HashMap<String, PairDateFromDateTo> getProjects() {
        return projects;
    }

    public void addProject(String p, LocalDateTime dateFrom, LocalDateTime dateTo){
        projects.put(p,new PairDateFromDateTo(dateFrom,dateTo));
    }
    public boolean hasWorkedOnProject(String p){
        return projects.containsKey(p);
    }
    public PairDateFromDateTo getDates(String p){
        return projects.get(p);
    }
}