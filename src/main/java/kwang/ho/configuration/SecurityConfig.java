package kwang.ho.configuration;

import kwang.ho.service.user.UserService;
import kwang.ho.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /* 로그인 실패 핸들러 의존성 주입 */
    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final UserServiceImpl userServiceImpl;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/bootstrap/**/*");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                    .antMatchers( "/login", "/join", "/resources/**").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한
                    // USER, ADMIN 접근 허용
                    .antMatchers("/manage").hasRole("ADMIN")
                    .anyRequest().authenticated()  //  나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                    .formLogin()
                    .loginPage("/login") // 로그인 페이지 링크
                    .loginProcessingUrl("/indexGo") // 로그인 URL(실제 파일 X)
                    .defaultSuccessUrl("/main") // 로그인 성공 후 리다이렉트 주소
                    .failureHandler(authenticationFailureHandler) // 로그인 실패 핸들러
                .and()
                    .logout()
                    .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID");;

    }

/*    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userServiceImpl);
        authenticationProvider.setPasswordEncoder(userServiceImpl.passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }*/

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
    }
}
