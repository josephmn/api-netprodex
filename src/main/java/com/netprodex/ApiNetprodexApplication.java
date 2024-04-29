package com.netprodex;

import com.netprodex.persistence.entity.PermissionEntity;
import com.netprodex.persistence.entity.RoleEntity;
import com.netprodex.persistence.entity.RoleEnum;
import com.netprodex.persistence.entity.UserEntity;
import com.netprodex.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ApiNetprodexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiNetprodexApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            // ************** CREATE PERMISSIONS ************** //
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();

            PermissionEntity refactorPermission = PermissionEntity.builder()
                    .name("REFACTOR")
                    .build();

            // ************** CREATE ROLES ************** //
            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleUser = RoleEntity.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

            RoleEntity roleInvited = RoleEntity.builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permissionList(Set.of(readPermission))
                    .build();

            RoleEntity roleDeveloper = RoleEntity.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
                    .build();

            // ************** CREATE USERS ************** //
            UserEntity userAdmin = UserEntity.builder()
                    .username("admin")
                    .password("$2y$10$Th2vpAvPMrFyQReC91vaXOxrE8v7meMIyXfLW3DuD3YC/Nixnm1uW")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();

            UserEntity userUser = UserEntity.builder()
                    .username("user")
                    .password("$2y$10$Th2vpAvPMrFyQReC91vaXOxrE8v7meMIyXfLW3DuD3YC/Nixnm1uW")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleUser))
                    .build();

            UserEntity userInvited = UserEntity.builder()
                    .username("invited")
                    .password("$2y$10$Th2vpAvPMrFyQReC91vaXOxrE8v7meMIyXfLW3DuD3YC/Nixnm1uW")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleInvited))
                    .build();

            UserEntity userDeveloper = UserEntity.builder()
                    .username("developer")
                    .password("$2y$10$Th2vpAvPMrFyQReC91vaXOxrE8v7meMIyXfLW3DuD3YC/Nixnm1uW")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleDeveloper))
                    .build();

            if (userRepository.count() == 0) {
                userRepository.saveAll(List.of(userAdmin, userUser, userInvited, userDeveloper));
            } else {
                System.out.println("Los datos ya existen en la base de datos.");
            }
        };
    }
}
