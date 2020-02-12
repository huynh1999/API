package com.appdd.api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "groupfoodinfo")
@Table(name = "groupfoodinfo")
public class GroupFoodInfo {
    @Id
    @Column(name = "idgroup")
    private int Idgroup;
    @Column(name = "idfood")
    private  int Idfood;
}
