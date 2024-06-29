package com.unla.grupo22.tpc.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo22.tpc.entities.UserRole;
import com.unla.grupo22.tpc.repositories.IUserRepository;
import com.unla.grupo22.tpc.service.IUserService;


@Service("userService")
public class UserService implements IUserService, UserDetailsService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    @Override
    public Optional<com.unla.grupo22.tpc.entities.User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<com.unla.grupo22.tpc.entities.User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsernameAndFetchUserRolesEagerly(username));
    }

    @Override
    public com.unla.grupo22.tpc.entities.User save(com.unla.grupo22.tpc.entities.User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(com.unla.grupo22.tpc.entities.User user) {
        userRepository.delete(user);
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.unla.grupo22.tpc.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
    }

    private User buildUser(com.unla.grupo22.tpc.entities.User user, List<GrantedAuthority> grantedAuthorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
                        true, true, true, // accountNonExpired, credentialsNonExpired, accountNonLocked
                        grantedAuthorities);
    }

    private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRole userRole : userRoles) {
            String role = userRole.getRole();
            System.out.println("Role: " + role); // Añadir log para depuración
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return new ArrayList<>(grantedAuthorities);
    }
}
