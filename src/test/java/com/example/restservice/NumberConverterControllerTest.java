package com.example.restservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class NumberConverterControllerTest {

    NumberConverterController numberConverterController = new NumberConverterController(new NumberConverterService());

    @Test
    void numberConverterControllerInvalidSystemTest(){
        Assertions.assertThrows(ResponseStatusException.class,()->{
            numberConverterController.convertNumber(1,"decimal");
            numberConverterController.convertNumber(1,"none");
            numberConverterController.convertNumber(1,"");
        });
    }

    @Test
    void numberConverterControllerInvalidNumberTest(){
        Assertions.assertThrows(ResponseStatusException.class,()->{
            numberConverterController.convertNumber(-1,"roman");
            numberConverterController.convertNumber(0,"roman");
            numberConverterController.convertNumber(4000,"roman");
        });
    }

    @Test
    void numberConverterControllerToHexTest(){
        Assertions.assertEquals("f",numberConverterController.convertNumber(15,"hex"));
    }

    @Test
    void numberConverterControllerToRoman(){
        Assertions.assertEquals("I",numberConverterController.convertNumber(1,"roman"));
    }

}