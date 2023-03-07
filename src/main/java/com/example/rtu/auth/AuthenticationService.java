package com.example.rtu.auth;


import com.example.rtu.auth.token.Token;
import com.example.rtu.auth.token.TokenRepository;
import com.example.rtu.auth.token.TokenType;
import com.example.rtu.entity.Role;
import com.example.rtu.entity.User;
import com.example.rtu.repository.UserRepo;
import com.example.rtu.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {


    private final UserService userService;

    private final JWTService jwtService;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

   private final TokenRepository tokenRepository;








    public AuthenticationService(UserService userService, JWTService jwtService, UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }






    public AuthenticationResponse register(RegisterRequest request) {

        System.out.println(request.getName());
        System.out.println(request.getEmail());



              Role role = request.getRole() != null ? Role.valueOf(request.getRole()) : null;
              User user =   User.builder().name(request.getName())
             .email(request.getEmail())
             .role(role)
             .password(passwordEncoder.encode(request.getPassword()))
             .build();

        userRepo.save(user);

     var jwtToken = jwtService.generateToken(user);

     return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {

        var validUserTokens = tokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getUserId()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }



}

