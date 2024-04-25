package com.netprodex.persistence.payload;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MessageResponse implements Serializable {
    private String message;
    private Object object;
}
