package com.dogancanokur.petClinic.services;

import com.dogancanokur.petClinic.dao.OwnerRepository;
import com.dogancanokur.petClinic.dao.PetRepository;
import com.dogancanokur.petClinic.exception.OwnerNotFoundException;
import com.dogancanokur.petClinic.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PetClinicServiceImpl implements PetClinicService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    @Autowired
    public PetClinicServiceImpl(OwnerRepository ownerRepository, PetRepository petRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }
    // burada hangi ismin camelCase hali var ise autowired için o isim tercih edilecektir.
    // bkz: OwnerRepositoryJdbcImpl

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Secured(value = {"ROLE_USER", "ROLE_EDITOR"})
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Owner> findOwners(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Owner findOwner(Long id) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(id);
        if (owner == null) {
            throw new OwnerNotFoundException("Owner is not found");
        } else {
            return owner;
        }
    }

    @Override
    public long createOwner(Owner owner) {

        ownerRepository.create(owner);
        return owner.getId();
    }

    @Override
    public void updateOwner(Owner owner) {
        Owner foundOwner = ownerRepository.findById(owner.getId());
        if (foundOwner == null) {

            throw new OwnerNotFoundException("Owner not found");
        } else {

            ownerRepository.update(owner);
        }
    }

    @Override
    public void deleteOwner(Long id) {
//        Owner owner = findOwner(id);
//        if (owner == null) {
//            throw new OwnerNotFoundException("Owner Not Found");
//        } else {
//            ownerRepository.delete(id);
//        }

        petRepository.deleteByOwnerId(id);
        ownerRepository.delete(id);
//        if (true) throw new RuntimeException("test rollback");

    }
}
