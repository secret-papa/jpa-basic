package hellojpa;

import hellojpa.domain.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class JapMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            List<Member> resultList = em.createQuery(
                    "SELECT m From Member m WHERE m.name LIKE '%kim%'",
                    Member.class
            ).getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
