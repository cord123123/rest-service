package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controler {

    private final String template = "%s in %s system is %s";

    @GetMapping("/calculate")
    public String response(@RequestParam(value = "number") String inputNumber,
                           @RequestParam(value = "system") String numericSystem){
        String calculatedNumber;
        int intNumber;
        try{
         intNumber= Integer.parseInt(inputNumber);
        }catch (NumberFormatException e){
            return "Only integers allowed.";
        }

        if(numericSystem.toLowerCase().equals("hex")){
            calculatedNumber=Integer.toHexString(intNumber);
        }else if(numericSystem.toLowerCase().equals("roman")){
            try{
                calculatedNumber=intToRoman(intNumber);
            }catch(IntegerToRomanException e){
                return e.getMessage();
            }

        }else{
            return "System not implemented";
        }

        return String.format(template,inputNumber,numericSystem,calculatedNumber);
    }

    private String intToRoman(int input) throws IntegerToRomanException {
        StringBuilder stringBuilder = new StringBuilder();
        String[] romanSigns = {"I","IV","V","IX","X","XL","L","XC","C","LD","D","LM","M"};
        int[] numbers = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
        int index=numbers.length-1;
        if(input<1||input>3999) throw new IntegerToRomanException(input);

        while(input>0){
            if(input>=numbers[index]){
                input-=numbers[index];
                stringBuilder.append(romanSigns[index]);
            }else
                index--;
        }
        return stringBuilder.toString();
    }
}
