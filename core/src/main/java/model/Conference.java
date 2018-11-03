package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Conference {

    private UUID id;
    private String name;
    private List<Day> days;

    public Conference(UUID id) {
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "ID: " + getId() + "\n";
    }

    public Conference(String name) {
        this(UUID.randomUUID(), name, new ArrayList<>());
    }

    public Conference(UUID id, String name) {
        this(id, name, new ArrayList<>());
    }

    public Conference(UUID id, String name, List<Day> days) {
        this.id = id;
        this.name = name;
        this.days = days;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Day> getDays() {
        return days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conference)) return false;

        Conference that = (Conference) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getDays() != null ? getDays().equals(that.getDays()) : that.getDays() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDays() != null ? getDays().hashCode() : 0);
        return result;
    }
}
