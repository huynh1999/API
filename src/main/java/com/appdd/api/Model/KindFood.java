package com.appdd.api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "kindfood")
@Table(name = "kindfood")
public class KindFood {
    @Id
    @Column(name = "idkindfood")
    private int Idkindfood;
    @Column(name = "name")
    private String Name;
    @Column(name = "code")
    private String Code;
}
