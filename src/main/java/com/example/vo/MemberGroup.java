package com.example.vo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MemberGroup {

    @Id @GeneratedValue
    @Column(name = "GROUP_ID")
    private long groupId;
    @Column(name = "GROUP_NM")
    private String groupNm;

    @OneToMany(mappedBy = "memberGroup")
    private List<Member> members = new ArrayList<>();
}
