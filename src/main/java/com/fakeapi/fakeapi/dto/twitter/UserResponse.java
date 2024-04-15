package com.fakeapi.fakeapi.dto.twitter;

import java.util.List;

public class UserResponse {
    private List<TwitterUser> data;
    private Meta meta;

    public List<TwitterUser> getData() {
        return data;
    }

    public void setData(List<TwitterUser> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
