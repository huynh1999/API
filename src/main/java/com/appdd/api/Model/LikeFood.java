package com.appdd.api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "likefood")
@Table(name = "likefood")
public class LikeFood {
    @Id
    @Column(name = "idfood")
    private int idfood;
    @Column(name = "token")
    private String token;
}
