package spring.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value = "one")
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);

//        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(User.withDefaultPasswordEncoder().username("khamit").password("khamit").roles("admin"))
//                .withUser(User.withDefaultPasswordEncoder().username("airat").password("airat").roles("HR"))
//                .withUser(User.withDefaultPasswordEncoder().username("kamil").password("kamil").roles("employee"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").hasAnyRole("admin","employee","HR")
                .antMatchers("/new").hasAnyRole("admin","HR")
                .antMatchers("/delete","/update").hasRole("admin")
                .and().formLogin().permitAll();

    }
}
