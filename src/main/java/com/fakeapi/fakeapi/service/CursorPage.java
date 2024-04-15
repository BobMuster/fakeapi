package com.fakeapi.fakeapi.service;

import java.util.List;

public class CursorPage<T> {
    private List<T> items;
    private String nextCursor;
    private String nextLink;
    private int total;
    private int offset;
    private int limit;
    private String forin = "h,e,l,l,o";

    public CursorPage(List<T> items, String nextCursor) {
        this.items = items;
        this.nextCursor = nextCursor;
    }

    public CursorPage(List<T> items, String nextCursor, String nextItems) {
        this.items = items;
        this.nextCursor = nextCursor;
        this.nextLink = nextItems;
    }

    public CursorPage(List<T> items, String nextLink, int total, int offset, int limit) {
        this.items = items;
        this.nextLink = nextLink;
        this.total = total;
        this.offset = offset;
        this.limit = limit;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getForin() {
        return forin;
    }

    public void setForin(String forin) {
        this.forin = forin;
    }
}
