package com.example.rtu.auth;


import com.example.rtu.entity.Course;
import com.example.rtu.entity.User;
import com.example.rtu.exceptions.EntityNotFoundException;
import com.example.rtu.service.CourseService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/e-classroom/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request, HttpSession session) {
        AuthenticationResponse auth  = service.register(request);
        session.setAttribute("jwtToken", auth);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request, HttpSession session) {
        AuthenticationResponse auth  = service.authenticate(request);
        session.setAttribute("jwtToken", auth);
        return ResponseEntity.ok(auth);
    }






}