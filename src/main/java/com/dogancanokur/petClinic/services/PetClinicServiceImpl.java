package com.dogancanokur.petClinic.services;

import com.dogancanokur.petClinic.dao.OwnerRepository;
import com.dogancanokur.petClinic.exception.OwnerNotFoundException;
import com.dogancanokur.petClinic.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetClinicServiceImpl implements PetClinicService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public PetClinicServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Owner> findOwners(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
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

        Owner owner = findOwner(id);
        if (owner == null) {

            throw new OwnerNotFoundException("Owner Not Found");
        } else {

            ownerRepository.delete(id);
        }
    }
}
