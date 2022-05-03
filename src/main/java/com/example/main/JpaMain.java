package com.example.main;

import com.example.vo.Member;
import com.example.vo.MemberGroup;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
    public static void main(String[] args) {
        // 애플리케이션에서 한번만 생성하여 사용한다.
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-Member");

        // 하나의 트렌젝션을 만든다.
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // INSERT
            Member member = new Member();
            member.setId("hong");
            member.setName("홍길동");
            member.setAge(20);

            MemberGroup memberGroup = new MemberGroup();
            memberGroup.setGroupNm("GROUP1");
            entityManager.persist(memberGroup);
            member.setMemberGroup(memberGroup);

            // 영속성 컨텍스트에 들어가지만 실제로 DB에 저장하지 않는다.
            entityManager.persist(member);

            // SELECT
            Member findMember = entityManager.find(Member.class, "hong");
            System.out.println(findMember.getId());
            System.out.println(findMember.getName());
            System.out.println(findMember.getAge());
            System.out.println(findMember.getMemberGroup().getGroupNm());

            // UPDATE
            findMember.getMemberGroup().setGroupNm("GROUP2");

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
