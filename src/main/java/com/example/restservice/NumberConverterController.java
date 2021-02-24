package com.example.restservice;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberConverterController {

    private NumberConverterService service;

    @Autowired
    public NumberConverterController(NumberConverterService service) {
        this.service = service;
    }

    @Operation(summary = "Convert integer number different numeric system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculated."),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied.")
    })
    @GetMapping("/number_converter")
    public String convertNumber(@Parameter(description = "Int number to convert.") @RequestParam int number,
                                @Parameter(description = "Numeric system {roman,hex}.") @RequestParam String system) {
        return service.getConvertedNumber(system, number);
    }
}


