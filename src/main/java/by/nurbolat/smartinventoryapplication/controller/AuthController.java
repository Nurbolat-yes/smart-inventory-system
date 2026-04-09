package by.nurbolat.smartinventoryapplication.controller;

import by.nurbolat.smartinventoryapplication.config.JwtUtil;
import by.nurbolat.smartinventoryapplication.dto.AuthRequest;
import by.nurbolat.smartinventoryapplication.entity.security.User;
import by.nurbolat.smartinventoryapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        String role = auth.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        String token = jwtUtil.generateToken(req.getUsername(), role);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req,
                                      @RequestParam(defaultValue = "Staff") String role) {
        User user = userService.registerUser(req.getUsername(), req.getPassword(), role);
        return ResponseEntity.ok(Map.of("userId", user.getUserId(), "username", user.getUsername(), "role", user.getRole()));
    }
}