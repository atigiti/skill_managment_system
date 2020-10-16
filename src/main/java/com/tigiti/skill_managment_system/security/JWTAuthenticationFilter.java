package com.tigiti.skill_managment_system.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigiti.skill_managment_system.enteties.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// c'est un filtre qui va intervenir dans la partie d'authentification
// il doit hérité de la classe UserbalePasswordAuthenticationFilter
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // pn va pas l'injecter automatiquement car cette objet sera envoyé par Spring Security dans la classe de configuration qu'on a crée
    // il faut utiliser le consutructeur
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // si on veut envoyer les données en format www.URL.Encodded càd username = "..." and password="..."
         /*   String username = request.getParameter("username");
            String password = request.getParameter("password"); */

        // les données envoyés par le client sont de format JSon, il faut deserialiser le username et le password
        // les données on va les desérialiser dans un objet AppUser qu'on a crée
        AppUser appUser;
        // Jackson :  respnsable de prendre les données Json et les stocke dans des objets Java
        // l'attribue request de Filtre puisque il herite de UserPasswordAuthentication
        try{
            appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
        }
        catch(Exception e){
            // e.printStackTrace();
            // pour l'utilisateur reçoit quelque chose
            throw new RuntimeException(e.getMessage());
        }

        System.out.println("*************************************");
        System.out.println("Username : "+ appUser.getUserName());
        System.out.println("password : "+ appUser.getPassword());

        // envoyer à spring les données username et password envoyés par user
        return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(appUser.getUserName(), appUser.getPassword())
        );
    }


    // là on génére le Token
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


        // dans l'argument authResult on trouve le resultat d'authentification fournit par spring security
        // spring donne au filtre authResult

        // User de Spring Security Details
        // User de Spring contient les information sur l'uilisateur authentifié
        User springUser =(User) authResult.getPrincipal();

        // Génerer le Token
        String jwtToken = Jwts.builder()

                                .setSubject(springUser.getUsername())
                                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)  // type d'algorithme
                                .claim("roles", springUser.getAuthorities()) // serialiser automatiquement en format JSON
                                .compact();

        // Mettre le Token dans la response
        // la token envoyé par le client aprés doit contient le prefixe
        // c'est la seule modification que l'utilisatuer peut le faire sur le Token
        // et ce prefixe doit être identique à ce qui est dans la partie backend
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwtToken);


        // on peut verifier le fonctionnement de cette methode dans postmane
        // aprés l'authentification, dans le Response Header on trouve bien que le Token à été bien envoyés
        // sous le nom Authorisation
        // on peut utiliser jwt.io pour verifier le token voir 40:00 video 6 pensé securité web et mobile


    }
}
