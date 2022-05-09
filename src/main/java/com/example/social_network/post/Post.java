package com.example.social_network.post;

import com.example.social_network.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "POST")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ID")
    private int id;


    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(nullable = false, name = "CONTENT", length = 300)
    @NonNull
    private String content;

}
