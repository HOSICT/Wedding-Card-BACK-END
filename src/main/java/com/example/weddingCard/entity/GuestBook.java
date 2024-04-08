package com.example.weddingCard.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "guest_book")
public class GuestBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_book_id")
    private Integer guestBookId;

    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;

    @Column(name = "comment_id")
    private Integer commentId;

    private String name;

    private LocalDateTime date;

    private String content;

    @Column(name = "management_password")
    private String managementPassword;

    public Integer getGuestBookId() {
        return guestBookId;
    }

    public void setGuestBookId(Integer guestBookId) {
        this.guestBookId = guestBookId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getManagementPassword() {
        return managementPassword;
    }

    public void setManagementPassword(String managementPassword) {
        this.managementPassword = managementPassword;
    }
}
