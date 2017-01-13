package org.springframework.social.yammer.api;

import org.springframework.social.yammer.api.YammerMessage.Attachment;

import java.util.Date;
import java.util.List;

public class YammerMessageBuilder {

    private long id;
    private String url;
    private long networkId;
    private String privacy;
    private long threadId;
    private String senderType;
    private List<Attachment> attachments;
    private Long repliedToId;
    private long senderId;
    private String webUrl;
    private String clientUrl;
    private boolean systemMessage;
    private String messageType;
    private Date createdAt;
    private boolean directMessage;
    private String clientType;
    private org.springframework.social.yammer.api.YammerMessage.Body body;
    private org.springframework.social.yammer.api.YammerMessage.LikedBy likedBy;
    private long groupId;
    private long sharedMessageId;

    private YammerMessageBuilder() {
    }

    public YammerMessageBuilder id(long val) {
        id = val;
        return this;
    }

    public YammerMessageBuilder url(String val) {
        url = val;
        return this;
    }

    public YammerMessageBuilder networkId(long val) {
        networkId = val;
        return this;
    }

    public YammerMessageBuilder privacy(String val) {
        privacy = val;
        return this;
    }

    public YammerMessageBuilder threadId(long val) {
        threadId = val;
        return this;
    }

    public YammerMessageBuilder senderType(String val) {
        senderType = val;
        return this;
    }

    public YammerMessageBuilder attachments(List<Attachment> val) {
        attachments = val;
        return this;
    }

    public YammerMessageBuilder repliedToId(Long val) {
        repliedToId = val;
        return this;
    }

    public YammerMessageBuilder senderId(long val) {
        senderId = val;
        return this;
    }

    public YammerMessageBuilder webUrl(String val) {
        webUrl = val;
        return this;
    }

    public YammerMessageBuilder clientUrl(String val) {
        clientUrl = val;
        return this;
    }

    public YammerMessageBuilder systemMessage(boolean val) {
        systemMessage = val;
        return this;
    }

    public YammerMessageBuilder messageType(String val) {
        messageType = val;
        return this;
    }

    public YammerMessageBuilder createdAt(Date val) {
        createdAt = val;
        return this;
    }

    public YammerMessageBuilder directMessage(boolean val) {
        directMessage = val;
        return this;
    }

    public YammerMessageBuilder clientType(String val) {
        clientType = val;
        return this;
    }

    public YammerMessageBuilder body(org.springframework.social.yammer.api.YammerMessage.Body val) {
        body = val;
        return this;
    }

    public YammerMessageBuilder likedBy(org.springframework.social.yammer.api.YammerMessage.LikedBy val) {
        likedBy = val;
        return this;
    }

    public YammerMessageBuilder groupId(long val) {
        groupId = val;
        return this;
    }

    public YammerMessageBuilder sharedMessageId(long val) {
        sharedMessageId = val;
        return this;
    }

    public org.springframework.social.yammer.api.YammerMessage build() {
        return new YammerMessage(id, body, url, networkId, privacy, threadId,
            senderType, attachments, repliedToId, senderId, webUrl,
            clientUrl, systemMessage, messageType, createdAt, directMessage,
            clientType, likedBy, groupId, sharedMessageId);
    }
}
