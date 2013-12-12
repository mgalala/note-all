package com.mgalala.noteall.util;

/**
 * Created by galala on 12/11/13.
 */
public enum IntentTypeEnum {

    IMAGE("image/*"),

    VIDEO("video/*"),

    DOCUMENT("application/"),

    WEB("text/plain");

    private final String type;

    IntentTypeEnum(String _type) {
        this.type = _type;
    }

    public String getType() {
        return this.type;
    }
}
