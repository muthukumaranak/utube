package com.app.youtubeclone.entity;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String user;

    @Column(name = "channelid")
    private int channelid;

    public Subscriptions(int id, String user, int channelid) {
        this.id = id;
        this.user = user;
        this.channelid = channelid;
    }

    public Subscriptions(String user, int channelid) {
        this.user = user;
        this.channelid = channelid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getChannelid() {
        return channelid;
    }

    public void setChannelid(int channelid) {
        this.channelid = channelid;
    }
}
