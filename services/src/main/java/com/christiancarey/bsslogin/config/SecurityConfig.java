package com.christiancarey.bsslogin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//            .ignoring()
//                .antMatchers("/**/*.css", "/**/*.png", "/**/*.gif", "/**/*.jpg", "/**/*.js", "/**/*.html");
//    }
//    
//    @Override
//    public void configure (HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/api/info/**", "/favicon.ico").permitAll()
//                .antMatchers("/api/**").authenticated()
//            .and()
//            .rememberMe()
//            .and()
//            .logout()
//                .logoutUrl("/signout")
//                .logoutSuccessUrl("/#/login")
//                .deleteCookies("JSESSIONID")
//            .and()
//            .csrf().disable();
//    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/**").fullyAuthenticated()
				.and()
			.formLogin();
	}
	
	@Configuration
	protected static class AuthenticationConfiguration extends
			GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.ldapAuthentication()
					.userDnPatterns("uid={0},ou=people")
					.groupSearchBase("ou=groups")
					.groupSearchFilter("(member={0})")
					.userSearchBase("ou=groups")
					.userSearchFilter("(uid={0})")
					.contextSource()
						.url("ldap://192.168.56.27:389/dc=springframework,dc=org");
		}
	}
}
