package com.example.lab4p2;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier {

    @RequestMapping("/getChange/{num}")
    public Change getChange(@PathVariable("num") int num) {
        int[] pay = new int[7];
        int[] numpay = new int[]{1000, 500, 100, 20, 10, 5, 1};
        for (int i = 0; i < pay.length; i++) {
            pay[i] = num / numpay[i];
            num = num % numpay[i];
        }
        return new Change(pay[0], pay[1], pay[2], pay[3], pay[4], pay[5], pay[6]);


    }
}
