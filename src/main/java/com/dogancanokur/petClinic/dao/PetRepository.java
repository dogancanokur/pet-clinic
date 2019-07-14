package com.dogancanokur.petClinic.dao;

import com.dogancanokur.petClinic.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository {

    List<Pet> findAll();

    Pet findById(Long id);

    Pet findByName(String name);

    List<Pet> findByOwnerId(Long ownerId);

    void create(Pet pet);

    Pet update(Pet pet);

    void delete(Long id);

    void deleteByOwnerId(Long ownerId);
}
