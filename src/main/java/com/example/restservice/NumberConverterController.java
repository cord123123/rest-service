package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class NumberConverterController {

    private NumberConverterService service;

    @Autowired
    public NumberConverterController(NumberConverterService service) {
        this.service = service;
    }

    @GetMapping("/number_converter")
    public String convertNumber(@RequestParam int number,
                                @RequestParam String system) {
        try {
            return service.getConvertedNumber(system, number);
        } catch (NumericSystemNotSupported numericSystemNotSupported) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Numeric system not supported.",numericSystemNotSupported);
        } catch (RomanNumberOutOfRangeException romanNumberOutOfRangeException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Conversion in range 1-3999 only supported.",romanNumberOutOfRangeException);
        }
    }
}


