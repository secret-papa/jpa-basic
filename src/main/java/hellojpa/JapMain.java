package hellojpa;

import hellojpa.domain.*;
import jakarta.persistence.*;

import java.util.List;

public class JapMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("팀A");

            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");

            em.persist(teamB);

            Member member1 = new Member();
            member1.setName("회원1");
            member1.setAge(10);
            member1.setMemberType(MemberType.ADMIN);
            member1.changeTeam(teamA);

            em.persist(member1);

            Member member2 = new Member();
            member2.setName("회원2");
            member2.setAge(12);
            member2.setMemberType(MemberType.ADMIN);
            member2.changeTeam(teamA);

            em.persist(member2);

            Member member3 = new Member();
            member3.setName("회원3");
            member3.setAge(13);
            member3.setMemberType(MemberType.ADMIN);
            member3.changeTeam(teamB);

            em.persist(member3);

            em.flush();
            em.clear();

            String query = "select m from Member m where m = :member";
            Member findMember = em.createQuery(query, Member.class)
                    .setParameter("member", member1)
                    .getSingleResult();

            System.out.println("findMember = " + findMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
