package com.codegym.controller;

import com.codegym.model.Country;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;
    @GetMapping("/country-list")
    public ModelAndView listCountry() {
        Iterable<Country> countries = countryService.findAll();
        ModelAndView modelAndView = new ModelAndView("countries/list");
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/country-create")
    public ModelAndView getCreateForm(){
        ModelAndView modelAndView = new ModelAndView("countries/create");
        modelAndView.addObject("countries", new Country());
        return modelAndView;
    }

    @PostMapping("/country-create")
    public ModelAndView saveNewCountry(@ModelAttribute ("countries") Country country){
        countryService.save(country);
        ModelAndView modelAndView = new ModelAndView("countries/create");
        modelAndView.addObject("countries", new Country());
        modelAndView.addObject("message","You have just created a new country");
        return modelAndView;
    }

    @GetMapping("country-edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        Country country = countryService.findById(id);
        if (country != null) {
            ModelAndView modelAndView = new ModelAndView("countries/edit");
            modelAndView.addObject("countries", country);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("country-edit")
    public ModelAndView doEdit(@ModelAttribute ("countries") Country country){
        countryService.save(country);
        ModelAndView modelAndView = new ModelAndView("countries/edit");
        modelAndView.addObject("message", "Edited this country");
        modelAndView.addObject("categories", new Country());
        return modelAndView;
    }

    @GetMapping("country-delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) {
        Country country = countryService.findById(id);
        if (country != null) {
            ModelAndView modelAndView = new ModelAndView("countries/delete");
            modelAndView.addObject("countries", country);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("country-delete")
    public String delete(@ModelAttribute ("countries") Country country){
        countryService.remove(country.getId());
        return "redirect:country-list";

    }
}