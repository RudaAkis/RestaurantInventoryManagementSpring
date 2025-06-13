package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.LoginRequest;
import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.LoginResponse;
import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.SignupRequest;
import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.UserDisplayDTO;
import lt.javau12.RestaurantInventoryManager.entities.Role;
import lt.javau12.RestaurantInventoryManager.entities.User;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.UserNotFoundException;
import lt.javau12.RestaurantInventoryManager.mappers.UserMapper;
import lt.javau12.RestaurantInventoryManager.repositories.UserRepository;
import lt.javau12.RestaurantInventoryManager.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepo;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final UserMapper userMapper;

    public AuthService(UserRepository userRepo,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.userMapper = userMapper;
    }



    public LoginResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepo.findByUsername(request.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found with username " + request.getUsername()));

        String jwt = jwtUtils.generateToken(user);

        return new LoginResponse(jwt);
    }

    public List<UserDisplayDTO> getAllUsers(){
        List<User> users = userRepo.findAll();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public UserDisplayDTO updateUser(SignupRequest signupRequest, Long id){
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User was not found with id " + id));

        user.setFirstname(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setEmail(signupRequest.getEmail());
        user.setRole(signupRequest.getRole());
        user.setUsername(signupRequest.getUsername());

        User updatedUser = userRepo.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public boolean delete(Long id){
        if (userRepo.existsById(id)){
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
