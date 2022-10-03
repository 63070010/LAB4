package com.example.lab4;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MathAPI {
    @RequestMapping(value = "/plus/{n1}/{n2}", method = RequestMethod.GET)
    public double myPlus(@PathVariable("n1") double n1, @PathVariable("n2") double n2) {
        return n1+n2;
    }

    @RequestMapping(value = "/minus/{n1}/{n2}", method = RequestMethod.GET)
    public double myMinus(@PathVariable("n1") double n1, @PathVariable("n2") double n2) {
        return n1-n2;
    }

    @RequestMapping(value = "/divide/{n1}/{n2}", method = RequestMethod.GET)
    public double myDivide(@PathVariable("n1") double n1, @PathVariable("n2") double n2) {
        return n1/n2;
    }

    @RequestMapping(value = "/multi/{n1}/{n2}", method = RequestMethod.GET)
    public double myMulti(@PathVariable("n1") double n1, @PathVariable("n2") double n2) {
        return n1*n2;
    }

    @RequestMapping(value = "/mod/{n1}/{n2}", method = RequestMethod.GET)
    public double myMod(@PathVariable("n1") double n1, @PathVariable("n2") double n2) {
        return n1%n2;
    }

    @RequestMapping(value = "/max/{n3}", method = RequestMethod.GET)
    public double myMax(@PathVariable("n3") double n3) {
        return n3;
    }

}