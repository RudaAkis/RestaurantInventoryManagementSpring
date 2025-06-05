package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.entities.User;
import lt.javau12.RestaurantInventoryManager.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class InventoryUserDetailsService  implements UserDetailsService{

    UserRepository userRepository;

    public InventoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("UserNotFound") );

        return org.springframework.security.core.userdetails.User.builder()
                .username( user.getUsername())
                .password( user.getPassword())
                .authorities( "ROLE_" + user.getRole().name())
                .disabled( user.isAccountLocked())
                .build();

    }
}
