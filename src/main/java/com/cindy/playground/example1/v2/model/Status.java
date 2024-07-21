package com.cindy.playground.example1.v2.model;

import java.util.Arrays;

public enum Status {
    TODO(0),
    IN_PROGRESS(1),
    DONE(2);

    private final Integer index;

    Status(final Integer index) {
        this.index = index;
    }

    public static Status findNext(Status status) {
        return Arrays.stream(values()).filter(s -> s.index.equals(status.index + 1)).findFirst().orElse(Status.DONE);
    }
}
