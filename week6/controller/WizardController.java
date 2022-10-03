package com.example.week6.controller;

import com.example.week6.pojo.Wizard;
import com.example.week6.pojo.Wizards;
import com.example.week6.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;


    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public Wizards getWizard(){
        Wizards w = new Wizards();
        w.setModel(wizardService.retrieveWizard());
        return w;
    }

    @RequestMapping(value = "/addWizard", method = RequestMethod.POST)
    public boolean createWizard(
            @RequestParam("name") String name,
            @RequestParam("sex") String sex,
            @RequestParam("position") String position,
            @RequestParam("money") int money,
            @RequestParam("school") String school,
            @RequestParam("house") String house
    ){
        boolean status = wizardService.createWizard(new Wizard(null, sex, name, school, house, position, money));
        return status;
    }

    @RequestMapping(value = "/deleteWizard", method = RequestMethod.POST)
    public boolean deleteWizard(
            @RequestParam("name") String name
    ){
        Wizard wizard = wizardService.retrieveWizardByName(name);
        boolean status = wizardService.deleteWizard(wizard);
        return status;
    }

    @RequestMapping(value = "/updateWizard", method = RequestMethod.POST)
    public boolean updateWizard(
            @RequestParam("name") String name,
            @RequestParam("oldName") String oldName,
            @RequestParam("sex") String sex,
            @RequestParam("position") String position,
            @RequestParam("money") int money,
            @RequestParam("school") String school,
            @RequestParam("house") String house
    ){
        Wizard wizard = wizardService.retrieveWizardByName(oldName);
        if (wizard != null){
            wizardService.updateWizard(new Wizard(wizard.get_id(), sex, name, school, house, position, money));
            return true;
        }
        else {
            return false;
        }
    }
}