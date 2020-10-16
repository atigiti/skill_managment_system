package com.tigiti.skill_managment_system.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// se filtre sera executer à chaque requéte pour valider le token envoyer par l'utilisateur
// et envoyer le resultat à notre contoller Spring Security
public class JWTAutorisationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       // si on a essayé de changer le token dans la partie user, par exemple, on utiliser jwt.io
        // pour ajouter des rôles ça va pas marcher car le signature se calcule en fonction de secret et aussi
        // en fonction de payload et header de token
        // donc on va pas garder la même signature si on a changer quelque chose dans le token
        // et de coup ça va pas marché
        // la formule de signature est
            /*
            *   HMACSHA256 (
            *                   base64Encode(header)+"."+
            *                   base64Encode(payload)+"."+
            *                   secret
            *               )
            *
            * */



        // Configuration pour que Angular puisse utiliser la partie d'authentification
        // problème de Cross Origin
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        // les entêtes autorisés à envoyer à partir de Angular
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-RequestHeaders,authorization");
        // les headers que la parite Front peut les lire
        httpServletResponse.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        //on est dans un autre domaine que la partie backend
        // si par exemple en veut envoyer une requête POST, avant ça Angular envoiy une requête de Type OPTIONS
        // OPTION : permet au client HTTP d'intéroger le serveur , pour lui fournir les options de communications
        // à utiliser pour une ressource ciblée ou un ensemble de ressources
        // pour une requête de ce type il faut la permettere
        if(httpServletRequest.getMethod().equals("OPTIONS")){
            // il va envoyer les header qu'on specifier dans httpServleteResponse.addHeader(...)
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            String jwt = httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);
            if(jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                System.out.println("Token invalid");
                return;
            }
            System.out.println("jwt ===> "+ jwt);
            // Tester le Token
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,"")) // pour supprimer le prefixe
                    .getBody(); // recuperer le contenu de token

            String username = claims.getSubject(); //on a stocker le username dans le le claim subject lors de la génération de Token
            ArrayList<Map<String,String>> roles= (ArrayList<Map<String, String>>) claims.get("roles");
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
            });

            // on a pas besoins de password par ce qu'il n'existe pas dans le token
            // et jwt suppose que l'utilisateur est déjà authentifié ; il va juste verifier est ce que le token envoyé est valide on pas

            UsernamePasswordAuthenticationToken springUser = new UsernamePasswordAuthenticationToken(username, null, authorities);
            // charger le contexte de securité de Spring => "Spring voila l'utilisateur qui a envoyé la requête voila son identité"
            SecurityContextHolder.getContext().setAuthentication(springUser);
            //
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }



    }
}
