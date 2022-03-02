
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

## < 스프링 데이터 JPA > 

#### 1. JDBC
```
- DataSource는 데이터베이스 커넥션을 획득할 때 사용하는 객체다. 스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 DataSource를 생성하고    
  스프링 빈으로 만들어둔다. 그래서 DI를 받을 수 있다.          
- 스프링의 DI을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
```
#### +) JDBC Template 
> 스프링 JdbcTemplate과 MyBatis 같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거해준다. 하지만 SQL은 직접 작성해야 한다.

#### 2. 통합 테스트
```
@SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다.     

@Transactional : 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다.     
                 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
```

#### 3. JPA
- build.gradle 파일에 JPA, h2 데이터베이스 관련 라이브러리 추가 ( spring-boot-starter-data-jpa )
- 스프링 부트에 JPA 설정 추가
- **JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.**


#### ⭐️ 4. 스프링 데이터 JPA

![image](https://user-images.githubusercontent.com/60590737/155499192-74e05834-5293-431f-a586-2aaf74802686.png)

```
1. 스프링 데이터 JPA가 SpringDataJpaMemberRepository(인터페이스)를 스프링 빈으로 자동 등록해준다.
2. 인터페이스를 통한 기본적인 CRUD
3. findByName() , findByEmail() 처럼 메서드 이름 만으로 조회 기능 제공 
4. 페이징 기능 자동 제공
```

#### 5. AOP 
**공통 관심사항과 핵심 관심사항을 분리**
- 원하는 곳에 공통 관심사항 적용 
- 프록시를 거쳐 실제로 이동 

![image](https://user-images.githubusercontent.com/60590737/155695986-38e0ac8c-ebdc-413f-9df9-63c4dd8eb0b7.png)
        
