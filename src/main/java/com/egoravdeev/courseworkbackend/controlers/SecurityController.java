package com.egoravdeev.courseworkbackend.controlers;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/read")
    @Secured("ROLE_READ")
    public String read() {
        return "Read user";
    }

    @GetMapping("/write")
    @RolesAllowed("WRITE")
    public String write() {
        return "Write user";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyRole('WRITE', 'READ', 'DELETE')")
    public String delete() {
        return "Delete user";
    }

    @GetMapping("/user")
    @PostAuthorize("#username == authentication.principal.username")
    public String user(@RequestParam String username) {
        return "Hello " + username;
    }
}
