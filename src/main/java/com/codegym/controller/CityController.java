package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CityController {
   @Autowired
    private CityService cityService;

   @Autowired
   private CountryService countryService;


    @ModelAttribute("countries")
    public Iterable<Country> findAll() {
        return countryService.findAll();
    }


    @GetMapping("/city-list")
    public ModelAndView listCity() {
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("cities/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }
    @GetMapping("/city-create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("cities/create");
        modelAndView.addObject("cities", new City());
        return modelAndView;
    }

    @PostMapping("/city-create")
    public ModelAndView doCreate(@Valid @ModelAttribute("cities") City city, BindingResult bindingResult) {
        new City().validate(city,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/cities/create");
            return modelAndView;
        } else {
            cityService.save(city);
            ModelAndView modelAndView = new ModelAndView("cities/create");
            modelAndView.addObject("cities", new City());
            modelAndView.addObject("message", "you have just created a new City");
            return modelAndView;
        }
    }

//    @PostMapping("/city-create")
//    public ModelAndView saveNewCountry(@ModelAttribute ("cities") City city){
//        cityService.save(city);
//        ModelAndView modelAndView = new ModelAndView("cities/create");
//        modelAndView.addObject("cities", new City());
//        modelAndView.addObject("message","You have just created a new city");
//        return modelAndView;
//    }


    @GetMapping("/city-edit/{id}")
    public ModelAndView editFOrm(@PathVariable Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            ModelAndView modelAndView = new ModelAndView("cities/edit");
            modelAndView.addObject("cities", city);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }
    @PostMapping("/city-edit")
    public ModelAndView doEdit(@ModelAttribute("cities") City city){
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("cities/edit");
        modelAndView.addObject("message","The city is edited successful!");
        modelAndView.addObject("cities", new City());
        return modelAndView;
    }
    @GetMapping("/city-delete/{id}")
    public ModelAndView deleteFOrm(@PathVariable Long id){
        City city = cityService.findById(id);
        if(city!=null){
            ModelAndView modelAndView = new ModelAndView("cities/delete");
            modelAndView.addObject("cities", city);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }
    @PostMapping("/city-delete")
    public ModelAndView doDelete(@ModelAttribute("cities") City city){
        cityService.remove(city.getCityId());
        ModelAndView modelAndView = new ModelAndView("redirect:city-list");
        return modelAndView;
    }

    @GetMapping("/city-detail/{id}")
    public ModelAndView checkDetail(@PathVariable Long id){
        City city = cityService.findById(id);
        if(city!=null){
            ModelAndView modelAndView = new ModelAndView("cities/detail");
            modelAndView.addObject("cities", city);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }
}