package com.dogancanokur.petClinic.dao.jdbc;

import com.dogancanokur.petClinic.dao.PetRepository;
import com.dogancanokur.petClinic.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetRepositoryJdbcImpl implements PetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pet> findAll() {
        // TODO
        return null;
    }

    @Override
    public Pet findById(Long id) {
        // TODO
        return null;
    }

    @Override
    public Pet findByName(String name) {
        // TODO
        return null;
    }

    @Override
    public List<Pet> findByOwnerId(Long ownerId) {
        // TODO
        return null;
    }

    @Override
    public void create(Pet pet) {
        // TODO
    }

    @Override
    public Pet update(Pet pet) {
        // TODO
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO
    }

    @Override
    public void deleteByOwnerId(Long ownerId) {
        String sql = "delete from t_pet where owner_id = ?";
        jdbcTemplate.update(sql, ownerId);
    }
}
