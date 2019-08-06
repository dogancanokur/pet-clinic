package com.dogancanokur.petClinic.web;

import com.dogancanokur.petClinic.model.Owner;
import com.dogancanokur.petClinic.services.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PetClinicNewOwnerController {

    private final PetClinicService petClinicService;

    @Autowired
    public PetClinicNewOwnerController(PetClinicService petClinicService) {
        this.petClinicService = petClinicService;
    }

    @RequestMapping(value = "/owner/new", method = RequestMethod.GET)
    public String newOwner() {
        return "newOwner";
    }

    @ModelAttribute
    public Owner initModel() {
        return new Owner();
    }

    @RequestMapping(value = "/owner/new", method = RequestMethod.POST)
    public String handleFormSubmit(@ModelAttribute Owner owner) {
        petClinicService.createOwner(owner);
        return "redirect:/owners";
    }
}
