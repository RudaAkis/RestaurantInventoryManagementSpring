package lt.javau12.RestaurantInventoryManager.controllers;

import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.LoginRequest;
import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.LoginResponse;
import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.SignupRequest;
import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.UserDisplayDTO;
import lt.javau12.RestaurantInventoryManager.entities.Role;
import lt.javau12.RestaurantInventoryManager.entities.User;
import lt.javau12.RestaurantInventoryManager.mappers.UserMapper;
import lt.javau12.RestaurantInventoryManager.repositories.UserRepository;
import lt.javau12.RestaurantInventoryManager.security.JwtUtils;
import lt.javau12.RestaurantInventoryManager.services.AuthService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final UserRepository userRepo;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;

    public AuthController(AuthService authService, UserRepository userRepo,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils, UserMapper userMapper) {
        this.authService = authService;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDisplayDTO>> getAllUsers(){
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @PostMapping("/create/employee") // url /auth/create/employee
    public ResponseEntity<UserDisplayDTO> createUser(@RequestBody SignupRequest request){
        if (!request.getFirstPassword().equals(request.getRepeatPassword())){
            throw new RuntimeException("Password do not match");
        }
        User user = new User(null, request.getUsername(), request.getLastname(), request.getEmail(),
                request.getUsername(), passwordEncoder.encode(request.getFirstPassword()), request.getRole(), false);
        User createdUser = userRepo.save(user);

        return ResponseEntity.ok(userMapper.toDTO(createdUser));
    }

    @PostMapping("/create/manager") // url /auth/create/manager
    public ResponseEntity<String> createManager(@RequestBody SignupRequest request){
        if (!request.getFirstPassword().equals(request.getRepeatPassword())){
            throw new RuntimeException("Password do not match");
        }
        User user = new User(null, request.getUsername(), request.getLastname(), request.getEmail(),
                request.getUsername(), passwordEncoder.encode(request.getFirstPassword()), Role.MANAGER, false);
        userRepo.save(user);

        return ResponseEntity.ok("Manager Registered Successfully");
    }

    @PostMapping("/create/admin") // url /auth/create/admin
    public ResponseEntity<String> createAdmin(@RequestBody SignupRequest request){
        if (!request.getFirstPassword().equals(request.getRepeatPassword())){
            throw new RuntimeException("Password do not match");
        }
        User user = new User(null, request.getUsername(), request.getLastname(), request.getEmail(),
                request.getUsername(), passwordEncoder.encode(request.getFirstPassword()), Role.ADMIN, false);
        userRepo.save(user);

        return ResponseEntity.ok("Admin Registered Successfully");
    }

    @PostMapping("/login") // url /auth/login
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDisplayDTO> update(@RequestBody SignupRequest signupRequest, @PathVariable Long id){
        return ResponseEntity.ok(authService.updateUser(signupRequest, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return authService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
