package com.netprodex.persistence.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse implements Serializable {
    private LocalDateTime date = LocalDateTime.now();
    private String message;
    private boolean error = false;
    private Object object;

    public ApiResponse(Object object) {
        this.message = "successful execution";
        this.object = object;
    }
}
