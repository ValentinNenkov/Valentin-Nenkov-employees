package main;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PairDateFromDateTo {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    PairDateFromDateTo(LocalDateTime dateFrom, LocalDateTime dateTo){
        this.dateFrom=dateFrom;
        this.dateTo=dateTo;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }
}