package com.eiasinprodhan.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;

}
