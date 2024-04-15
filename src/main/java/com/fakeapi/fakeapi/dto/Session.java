package com.fakeapi.fakeapi.dto;

import javax.annotation.Resource;

@Resource
public class Session {
    private String sessionId;

    public Session() {
    }

    public Session(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
