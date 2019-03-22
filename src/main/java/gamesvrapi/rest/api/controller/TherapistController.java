package gamesvrapi.rest.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import gamesvrapi.rest.api.model.TherapistEntity;
import gamesvrapi.rest.api.service.TherapistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/therapist")
public class TherapistController {

    private final TherapistService service;

    //@RequestHeader("Authorization") final String token
    @GetMapping
    public List<TherapistEntity> getAllTherapists() {
//        String[] splitted = token.split(" ");
//        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        String decodedToken1 = StringUtils.newStringUtf8(Base64.decodeBase64(JWT.decode(splitted[1]).getPayload()));
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Map<String,Object> map = mapper.readValue(decodedToken1, Map.class);
////            log.info(map.get());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return this.service.getAllTherapists();
    }

    @PostMapping
    public TherapistEntity createTherapist(@Valid @RequestBody final TherapistEntity therapist) {
        return this.service.createTherapist(therapist);
    }

    @GetMapping("/{id}")
    public TherapistEntity getTherapistById(@PathVariable("id") final Long id) {
        return this.service.getTherapistById(id);
    }

}
