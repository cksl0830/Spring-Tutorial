        
# 인프런 김영한 선생님 강의를 들으며 공부한 내용 정리 

## < 스프링 기초 개념 >        
![image](https://user-images.githubusercontent.com/60590737/155117646-75dd041b-94f0-4f9d-a92d-48de7fe8fd9b.png)
      
- ## API : @ResponseBody 
> ![image](https://user-images.githubusercontent.com/60590737/155117760-9d7ee4d5-5a8f-4602-92c8-e7126824f08e.png)

```
1. HTTP의 BODY에 문자 내용을 직접 반환
2. viewResolver 대신에 HttpMessageConverter 가 동작
3. 기본 문자처리: StringHttpMessageConverter
4. 기본 객체처리: MappingJackson2HttpMessageConverter
```

- #### Testcase 작성시
```
//given 어떤 데이터를 기반으로 하느냐
//when 검증하는 부분
//then 결과 
```

## < 스프링 빈과 의존관계 >        
#### 1. 컴포넌트 스캔과 자동 의존관계 설정        
- Controller -> Service -> Repository (같은 패키지 안에서 컴포넌트 스캔을 통해 의존관계 성립)      
:: @Autowired 를 통해 주입      
:: 싱글톤으로 등록 (같은 스프링 빈이면 모두 같은 인스턴스)

#### 2. 자바 코드로 직접 스프링 빈 등록하기    
- 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.     
- 그러나 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다. 

#### 3. 회원 등록 및 조회 컨트롤러 (MVC)     
> 컨트롤러는 정적파읿보다 우선순위가 높기에 먼저 실행 

- 등록 ( Form 객체를 통해 데이터 전달 ) 
```
@PostMapping(value = "/members/new")
public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());
    memberService.join(member);
    return "redirect:/";
}
```

- 조회 ( model 을 통해 view에 값 넘겨주기 ) 
```
@GetMapping(value = "/members")
public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
}
```
