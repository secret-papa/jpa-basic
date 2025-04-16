package hellojpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            비 영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("Hello");

//            영속 상태
            System.out.println("=====BEFORE=====");
            em.persist(member);
            System.out.println("=====AFTER=====");
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("Hello JPA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
