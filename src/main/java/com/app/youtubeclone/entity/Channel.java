package com.app.youtubeclone.entity;

import javax.persistence.*;

@Entity
@Table(name = "channels", uniqueConstraints = @UniqueConstraint(columnNames = "owner"))
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "channel")
    private String channel;

    @Column(name = "description")
    private String description;

    @Column(name = "subscribers")
    private int subscribers;

    @Column(name = "videos")
    private int videos;

    @Column(name = "owner")
    private String owner;

    @Column(name = "coverUrl")
    private String coverUrl;

    @Column(name = "createdAt")
    private String createdAt;

    public Channel() {
    }

    public Channel(String channel, String description, String coverUrl, String createdAt, String owner) {
        this.channel = channel;
        this.description = description;
        this.coverUrl = coverUrl;
        this.createdAt = createdAt;
        this.owner = owner;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public int getVideos() {
        return videos;
    }

    public void setVideos(int videos) {
        this.videos = videos;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
