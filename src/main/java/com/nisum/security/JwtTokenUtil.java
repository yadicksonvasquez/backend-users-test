/**
 * 
 */
package com.nisum.security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


/**
 * @author yadickson
 *
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class JwtTokenUtil {

	@Autowired
	private Environment env;

	/**
	 * Generate token with hard code secret
	 * 
	 * @param user
	 * @return
	 */
	public String generateToken(UserDetails user) {

		SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getProperty("secretkey")));

		return Jwts.builder().setSubject(user.getUsername()).setIssuer(env.getProperty("issuer"))
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()
						+ Integer.parseInt(env.getProperty("access_token_validate_seconds")) * 1000))
				.signWith(secretKey, SignatureAlgorithm.HS256).compact();
	}

	public String getUsername(String token) {
		SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getProperty("secretkey")));

		JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();

		Claims claims = parser.parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	public Date getExpirationDate(String token) {
		SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getProperty("secretkey")));

		JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();

		Claims claims = parser.parseClaimsJws(token).getBody();

		return claims.getExpiration();
	}

	public boolean validate(String token) {
		try {
			SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getProperty("secretkey")));

			JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();

			parser.parseClaimsJws(token);
			
			return true;
		} catch (SecurityException ex) {
			log.error("Invalid JWT signature - {}", ex.getMessage());
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token - {}", ex.getMessage());
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token - {}", ex.getMessage());
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token - {}", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty - {}", ex.getMessage());
		}
		return false;
	}

}
