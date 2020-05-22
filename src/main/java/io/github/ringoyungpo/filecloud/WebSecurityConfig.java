package io.github.ringoyungpo.filecloud;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()
                .antMatchers("/").access(AuthenticatedVoter.IS_AUTHENTICATED_ANONYMOUSLY);
    }
}