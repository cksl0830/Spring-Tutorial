package chan.chanspring;

import chan.chanspring.repository.MemberRepository;
import chan.chanspring.repository.MemoryMemberRepository;
import chan.chanspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
