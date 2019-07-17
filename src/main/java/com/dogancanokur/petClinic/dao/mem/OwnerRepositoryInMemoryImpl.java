package com.dogancanokur.petClinic.dao.mem;

import com.dogancanokur.petClinic.dao.OwnerRepository;
import com.dogancanokur.petClinic.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OwnerRepositoryInMemoryImpl implements OwnerRepository {
    private Map<Long, Owner> ownerMap = new HashMap<>();

    public OwnerRepositoryInMemoryImpl() {
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();
        Owner owner3 = new Owner();
        Owner owner4 = new Owner();

        owner1.setId(1L);
        owner1.setFirstName("Doğancan");
        owner1.setLastName("Okur");

        owner2.setId(2L);
        owner2.setFirstName("Kübra");
        owner2.setLastName("Okur");

        owner3.setId(3L);
        owner3.setFirstName("Murat");
        owner3.setLastName("Kgil");

        owner4.setId(4L);
        owner4.setFirstName("Gürcan");
        owner4.setLastName("Çolak");

        ownerMap.put(owner1.getId(), owner1);
        ownerMap.put(owner2.getId(), owner2);
        ownerMap.put(owner3.getId(), owner3);
        ownerMap.put(owner4.getId(), owner4);
    }

    @Override
    public List<Owner> findAll() {
        return new ArrayList<Owner>(ownerMap.values());
    }

    @Override
    public Owner findById(Long id) {
        return ownerMap.get(id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {

        return ownerMap.values().stream().filter(o -> o.getLastName().equals(lastName)).collect(Collectors.toList());
    }

    @Override
    public void create(Owner owner) {
        if (owner.getId() == null) {

            owner.setId(new Date().getTime());
        }

        ownerMap.put(owner.getId(), owner);
    }

    @Override
    public Owner update(Owner owner) {
        ownerMap.replace(owner.getId(), owner);
        return owner;
    }

    @Override
    public void delete(Long id) {
        ownerMap.remove(id);
    }
}
