package com.example.lab5_2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Word word_class = new Word();

    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.GET)
    private ArrayList<String> addBadWord(
            @PathVariable("word") String s
    ) {
        word_class.badWords.add(s);
        return word_class.badWords;
    }

    @RequestMapping(value = "/delBad/{word}", method = RequestMethod.GET)
    private ArrayList<String> deleteBadWord(
            @PathVariable("word") String s
    ) {
        word_class.badWords.remove(String.valueOf(s));
        return word_class.badWords;
    }

    @RequestMapping(value = "/addGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> addGoodWord(
            @PathVariable("word") String s
    ) {
        word_class.goodWords.add(s);
        return word_class.goodWords;
    }

    @RequestMapping(value = "/delGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteGoodWord(
            @PathVariable("word") String s
    ) {
        word_class.goodWords.remove(String.valueOf(s));
        return word_class.goodWords;
    }


    @RequestMapping(value = "/proof/{sentence}", method = RequestMethod.GET)
    public String proofSentence(
            @PathVariable("sentence") String s
    ){
        boolean good_check = false;
        boolean bad_check = false;

        for(String i : word_class.goodWords){
            if(s.indexOf(i) !=-1){
                good_check = true;
            }
        }
        for(String i : word_class.badWords){
            if(s.indexOf(i) !=-1){
                bad_check = true;
            }
        }

        if((good_check) && (bad_check)){
            rabbitTemplate.convertAndSend("Fanout", "", s);
            return "Found Good Word and Bad Word";
        } else if (bad_check) {
            rabbitTemplate.convertAndSend("Direct", "bad", s);
            return "Found Bad Word";
        } else if (good_check) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
            return "Found Good Word";
        }
        return null;
    }
    @RequestMapping(value = "/getSentence", method = RequestMethod.GET)
    public Sentence getSentence(){
        Object sentences = rabbitTemplate.convertSendAndReceive("Direct", "get", "");
        return ((Sentence) sentences);
    }
}
