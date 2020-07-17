package com.miscounting.twitch.rewardbot.domain;

public class PostRequest {

    private String url;
    private String payloadToPost;

    public String getURL() {
        return url;
    }

    public void setURL(String URL) {
        this.url = URL;
    }

    public String getPayloadToPost() {
        return payloadToPost;
    }

    public void setPayloadToPost(String payloadToPost) {
        this.payloadToPost = payloadToPost;
    }
}
