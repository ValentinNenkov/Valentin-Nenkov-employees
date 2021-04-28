package main;

import main.exceptions.NotFoundException;
import main.exceptions.UnknownFormatException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Demo {
    public static void main(java.lang.String[] args) throws NotFoundException, UnknownFormatException {
        ArrayList<Person> people = FileParser.readFromFile();
        Person temp1=null;
        Person temp2=null;
        long tempDays=-1;
        for (int i = 0; i < people.size()-1; i++) {
            for (int j = i+1; j < people.size(); j++) {
                for (Map.Entry<String,PairDateFromDateTo> e : people.get(j).projects.entrySet()) {
                    if(people.get(i).hasWorkedOnProject(e.getKey())){
                        PairDateFromDateTo dates1 = people.get(i).getDates(e.getKey());
                        PairDateFromDateTo dates2 = people.get(j).getDates(e.getKey());
                        int compareFrom = dates1.getDateFrom().compareTo(dates2.getDateFrom());
                        int compareTo = dates1.getDateTo().compareTo(dates2.getDateTo());
                        LocalDateTime dateFrom = compareFrom > 0 ? dates1.getDateFrom() : dates2.getDateFrom();
                        LocalDateTime dateTo = compareTo < 0 ? dates1.getDateTo() : dates2.getDateTo();
                        long duration = Duration.between(dateFrom,dateTo).toDays();
                        if(duration>tempDays){
                            tempDays=duration;
                            temp1=people.get(i);
                            temp2=people.get(j);
                        }
                    }
                }
            }
        }
        if(tempDays<0){
            System.out.println("No couple working together");
        }
        else {
            System.out.println(temp1.getId() + " worked with " + temp2.getId() + " for " + tempDays + " days.");
        }
    }
}
