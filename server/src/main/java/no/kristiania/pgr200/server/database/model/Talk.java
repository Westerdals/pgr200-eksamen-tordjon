package no.kristiania.pgr200.server.database.model;

import java.util.Objects;
import java.util.UUID;

public class Talk {

    private UUID id;
    private String title;
    private String description;
    private String topicTitle;

    public Talk(UUID id, String title, String description, String topicTitle) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topicTitle = topicTitle;
    }

    public Talk(String title, String description, String topicTitle){
        this(UUID.randomUUID(), title, description, topicTitle);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Talk talk = (Talk) o;
        return Objects.equals(title, talk.title) &&
                Objects.equals(description, talk.description) &&
                Objects.equals(topicTitle, talk.topicTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, topicTitle);
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Topic: " + getTopicTitle()  + "\n" +
                "ID: " + getId() + "\n";
    }

}
