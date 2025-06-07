package lt.javau12.RestaurantInventoryManager.controllers;

import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.LoginRequest;
import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.LoginResponse;
import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.SignupRequest;
import lt.javau12.RestaurantInventoryManager.entities.Role;
import lt.javau12.RestaurantInventoryManager.entities.User;
import lt.javau12.RestaurantInventoryManager.repositories.UserRepository;
import lt.javau12.RestaurantInventoryManager.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    public AuthController(UserRepository userRepo,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/create/user") // url /auth/create_user
    public ResponseEntity<String> createUser(@RequestBody SignupRequest request){
        if (!request.getFirstPassword().equals(request.getRepeatPassword())){
            throw new RuntimeException("Password do not match");
        }
        User user = new User(null, request.getUsername(), request.getLastname(), request.getEmail(),
                request.getUsername(), passwordEncoder.encode(request.getFirstPassword()), Role.USER, false);
        userRepo.save(user);

        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/create/manager") // url /auth/create_manager
    public ResponseEntity<String> createManager(@RequestBody SignupRequest request){
        if (!request.getFirstPassword().equals(request.getRepeatPassword())){
            throw new RuntimeException("Password do not match");
        }
        User user = new User(null, request.getUsername(), request.getLastname(), request.getEmail(),
                request.getUsername(), passwordEncoder.encode(request.getFirstPassword()), Role.MANAGER, false);
        userRepo.save(user);

        return ResponseEntity.ok("Manager Registered Successfully");
    }

    @PostMapping("/create/admin") // url /auth/create_admin
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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepo.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found with username " + request.getUsername()));

        String jwt = jwtUtils.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }




}
