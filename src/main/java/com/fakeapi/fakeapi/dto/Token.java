package com.fakeapi.fakeapi.dto;

public class Token {
    private String tokenId;

    public Token(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
