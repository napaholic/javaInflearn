package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImplTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
public class AutoAppConfigTest {

	@Test
	void basicScan() {
		 AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		 MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);

		OrderServiceImplTest orderService = ac.getBean(OrderServiceImplTest.class);
		MemberRepository memberRepository = ac.getBean(MemberRepository.class);
		System.out.println("memberRepository = " + memberRepository);
	}
}