package shop.mtcoding.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import shop.mtcoding.bank.config.enums.UserEnum;
import shop.mtcoding.bank.handler.LoginHandler;

//SecurityFilterChain

@Configuration //여기선 웬만해선 생성자주입을 쓰지않는것이 좋다 -> Autowired를 사용할 것!
public class SecurityConfig {

    @Autowired
    private LoginHandler loginHandler;

    public BCryptPasswordEncoder passwordEncoder() { //Spring Security는 회원가입할 때 비밀번호가 Hash가 아니면 로그인이 막히게된다
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChat(HttpSecurity http) throws Exception { //HttpSecurity에는 필터가 다들어가있음
        //여기서 커스텀할 것..!
        http.headers().frameOptions().disable();
        http.csrf().disable(); //csrf =나중엔 없애고 테스트하는게 좋음

        http.authorizeHttpRequests()
        .antMatchers("/api/transaction/**").authenticated()
        .antMatchers("/api/user/**").authenticated()
        .antMatchers("/api/account/**").authenticated()
        .antMatchers("/api/admin/**").hasRole("ROLE_" + UserEnum.ADMIN)//어째서인지 앞에 prefix를 붙여줘야한다..
                .anyRequest().permitAll()
                .and()
                .formLogin() //type : x-www-form-urlencoded (POST)
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/api/login")
                .successHandler(loginHandler)
                .failureHandler(loginHandler);
                //.defaultSuccessUrl("/") 원래는 페이지를 반환하는데 우리는 RestController여서 Data를 반환

        return http.build();
    }
}
