package com.dogancanokur.petClinic.web;

import com.dogancanokur.petClinic.services.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PetClinicController {

    private final PetClinicService petClinicService;

    @Autowired
    public PetClinicController(PetClinicService petClinicService) {
        this.petClinicService = petClinicService;
    }

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public ModelAndView getOwners() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("owners", petClinicService.findOwners());
        modelAndView.setViewName("owners");
        return modelAndView;
    }

    @RequestMapping("/pcs")
    @ResponseBody
    public String test() {

        return "Welcome PetClinic!";
    }
}