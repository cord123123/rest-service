package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class NumberConverterControler {

    private final String template = "%s in %s system is %s";

    @GetMapping("/NumberConverter")
    public String convertNumber(@RequestParam(value = "number") String inputNumber,
                                @RequestParam(value = "system") String numericSystem) {
        try {
            int intNumber = Integer.parseInt(inputNumber);
            String outputNumber = getConvertedNumber(numericSystem, intNumber);
            return String.format(template, inputNumber, numericSystem, outputNumber);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only integers allowed.", e);
        }
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private class NumericSystemNotImplemented extends RuntimeException {
        NumericSystemNotImplemented(String system) {
            super(system + " is not implemented.");
        }
    }

    private String getConvertedNumber(String numericSystem, int intNumber) throws NumericSystemNotImplemented {
        String outputNumber;
        if (numericSystem.toLowerCase().equals("hex")) {
            outputNumber = Integer.toHexString(intNumber);
        } else if (numericSystem.toLowerCase().equals("roman")) {
            outputNumber = intToRoman(intNumber);
        } else {
            throw new NumericSystemNotImplemented(numericSystem);
        }
        return outputNumber;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private class RomanNumberOutOfRangeException extends RuntimeException {
        RomanNumberOutOfRangeException(int number) {
            super(number + " is not in valid range (1-3999)");
        }
    }

    private String intToRoman(int input) throws RomanNumberOutOfRangeException {
        StringBuilder stringBuilder = new StringBuilder();
        String[] romanSigns = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "LD", "D", "LM", "M"};
        int[] numbers = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        int index = numbers.length - 1;
        if (input < 1 || input > 3999) throw new RomanNumberOutOfRangeException(input);

        while (input > 0) {
            if (input >= numbers[index]) {
                input -= numbers[index];
                stringBuilder.append(romanSigns[index]);
            } else
                index--;
        }
        return stringBuilder.toString();
    }
}
