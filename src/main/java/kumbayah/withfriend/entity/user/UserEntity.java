package kumbayah.withfriend.entity.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long userId;

    @Column
    private double point;
}
