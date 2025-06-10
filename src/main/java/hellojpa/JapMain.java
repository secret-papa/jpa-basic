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
                Team team = new Team();
                team.setName("teamA");

                em.persist(team);

                Member member = new Member();
                member.setName("teamA");
                member.setAge(10);
                member.setTeam(team);

                em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from Member m, Team t where m.name = t.name";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            System.out.println("result = " + result.size());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
