package com.netprodex.persistence;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Cliente implements Serializable {

    @Schema(description = "Id customer", name = "customerId", type = "Integer", example = "1")
    private Integer customerId;

    @Schema(description = "Name customer", name = "name", type = "String", example = "John")
    @NotEmpty(message = "Name is require")
    @Size(max = 100)
    private String name;

    @Schema(description = "Last name customer", name = "lastname", type = "String", example = "Darrell")
    @NotEmpty(message = "Last name is require")
    @Size(max = 100)
    private String lastname;

    @Schema(description = "Email customer", name = "email", type = "String", example = "darrel.xyz@example.com")
    @NotEmpty(message = "Email is require")
    @Email
    private String email;

    @Schema(description = "Phone number", name = "phone", type = "String", example = "987654321")
    @NotEmpty(message = "Phone is require")
    @Size(max = 50)
    private String phone;

    @Schema(description = "Address customer", name = "address", type = "String", example = "Av. los avenger 123")
    @Size(min = 1, max = 100)
    private String address;
}
