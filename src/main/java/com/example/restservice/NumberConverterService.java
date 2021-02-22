package com.example.restservice;

import org.springframework.stereotype.Service;

@Service
public class NumberConverterService {
    private static final String[] romanSigns = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "LD", "D", "LM", "M"};
    private static final int[] romanSignsIntEquivalents = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    private String intToRoman(int input) throws RomanNumberOutOfRangeException {
        StringBuilder stringBuilder = new StringBuilder();
        int index = romanSignsIntEquivalents.length - 1;
        if (input < 1 || input > 3999) throw new RomanNumberOutOfRangeException(input);

        while (input > 0) {
            if (input >= romanSignsIntEquivalents[index]) {
                input -= romanSignsIntEquivalents[index];
                stringBuilder.append(romanSigns[index]);
            } else
                index--;
        }
        return stringBuilder.toString();
    }

    public String getConvertedNumber(String numericSystem, int intNumber) throws NumericSystemNotSupported, RomanNumberOutOfRangeException {
        if (numericSystem.equalsIgnoreCase("hex")) {
            return Integer.toHexString(intNumber);
        } else if (numericSystem.equalsIgnoreCase("roman")) {
            return intToRoman(intNumber);
        } else {
            throw new NumericSystemNotSupported();
        }
    }

}

class NumericSystemNotSupported extends Exception {
    NumericSystemNotSupported() {
        super("Numeric system not supported.");
    }
}

class RomanNumberOutOfRangeException extends Exception {
    RomanNumberOutOfRangeException(int number) {
        super(number + " not in valid range (1-3999)");
    }
}
