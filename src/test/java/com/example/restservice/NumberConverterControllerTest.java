package com.example.restservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberConverterControllerTest {

    NumberConverterController numberConverterController = new NumberConverterController(new NumberConverterService());

    @Test
    void numberConverterControllerToHexTest(){
        Assertions.assertEquals("f",numberConverterController.convertNumber(15,"hex"));
    }

    @Test
    void numberConverterControllerToRoman(){
        Assertions.assertEquals("I",numberConverterController.convertNumber(1,"roman"));
    }

}