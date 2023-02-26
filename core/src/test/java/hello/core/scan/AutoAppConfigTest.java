package hello.core.scan;

import hello.core.member.AutoAppconfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
public class AutoAppConfigTest {

	@Test
	void basicScan() {
		 AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppconfig.AutoAppConfig.class);
		 MemberService memberService = ac.getBean(MemberService.class);
		//
		assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
