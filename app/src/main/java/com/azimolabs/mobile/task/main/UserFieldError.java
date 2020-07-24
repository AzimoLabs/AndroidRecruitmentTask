package com.azimolabs.mobile.task.main;

import com.azimolabs.mobile.task.utils.ErrorType;

public class UserFieldError {

    private final ErrorType type;
    private final String errorMessage;

    public UserFieldError(ErrorType type, String errorMessage) {
        this.type = type;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
