package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PublishedCommentList {

    @SerializedName("commentaries")
    private List<PublishedComment> listPublishedComment;

    public PublishedCommentList(List<PublishedComment> listPublishedComment) {
        this.listPublishedComment = listPublishedComment;
    }

    public List<PublishedComment> getListPublishedComment() {
        return listPublishedComment;
    }
}
