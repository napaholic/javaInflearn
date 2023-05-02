package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //스프링과 테스트 통합
@SpringBootTest //스프링 컨테이너 안에서 테스트 실행 Autowired 사용 위해
@Transactional //테스트 케이스에 있으면 테스트 시작 전에 트랜잭션 시작하고 테스트 완료 후에 항상 롤백
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	@Rollback(false)
	public void 회원가입() throws Exception {
		//given
		Member member = new Member();
		member.setName("kim");

		//when
		Long saveId = memberService.join(member);

		//then
		assertEquals(member, memberRepository.findOne(saveId));
	}

	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");

		//when
		memberService.join(member1);
//		try {
//			memberService.join(member2); //예외가 발생해야 한다.
//		} catch (IllegalStateException e) {
//			return;
//		}
		//expected = IllegalStateException.class 가 하위 join 이 던지는 예외를 잡아줌
		memberService.join(member2);

		//then
		fail("예외가 발생해야 한다.");
	}
}