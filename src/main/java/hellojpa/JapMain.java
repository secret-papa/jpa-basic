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
            Member member = new Member();
            member.setName("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            List<Member> resultList = em.createQuery(
                    "select m from Member m",
                        Member.class
                    )
                    .getResultList();

            Member findMember = resultList.get(0);
            findMember.setAge(11);

//            결과가 하나인 경우
//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 10", Member.class);
//            query.getSingleResult();

            for (Member member1: resultList) {
                System.out.println("member1 = " + member1);
            }

//            Type을 정의할 수 없을 경우 new를 활용할 수 있다
            List<MemberDto> resultList1 = em.createQuery(
                            "select new hellojpa.domain.MemberDto(m.name, m.age) from Member m",
                            MemberDto.class
                    )
                    .getResultList();

            MemberDto memberDto = resultList1.get(0);
            System.out.println("memberDto.name = " + memberDto.getName());
            System.out.println("memberDto.age = " + memberDto.getAge());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
