package su.vfe.foodmanager.web.controller.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import su.vfe.foodmanager.AuthorizedUser;
import su.vfe.foodmanager.model.Item;
import su.vfe.foodmanager.service.ItemService;
import su.vfe.foodmanager.util.DateTimeUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import static su.vfe.foodmanager.util.ValidationUtil.assureIdConsistent;
import static su.vfe.foodmanager.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/profile/items";

    @Autowired
    private ItemService service;

    @GetMapping("/{id}")
    public Item get(@PathVariable("id") int id) {
        int familyId = AuthorizedUser.family_id();
        log.info("get item {} for family {}", id, familyId);
        return service.get(id, familyId);
    }

    @GetMapping
    public List<Item> getAll() {
        int familyId = AuthorizedUser.family_id();
        log.info("getAll items for family {}", familyId);
        return service.getAll(familyId);
    }

    @GetMapping(value = "/filter")
    public List<Item> getFiltered(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(value = "startDate", required = false) LocalDateTime startDate,
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(value = "endDate", required = false) LocalDateTime endDate,
                                  @RequestParam(value = "closed", required = false) boolean closed) {
        int familyId = AuthorizedUser.family_id();
        log.info("getBetween dates({} - {}) with status {}) for family {}", startDate, endDate, closed, familyId);

        return service.getBetweenByStatus(
                startDate != null ? startDate : DateTimeUtil.MIN_DATE_TIME,
                endDate != null ? endDate : DateTimeUtil.MAX_DATE_TIME, closed, familyId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        int familyId = AuthorizedUser.family_id();
        log.info("delete item {} for family {}", id, familyId);
        service.delete(id, familyId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Item item, @PathVariable("id") int id) {
        int familyId = AuthorizedUser.family_id();
        assureIdConsistent(item, id);
        log.info("update {}", item);
        service.update(item, familyId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createWithLocation(@Valid @RequestBody Item item) {
        int familyId = AuthorizedUser.family_id();
        checkNew(item);
        log.info("create {} for family {}", item, familyId);

        Item created = service.create(item, familyId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PatchMapping(value = "/{id}")
    public void close(@PathVariable("id") int id, @RequestParam("closed") boolean closed) {
        int familyId = AuthorizedUser.family_id();
        log.info("set closed for item {} to {}", id, closed);
        service.close(id, closed, familyId);
    }
}