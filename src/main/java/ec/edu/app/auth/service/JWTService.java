package ec.edu.app.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collection;

public interface JWTService {

    public String crearToken(Authentication authentication) throws JsonProcessingException;

    public boolean validarToken(String token);

    public Claims getClaims(String token);

    public String obtenerUserName(String token);

    public Collection<? extends GrantedAuthority> obtenerRoles(String token) throws IOException;

    public String resolver(String token);

}
