package com.netprodex.persistence.payload;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class MessageError implements Serializable {
    private LocalDateTime date = LocalDateTime.now();
    private String message;
    private String url;
    private boolean error = true;
    private Object object;

    public MessageError(String message, String url, Object object) {
        this.message = message;
        this.url = url.replace("uri=", "");
        this.object = object;
    }
}
