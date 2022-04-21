package com.jonathan.ApiSpringBoot.Service;

import com.jonathan.ApiSpringBoot.dao.UserDao;
import com.jonathan.ApiSpringBoot.domain.Rol;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import com.jonathan.ApiSpringBoot.domain.Users;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jonathan
 */
@Service("userDetailsService")
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userDao.findByUsername(username);
        
        if(users == null) {
            throw new UsernameNotFoundException(username);
        }
        
        var rols = new ArrayList<GrantedAuthority>();
        
        for(Rol rol: users.getRols()) {
            rols.add(new SimpleGrantedAuthority(rol.getName()));
        }
        
        return new User(users.getUsername(), users.getPassword(), rols);
    }
}
