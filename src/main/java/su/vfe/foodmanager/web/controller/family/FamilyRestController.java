package su.vfe.foodmanager.web.controller.family;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import su.vfe.foodmanager.model.Family;
import su.vfe.foodmanager.service.FamilyService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import static su.vfe.foodmanager.util.ValidationUtil.*;

@RestController
@RequestMapping(value = FamilyRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class FamilyRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/families";

    @Autowired
    private FamilyService service;

    @GetMapping("/{id}")
    public Family get(@PathVariable("id") int id) {
        log.info("get family {}", id);
        return service.get(id);
    }

    @GetMapping
    public List<Family> getAll() {
        log.info("getAll families");
        return service.getAll();
    }

    //TODO: Maybe I delete it later (not necessary with EAGER loading)
    @GetMapping("/full/{id}")
    public Family getWithUsers(@PathVariable("id") int id) {
        log.info("get family {} with users", id);
        return service.getWithUsers(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete family {}", id);
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Family> createWithLocation(@Valid @RequestBody Family family) {
        checkNew(family);
        log.info("create {}", family);

        Family created = service.create(family);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Family family, @PathVariable("id") int id) {
        assureIdConsistent(family, id);
        log.info("update {}", family);
        service.update(family);
    }

    @PatchMapping(value = "/{id}/remove/user/{userId}")
    public void removeUser(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        log.info("remove User with id={} from Family with id={}", userId, id);
        service.removeUser(id, userId);
    }

    @PatchMapping(value = "/{id}/add/user/{userId}")
    public void addUser(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        log.info("add User with id={} to Family with id={}", userId, id);
        service.addUser(id, userId);
    }
}