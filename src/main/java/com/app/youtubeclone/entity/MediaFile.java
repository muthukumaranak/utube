package com.app.youtubeclone.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "media_file")
public class MediaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "tag")
    private String tag;

    @Column(name = "restriction")
    private String restriction;

    @Column(name = "createdAt")
    private String createdAt;

    @Column(name = "visibility")
    private String visibility;

    @Column(name = "thumbnailUrl")
    private String thumbnailUrl;

    @Column(name = "videoUrl")
    private String videoUrl;

    @Column(name = "owner")
    private String owner = "admin";

    @Column(name = "likes")
    private Integer likes = 0;

    @Column(name = "dislikes")
    private Integer dislikes = 0;

    @Column(name = "views")
    private Integer views = 0;

    @Column(name = "duration")
    private String duration;


    @OneToMany(mappedBy = "mediaComment", orphanRemoval = true, cascade = CascadeType.PERSIST)
   // @OneToMany(mappedBy = "mediaComment", fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true, targetEntity = MediaComment.class)
    private List<MediaComment> mediaComment;


    public MediaFile() {
    }

    public MediaFile(String title, String description, String tag, String restriction, String createdAt, String visibility, String thumbnailUrl, String videoUrl, String owner,String duration) {

        this.title = title;
        this.description = description;
        this.tag = tag;
        this.restriction = restriction;
        this.createdAt = createdAt;
        this.visibility = visibility;
        this.thumbnailUrl = thumbnailUrl;
        this.videoUrl = videoUrl;
        this.owner = owner;
        this.duration = duration;
    }

    public MediaFile(String title, String description, String tag, String restriction, String createdAt,
                     String visibility, String thumbnailUrl, String videoUrl, String owner, int likes, int dislikes, int views, String duration) {
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.restriction = restriction;
        this.createdAt = createdAt;
        this.visibility = visibility;
        this.thumbnailUrl = thumbnailUrl;
        this.videoUrl = videoUrl;
        this.owner = owner;
        this.likes = likes;
        this.dislikes = dislikes;
        this.duration = duration;
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<MediaComment> getMediaComment() {
        return mediaComment;
    }

    public void setMediaComment(List<MediaComment> mediaComment) {
        this.mediaComment = mediaComment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

}
