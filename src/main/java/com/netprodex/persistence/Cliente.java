package com.netprodex.persistence;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Schema(description = "Id customer", name = "customerId", type = "Integer", example = "1")
    private Integer customerId;

    @Schema(description = "Name customer", name = "name", type = "String", example = "John")
    @NotNull
    @Size(max = 100)
    private String name;

    @Schema(description = "Last name customer", name = "lastname", type = "String", example = "Darrell")
    @Size(max = 100)
    @NotNull
    private String lastname;

    @Schema(description = "Email customer", name = "email", type = "String", example = "darrel.xyz@example.com")
    @Size(max = 100)
    @NotNull
    private String email;

    @Schema(description = "Phone number", name = "phone", type = "String", example = "987654321")
    @Size(max = 50)
    private String phone;

    @Schema(description = "Address customer", name = "address", type = "String", example = "Av. los avenger 123")
    @Size(min = 1, max = 100)
    private String address;
}
