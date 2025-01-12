package com.netprodex.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
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

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
}
