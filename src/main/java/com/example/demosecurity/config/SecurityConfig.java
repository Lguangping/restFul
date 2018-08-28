package com.example.demosecurity.config;

import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;

import com.example.demosecurity.service.CustomUserService;
import com.example.demosecurity.service.MyFilterSecurityInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

//import com.qydata.eyesonly.security.UserServiceImpl;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * restFul功能加强
     */
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;


    @Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new CustomUserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()    // 定义那些url需要被保护,哪些不需要被保护
                .antMatchers("/logo.png","static/**","/lay/**","/font/**","/layui/**","/layui**","/images/**","/js/**","/css/**","/login/**","/assembly/**","/business/**")
                    .permitAll()    // 设置任何人都可以访问静态元素
                .antMatchers("/**")
                    .authenticated()    //任何请求登陆后都可以访问
                .anyRequest().authenticated() //任何请求,登录后可以访问
            .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/home",true)
                .permitAll()    //登陆后默认显示home页面
            .and()
                .logout().permitAll() //注销行为任意访问
            .and()
                .headers()
                .frameOptions()
                .sameOrigin()
            .and()
                .csrf()
                .disable(); //关闭csrf保护
        // restFul功能加强
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
            .csrf().disable();
        // @formatter:on
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserService()); //user Details Service验证
        // 在内存中生成两个用户和权限
//        auth.inMemoryAuthentication().withUser("user").password("123456").roles("USER").and().withUser("admin").password("123456").roles("USER","ADMIN");
    }

    /**
     * 加密
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) createDelegatingPasswordEncoder();
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());//new BCryptPasswordEncoder();
        return delegatingPasswordEncoder;
    }
}
