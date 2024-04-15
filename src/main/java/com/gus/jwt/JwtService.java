package com.gus.jwt;


import com.gus.entity.UsuariosModel;
import com.gus.services.VariablesGlobalesService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtService {
@Autowired
    private VariablesGlobalesService variablesGlobalesService;


    public String generarToken(String username){

        Map<String,Object>claims=new HashMap<>();

        return createToken(claims,username);
    }


    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 6000 * 30))
                .signWith(getSingKey(), SignatureAlgorithm.HS512).compact();
    }

    private Key getSingKey(){

        byte[] keyBytes= Decoders.BASE64.decode(this.variablesGlobalesService.buscarPorId(2L).getValor());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){

        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){

        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);

        return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSingKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){

        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UsuariosModel userDetails){
        final String username=extractUsername(token);

        return (username.equals(userDetails.getCorreo())&& !isTokenExpired(token));
    }
}
