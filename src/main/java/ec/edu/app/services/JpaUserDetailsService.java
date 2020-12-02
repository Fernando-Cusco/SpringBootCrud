package ec.edu.app.services;

import ec.edu.app.dao.IUsuarioDao;
import ec.edu.app.models.Rol;
import ec.edu.app.models.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailService")
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioDao usuarioDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if(usuario == null) {
            logger.info("Error el usuario no existe: "+username);
            throw new UsernameNotFoundException("El usuario: "+username+" no existe");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Rol rol: usuario.getRoles()) {
            logger.info(rol.getAuthority()+" rol del usuario: "+username);
            authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
        }
        if(authorities.isEmpty()) {
            logger.info("Error el usuario "+username+" no tiene roles asignado");
            throw new UsernameNotFoundException("Error el usuario "+username+" no tiene roles asignado");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnable(), true, true, true, authorities);
    }
}
