package com.dogancanokur.petClinic.web;

import com.dogancanokur.petClinic.exception.InternalServerException;
import com.dogancanokur.petClinic.exception.OwnerNotFoundException;
import com.dogancanokur.petClinic.model.Owner;
import com.dogancanokur.petClinic.services.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class PetClinicRestController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> getOwners() {
        try {
            List<Owner> owners = petClinicService.findOwners();
            return ResponseEntity.ok(owners);
        } catch (Exception e) {

            throw new InternalServerException(e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/owner", method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> getOwner(@RequestParam("ln") String lastname) {
        try {

            List<Owner> owners = petClinicService.findOwners(lastname);
            return ResponseEntity.ok(owners);
        } catch (Exception e) {

            throw new InternalServerException(e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/owner/{id}", method = RequestMethod.GET)
    public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
        try {

            Owner owner = petClinicService.findOwner(id);
            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundException e) {

            throw new OwnerNotFoundException(e);
//            return ResponseEntity.notFound().build();
        } catch (Exception e) {

            throw new InternalServerException(e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
        try {
            Long id = petClinicService.createOwner(owner);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {

            throw new InternalServerException(e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/owner/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOwner(@PathVariable Long id, @RequestBody Owner ownerRequest) {
        try {

            Owner owner = petClinicService.findOwner(id);

            owner.setFirstName(ownerRequest.getFirstName());
            owner.setLastName(ownerRequest.getLastName());

            petClinicService.updateOwner(owner);

            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundException e) {

            return createOwner(ownerRequest);
//            return ResponseEntity.notFound().build();
        } catch (Exception e) {

            throw new InternalServerException(e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/owner/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOwner(@PathVariable Long id) {
        try {

            petClinicService.deleteOwner(id);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException e) {

            throw new OwnerNotFoundException(e);
//            return ResponseEntity.notFound().build();
        } catch (Exception e) {

            throw new InternalServerException(e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // postman test api => https://www.getpostman.com/collections/f5e79d51cc467db87cae

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}", produces = "application/json")
    public ResponseEntity<?> getOwnerAsHateoasResource(@PathVariable Long id) {
        try {

            Owner owner = petClinicService.findOwner(id);
            Link self = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withSelfRel();
            Link create = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner").withRel("create");
            Link update = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("update");
            Link delete = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("delete");

            Resource<Owner> resource = new Resource<Owner>(owner, self, create, update, delete);

            return ResponseEntity.ok(resource);
        } catch (OwnerNotFoundException e) {

            throw new OwnerNotFoundException(e);
        }

    }
}
