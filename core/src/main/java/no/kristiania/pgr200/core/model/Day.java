package no.kristiania.pgr200.core.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Day {

    private UUID id;

    LocalDate date;

    private List<Timeslot> timeslots;

    public Day(UUID id, LocalDate date, List<Timeslot> timeslots) {
        this.id = id;
        this.date = date;
        this.timeslots = timeslots;
    }

    public Day(UUID id, LocalDate date) {
       this(id, date, new ArrayList<>());
    }

    public Day(LocalDate date) {
        this(UUID.randomUUID(), date, new ArrayList<>());
    }


    public LocalDate getDate(){
        return date;
    }

    public UUID getId() {
        return id;
    }

    public List<Timeslot> getTimeslots() {
        return timeslots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return Objects.equals(id, day.id) &&
                Objects.equals(date, day.date) &&
                Objects.equals(timeslots, day.timeslots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, timeslots);
    }

    @Override
    public String toString() {
         return "Date: " + getDate() + "\n" +
                "ID: " + getId() + "\n";
    }
}
