package com.vitah.ucenter.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author vitah
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // 使用的是JWT，所以不需要CSRF
            .csrf().disable()
            // 使用token，不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // 所有的请求都不需要验证token
            .authorizeRequests().anyRequest().permitAll();

        // 配置需要验证token的路由
        httpSecurity
            .antMatcher("/auth/check")
            .antMatcher("/user/password/modify")
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .addFilterBefore(new TokenFilter(), BasicAuthenticationFilter.class);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
