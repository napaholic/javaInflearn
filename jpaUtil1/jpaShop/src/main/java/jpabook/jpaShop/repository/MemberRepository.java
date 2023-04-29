package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository 어노테이션 사용시 스프링이 컴포넌트 스캔에 의해 스프링빈으로 등록되고 관리됨
@Repository
@RequiredArgsConstructor
public class MemberRepository {ㄹ

	// @PersistenceContext 어노테이션을 사용하면 엔티티 매니저를 주입받을 수 있다.
	private final EntityManager em;

//	public MemberRepository(EntityManager em) { //@RequiredArgsConstructor 가 final 붙은 필드만 생성자 만들어줌
//		this.em = em;
//	}

	public void save(Member member) {
		em.persist(member);
	}

	// 단건 조회
	// 첫번째 파라미터는 조회할 엔티티 타입, 두번째 파라미터는 식별자
	// 조회할 엔티티 타입과 식별자를 전달하면 해당 엔티티를 조회할 수 있다.
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
			.getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
			.setParameter("name", name)
			.getResultList();
	}
}
