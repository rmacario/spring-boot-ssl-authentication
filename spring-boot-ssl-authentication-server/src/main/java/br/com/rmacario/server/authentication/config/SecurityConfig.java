package br.com.rmacario.server.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.x509()
					.subjectPrincipalRegex("CN=(.*?)(?:,|$)")
					.userDetailsService(userDetailsService());
	}	
	
	@Bean
    public UserDetailsService userDetailsService() {
        return (username -> {
        	if (username.equals("client-test")) {
                return new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER_SSL"));
                
            } else {
            	throw new UsernameNotFoundException("Erro ao validar client.");
            }
        });
    }
}
