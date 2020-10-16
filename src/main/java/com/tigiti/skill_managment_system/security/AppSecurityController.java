package com.tigiti.skill_managment_system.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class AppSecurityController extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // builder pattern

        // il y a trois methode d'authentifacation dans Spring

        // 1  pour le teste
       /* auth.inMemoryAuthentication()
                .withUser("admin").password("1234").roles("Admin","User")
                .and()
                .withUser("user").password("user").roles("User");*/
        // 2  utilisation de JDBC auth, on utilise les requetes SQL pour récupere le username et la password
        // c'est pas la meilleure solution
        /* auth.jdbcAuthentication()
                .usersByUsernameQuery("select * from users ")
                .authoritiesByUsernameQuery("select * from roles where username = ...")
        */


        // 3  Auth basé sur UserDetailsService par
       auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);

      /*  auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(bCryptPasswordEncoder.encode("koko"))
                .roles("USER", "ADMIN");*/

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // descativer le CSRF Token fournit par spring par defaut
        http.csrf().disable();

        // Désactiver définitivement le système de session fournit par spring
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // formulaire d'authentification offert par spring
        http.formLogin();

        // pour personnaliser le form login
        //http.formLogin().loginPage("/myLoginPage");

        // mensionner que l'ajout d'une tasks nece   @Overridessite le role ADMIN
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/skills/**").hasAuthority("Admin");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/skills/**").permitAll();

        // EnterpriseAdmin Authority
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/addJob/**").hasAuthority("EnterpriseAdmin");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/addSkills/**").hasAuthority("EnterpriseAdmin");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/addCandidatToJob/**").hasAuthority("EnterpriseAdmin");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/getEnterpriseAdminDetails/**").hasAuthority("EnterpriseAdmin");

        // Expert Authority
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/setRequiredNote/**").hasAuthority("Expert");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/setEvaluation/**").hasAuthority("Expert");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/setAcquiredNote/**").hasAuthority("Expert");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/getExpertDetails/**").hasAuthority("Expert");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/getJobForRequiredNote/**").hasAuthority("Expert");

        // Candidat Authority
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/getCandidatDetails/**").hasAuthority("Candidat");


        // Allow for registration
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/registerEnterpriseAdmin/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/registerCandidat/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/registerExpert/**").permitAll();


        // Les requêtes qui necessite pas une authentification

        http.authorizeRequests().antMatchers("/login/**").permitAll();
        http.authorizeRequests().antMatchers("/registerEnterpriseAdmin/**").permitAll();
        http.authorizeRequests().antMatchers("/registerExpert/**").permitAll();
        http.authorizeRequests().antMatchers("/registeCandidat/**").permitAll();

        // c à d tous les requètes necessitent une authentification
        // http.authorizeRequests().anyRequest().authenticated();


        // Ajouter le Filtre en envoyant ke authenticationManager de Spring security
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAutorisationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
