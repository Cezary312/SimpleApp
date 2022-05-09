package com.example.social_network.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ID")
    private int id;

    @Column(nullable = false, name = "USER_NAME", length = 45)
    private String userName;

    @Column(nullable = false, name = "PASSWORD", length = 45)
    private String password;

}
