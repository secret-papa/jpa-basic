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
            Member member = new Member();
            member.setName("member1");
            member.setAge(10);
            member.setMemberType(MemberType.ADMIN);

            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from Member m where m.memberType = :memberType";
            List<Member> result = em.createQuery(query, Member.class)
                    .setParameter("memberType", MemberType.ADMIN)
                    .getResultList();

            for (Member m : result) {
                System.out.println("m = " + m.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
