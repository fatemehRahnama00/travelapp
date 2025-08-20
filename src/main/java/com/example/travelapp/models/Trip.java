package com.example.travelapp.models;

public class Trip {
    private String id;
    private String destination;
    private String date;
    private String duration;
    private String notes;
    private String imageUrl;

    public Trip() {}

    public Trip(String id, String destination, String date, String duration, String notes, String imageUrl) {
        this.id = id;
        this.destination = destination;
        this.date = date;
        this.duration = duration;
        this.notes = notes;
        this.imageUrl = imageUrl;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}