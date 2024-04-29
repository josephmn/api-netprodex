package com.netprodex.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(name = "is_enabled", columnDefinition = "TINYINT")
    private Boolean isEnabled;

    @Column(name = "account_no_expired", columnDefinition = "TINYINT")
    private Boolean accountNoExpired;

    @Column(name = "account_no_locked", columnDefinition = "TINYINT")
    private Boolean accountNoLocked;

    @Column(name = "credential_no_expired", columnDefinition = "TINYINT")
    private Boolean credentialNoExpired;
}
