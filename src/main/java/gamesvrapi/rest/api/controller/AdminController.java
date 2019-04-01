package gamesvrapi.rest.api.controller;

import static gamesvrapi.rest.api.security.SecurityConstants.HEADER_STRING;

import java.util.List;

import javax.validation.Valid;

import gamesvrapi.rest.api.model.AdminEntity;
import gamesvrapi.rest.api.model.TherapistEntity;
import gamesvrapi.rest.api.service.AdminService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private final AdminService adminService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AdminEntity create (@Valid @RequestBody @NonNull final AdminEntity admin) {
        return this.adminService.create(admin);
    }

    @GetMapping(path = "/therapists", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TherapistEntity> getTherapists (@Valid @RequestHeader(HEADER_STRING) final String token) {
        return this.adminService.getTherapists(token);
    }

    // CRUD Game route

}
