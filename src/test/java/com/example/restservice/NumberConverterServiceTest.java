package com.example.restservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class NumberConverterServiceTest {

    NumberConverterService service = new NumberConverterService();

    @Test
    void invalidNumericSystemTest() {
        Assertions.assertThrows(NumericSystemNotSupported.class, () -> {
            service.getConvertedNumber("hexadecimal", 1);
            service.getConvertedNumber("empty", 1);
            service.getConvertedNumber("", 1);
            service.getConvertedNumber("hexadecimal", 1);
        });
    }

    @Test
    void validNumericSystemTest() {
        service.getConvertedNumber("roman", 1);
        service.getConvertedNumber("hex", 1);
        service.getConvertedNumber("hEX", 1);
        service.getConvertedNumber("rOMan", 1);
    }

    @Test
    void getConvertedNumberToHexTest() {
        Assertions.assertEquals("1", service.getConvertedNumber("hex", 1));
        Assertions.assertEquals("0", service.getConvertedNumber("hex", -0));
        Assertions.assertEquals("1f", service.getConvertedNumber("hex", 31));
        Assertions.assertEquals("ffffffff", service.getConvertedNumber("hex", -1));
        Assertions.assertEquals("80000000", service.getConvertedNumber("hex", Integer.MIN_VALUE));
    }

    @Test
    void romanNumberOutOfRangeOutOfRangeTest() {
        Assertions.assertThrows(RomanNumberOutOfRangeException.class, () -> {
            service.getConvertedNumber("roman", 0);
            service.getConvertedNumber("roman", -1);
            service.getConvertedNumber("roman", 4000);
            service.getConvertedNumber("roman", 45845);
        });
    }

    @Test
    void getConvertedNumberToRomanTest() {
        Assertions.assertEquals("I", service.getConvertedNumber("roman", 1));
        Assertions.assertEquals("XXXIV", service.getConvertedNumber("roman", 34));
        Assertions.assertEquals("CDXLIV", service.getConvertedNumber("roman", 444));
        Assertions.assertEquals("MMMCCCXXXIII", service.getConvertedNumber("roman", 3333));
        Assertions.assertEquals("MMMCMXCIX", service.getConvertedNumber("Roman", 3999));
    }
}