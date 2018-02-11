package su.vfe.foodmanager.web.controller.family;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import su.vfe.foodmanager.model.Family;
import su.vfe.foodmanager.service.FamilyService;

@RestController
@RequestMapping(value = FamilyRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class FamilyRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/profile/families";

    @Autowired
    private FamilyService service;

    @GetMapping("/{id}")
    public Family get(@PathVariable("id") int id) {
        log.info("get family {}", id);
        return service.get(id);
    }
}