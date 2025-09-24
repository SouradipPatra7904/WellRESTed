package souradippatra.WellRESTed.web;

import souradippatra.WellRESTed.dto.SleepSessionDto;
import souradippatra.WellRESTed.service.SleepSessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.Set;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/sleep-sessions")
public class SleepSessionController {


    private final SleepSessionService service;


    public SleepSessionController(SleepSessionService service) {
        this.service = service;
    }


    // Standard pageable listing (GET)
    @GetMapping
    public ResponseEntity < CollectionModel < EntityModel < SleepSessionDto >>> listSessions(
        @RequestParam(required = false) Long userId,
        Pageable pageable
    ) {
        Page < SleepSessionDto > page = service.list(userId, pageable);
        List < EntityModel < SleepSessionDto >> items = page.stream()
            .map(dto -> EntityModel.of(dto,
                linkTo(methodOn(SleepSessionController.class).getById(dto.getId())).withSelfRel()))
            .toList();


        CollectionModel < EntityModel < SleepSessionDto >> coll = CollectionModel.of(items,
            linkTo(methodOn(SleepSessionController.class).listSessions(userId, pageable)).withSelfRel());


        return ResponseEntity.ok(coll);
    }


    // Keyset / seek listing (GET with lastId)
    @GetMapping("/seek")
    public ResponseEntity < CollectionModel < EntityModel < SleepSessionDto >>> seek(
        @RequestParam Long userId,
        @RequestParam(required = false) Long lastId,
        @RequestParam(defaultValue = "20") int size
    ) {
        List < SleepSessionDto > items = service.seek(userId, lastId, size);
        List < EntityModel < SleepSessionDto >> resources = items.stream()
            .map(dto -> EntityModel.of(dto, linkTo(methodOn(SleepSessionController.class).getById(dto.getId())).withSelfRel()))
            .toList();
        CollectionModel < EntityModel < SleepSessionDto >> coll = CollectionModel.of(resources,
            linkTo(methodOn(SleepSessionController.class).seek(userId, lastId, size)).withSelfRel());
        return ResponseEntity.ok(coll);
    }


    @GetMapping("/{id}")
    public ResponseEntity < EntityModel < SleepSessionDto >> getById(@PathVariable Long id) {
        SleepSessionDto dto = service.getById(id);
        EntityModel < SleepSessionDto > model = EntityModel.of(dto,
            linkTo(methodOn(SleepSessionController.class).getById(id)).withSelfRel(),
            linkTo(methodOn(SleepSessionController.class).listSessions(dto.getUserId(), Pageable.ofSize(20))).withRel("collection"),
            linkTo(methodOn(SleepSessionController.class).delete(id)).withRel("delete")
        );
        return ResponseEntity.ok(model);
    }


    @PostMapping
    public ResponseEntity < EntityModel < SleepSessionDto >> create(@Validated @RequestBody SleepSessionDto dto) {
        SleepSessionDto created = service.create(dto);
        EntityModel < SleepSessionDto > model = EntityModel.of(created,
            linkTo(methodOn(SleepSessionController.class).getById(created.getId())).withSelfRel());


        URI location = linkTo(methodOn(SleepSessionController.class).getById(created.getId())).toUri();
        return ResponseEntity.created(location).body(model);
    }


    @PutMapping("/{id}")
    public ResponseEntity < EntityModel < SleepSessionDto >> replace(@PathVariable Long id, @Validated @RequestBody SleepSessionDto dto) {
        SleepSessionDto replaced = service.replace(id, dto);
        EntityModel < SleepSessionDto > model = EntityModel.of(replaced,
            linkTo(methodOn(SleepSessionController.class).getById(replaced.getId())).withSelfRel());
        return ResponseEntity.ok(model);
    }


    // PATCH: partial update using DTO (fields not present are ignored)
    @PatchMapping("/{id}")
    public ResponseEntity < EntityModel < SleepSessionDto >> patch(@PathVariable Long id, @RequestBody SleepSessionDto partial) {
        SleepSessionDto patched = service.patch(id, partial);
        EntityModel < SleepSessionDto > model = EntityModel.of(patched,
            linkTo(methodOn(SleepSessionController.class).getById(patched.getId())).withSelfRel());
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < Void > delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    // HEAD: existence check
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity < Void > head(@PathVariable Long id) {
        try {
            service.getById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    // OPTIONS: return allowed methods for collection
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity < Void > options() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAllow(Set.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS, HttpMethod.HEAD));
        return new ResponseEntity < > (headers, HttpStatus.OK);
    }
}