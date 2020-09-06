package com.app.youtubeclone.entity;

import javax.persistence.*;


@Entity
@Table(name = "media_comment")
public class MediaComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "commentby")
    public String commentby;

    @Column(name = "comment")
    public String comment;

    @ManyToOne
    @JoinColumn(name = "mediafieldid", referencedColumnName = "id")
    public MediaFile mediaComment;

    @Column(name = "created_at")
    public String created_at;

    public MediaComment(int id, String commentby, String comment, MediaFile mediaFile, String created_at) {
        this.id = id;
        this.commentby = commentby;
        this.comment = comment;
        this.mediaComment = mediaFile;
        this.created_at = created_at;
    }

    public MediaComment(String commentby, String comment, MediaFile mediaFile, String created_at) {
        this.commentby = commentby;
        this.comment = comment;
        this.mediaComment = mediaFile;
        this.created_at = created_at;
    }

    public MediaComment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentby() {
        return commentby;
    }

    public void setCommentby(String commentby) {
        this.commentby = commentby;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MediaFile getMediaFile() {
        return mediaComment;
    }

    public void setMediaFile(MediaFile mediaFile) {
        this.mediaComment = mediaFile;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}