package com.example.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Member {
    @Id
    private String id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")      // name으로 FK 컬럼명을 생성한다.
    private MemberGroup memberGroup;
}
