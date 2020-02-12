package com.appdd.api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "groupuserinfo")
@Table(name = "groupuserinfo")
public class GroupUserInfo {
    @Id
    @Column(name ="idgroup")
    private int Idgroup;
    @Column(name = "token")
    private String Token;
}
