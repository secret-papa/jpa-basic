package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import javax.swing.*;

public class JapMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");

            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = member.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
