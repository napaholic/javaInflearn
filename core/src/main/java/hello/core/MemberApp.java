package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        // 구현 객체를 선택하고, 구현 객체를 생성하고, 연결하는 책임을
        // MemberApp이 가지고 있음
        // MemberApp은 MemberService 인터페이스에만 의존하고 있음
        // MemberService 인터페이스에만 의존하고 있기 때문에
        // MemberService 인터페이스를 구현한 어떤 구현 객체로 변경해도
        // MemberApp은 전혀 영향을 받지 않음
        // MemberApp은 DIP를 잘 지키고 있음
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
