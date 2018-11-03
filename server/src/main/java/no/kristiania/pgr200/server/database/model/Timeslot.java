package no.kristiania.pgr200.server.database.model;


import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class Timeslot {

    private UUID id;
    private LocalTime start;
    private LocalTime end;
    private Talk talk;


    public Timeslot( LocalTime start, LocalTime end) throws RuntimeException {
        this(UUID.randomUUID(), start, end , null);
    }
    public Timeslot( UUID id, LocalTime start, LocalTime end) throws RuntimeException {
        this(id, start, end, null);
    }

    public Timeslot( LocalTime start, LocalTime end, Talk talk) throws RuntimeException {
        this(UUID.randomUUID(), start, end , talk);
    }

    public Timeslot(UUID id, LocalTime start, LocalTime end, Talk talk) throws RuntimeException{
            this.id = id;
            this.talk = talk;
            this.start = start;
            this.end = end;
    }




    public Talk getTalk() {
        return talk;
    }


    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
    public UUID getId() {
        return id;
    }
    //TODO: returnere talk isteden?
    public UUID getTalkId(){
        return talk == null ? null : talk.getId();
    }


    @Override
    public String toString() {
        return "Start: " + getStart() + "\n" +
                "End: " + getEnd() + "\n" +
                "ID: " + getId() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timeslot timeslot = (Timeslot) o;
        return Objects.equals(id, timeslot.id) &&
                Objects.equals(start, timeslot.start) &&
                Objects.equals(end, timeslot.end) &&
                Objects.equals(talk, timeslot.talk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end, talk);
    }


}
