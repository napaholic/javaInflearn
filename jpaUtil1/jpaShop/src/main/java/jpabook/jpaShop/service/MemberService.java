package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행되어야 한다.
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	// 생성자가 하나만 있으면 @Autowired를 생략할 수 있다.
	// 스프링 빈에 등록되어 있는 MemberRepository를 주입받는다.
	// 생성자 주입을 권장한다.
	// 테스트 코드 작성시에도 생성자 주입을 권장한다.
	//
//	@Autowired// 생성자가 하나만 있으면 @Autowired를 생략할 수 있다.
//	public MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}

	// 회원 가입
	// readOnly = false는 기본값이다.
	// 쓰기는 readOnly = false를 사용해야 한다.
	@Transactional(readOnly = false)// 조회에서는 readOnly = true를 사용하면 성능이 최적화된다.
	public Long join(Member member) {
		// 중복 회원 검증
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	// 중복 회원 검증
	// 동시에 같은 이름으로 회원 가입을 시도하면 동시에 중복 회원 검증을 통과할 수 있다.
	// 이를 방지하기 위해서는 데이터베이스의 Member의 name 컬럼에 유니크 제약조건을 걸어주어야 한다.
	// 하지만 유니크 제약조건을 걸어주면 예외가 발생하므로 예외를 잡아서 처리해주어야 한다.
	// 이를 위해 MemberRepository에 findByName() 메서드를 추가하고,
	// MemberService에 validateDuplicateMember() 메서드를 추가한다.
	// 이렇게 하면 동시에 같은 이름으로 회원 가입을 시도하면 동시에 중복 회원 검증을 통과할 수 없다.
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}


	// 회원 전체 조회

	@Transactional(readOnly = true) // 조회할 때는 readOnly = true를 사용하면 성능이 최적화된다.
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}


	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}


}
