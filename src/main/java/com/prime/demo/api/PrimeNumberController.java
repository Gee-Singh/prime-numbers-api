package com.prime.demo.api;

import com.prime.demo.aspect.LimitCheck;
import com.prime.demo.entity.PrimeNumber;
import com.prime.demo.services.PrimeNumberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeNumberController {

    /**
     * PrimeNumberService handles the business logic
     */
    @Autowired
    private PrimeNumberService primeNumberService;

    /**
     * API documentation , accessible via [http://localhost:8080/swagger-ui.html]
     */
    @ApiOperation(value = "Calculate prime number for a given value", response = PrimeNumber.class)
    @GetMapping(value = "/primes/{limit}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @LimitCheck
    public PrimeNumber findPrimeNumbers(@PathVariable("limit") Integer limit) {
        return primeNumberService.findPrimeNumbers(limit);
    }

}