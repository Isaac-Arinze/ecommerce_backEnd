package com.skytech.e_store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table (name = "tokens")
public class AuthenticationToken {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Integer id;
    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")

    private User user;

    public AuthenticationToken(User user) {
        this.token = UUID.randomUUID().toString();
        this.createdDate = new Date();
        this.user = user;
    }

    public AuthenticationToken() {
    }
}

