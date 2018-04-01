package com.urservices.ambassade.config;

import com.urservices.ambassade.security.*;
import com.urservices.ambassade.security.jwt.*;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.annotation.PostConstruct;

@Configuration
@Import(SecurityProblemSupport.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserDetailsService userDetailsService;

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;

    private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService,TokenProvider tokenProvider,CorsFilter corsFilter, SecurityProblemSupport problemSupport) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
    }

    @PostConstruct
    public void init() {
        try {
            authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/app/**/*.{js,html}")
            .antMatchers("/i18n/**")
            .antMatchers("/content/**")
            .antMatchers("/swagger-ui/index.html")
            .antMatchers("/test/**")
            .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(problemSupport)
            .accessDeniedHandler(problemSupport)
        .and()
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/activate").permitAll()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/account/reset-password/init").permitAll()
            .antMatchers("/api/account/reset-password/finish").permitAll()
            .antMatchers("/api/profile-info").permitAll()
            .antMatchers("/api/**").authenticated()
            .antMatchers(HttpMethod.POST, "/api/caisses").hasAuthority(AuthoritiesConstants.CAISSE_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/caisses").hasAuthority(AuthoritiesConstants.CAISSE_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/caisses").hasAuthority(AuthoritiesConstants.CAISSE_MANAGER)
            .antMatchers(HttpMethod.POST, "/api/categories ").hasAuthority(AuthoritiesConstants.CATEGORIE_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/categories ").hasAuthority(AuthoritiesConstants.CATEGORIE_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/categories ").hasAuthority(AuthoritiesConstants.CATEGORIE_MANAGER)
            .antMatchers(HttpMethod.POST, "/api/donnees-actes").hasAuthority(AuthoritiesConstants.DONNEES_ACTE_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/donnees-actes").hasAuthority(AuthoritiesConstants.DONNEES_ACTE_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/donnees-actes").hasAuthority(AuthoritiesConstants.DONNEES_ACTE_MANAGER)
            .antMatchers(HttpMethod.POST, "/api/livres").hasAuthority(AuthoritiesConstants.LIVRE_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/livres").hasAuthority(AuthoritiesConstants.LIVRE_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/livres").hasAuthority(AuthoritiesConstants.LIVRE_MANAGER)
            .antMatchers(HttpMethod.POST, "/api/monnaies").hasAuthority(AuthoritiesConstants.MONNAIE_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/monnaies").hasAuthority(AuthoritiesConstants.MONNAIE_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/monnaies").hasAuthority(AuthoritiesConstants.MONNAIE_MANAGER)
            .antMatchers(HttpMethod.POST, "/api/montants").hasAuthority(AuthoritiesConstants.MONTANT_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/montants").hasAuthority(AuthoritiesConstants.MONTANT_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/montants").hasAuthority(AuthoritiesConstants.MONTANT_MANAGER)
            .antMatchers(HttpMethod.POST, "/api/passeports").hasAuthority(AuthoritiesConstants.PASSEPORT_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/passeports").hasAuthority(AuthoritiesConstants.PASSEPORT_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/passeports").hasAuthority(AuthoritiesConstants.PASSEPORT_MANAGER)
            .antMatchers(HttpMethod.POST, "/api/produits").hasAuthority(AuthoritiesConstants.PRODUIT_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/produits").hasAuthority(AuthoritiesConstants.PRODUIT_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/produits").hasAuthority(AuthoritiesConstants.PRODUIT_MANAGER)
            .antMatchers(HttpMethod.POST, "/api/rapatriements").hasAuthority(AuthoritiesConstants.RAPATRIEMENT_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/rapatriements").hasAuthority(AuthoritiesConstants.RAPATRIEMENT_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/rapatriements").hasAuthority(AuthoritiesConstants.RAPATRIEMENT_MANAGER)
            .antMatchers(HttpMethod.POST, "/api/visas").hasAuthority(AuthoritiesConstants.VISA_MANAGER)
            .antMatchers(HttpMethod.PUT, "/api/visas").hasAuthority(AuthoritiesConstants.VISA_MANAGER)
            .antMatchers(HttpMethod.DELETE, "/api/visas").hasAuthority(AuthoritiesConstants.VISA_MANAGER)
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/v2/api-docs/**").permitAll()
            .antMatchers("/swagger-resources/configuration/ui").permitAll()
            .antMatchers("/swagger-ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
        .and()
            .apply(securityConfigurerAdapter());

    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

}
