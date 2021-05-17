package com.prime.demo.services;

import com.prime.demo.entity.PrimeNumber;
import com.prime.demo.respository.PrimeNumberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PrimeNumberServiceTest {

    @TestConfiguration
    static class PrimeNumberServiceTestConfig {
        @Bean
        public PrimeNumberService primeNumberService() {
            return new PrimeNumberService();
        }
    }

    @Autowired
    private PrimeNumberService primeNumberService;

    @MockBean
    private PrimeNumberRepository primeNumberRepository;

    @Test
    public void givenValidLimitNumber_whenServerIsCalled_thenReturnResponseData() {

        List<Integer> primeCollection = Arrays.asList(2, 3, 5, 7, 11);
        PrimeNumber primeNumber = new PrimeNumber();
        primeNumber.setInitial(11);
        primeNumber.setPrimes(primeCollection);

        when(primeNumberRepository.save(any())).thenReturn(primeNumber);

        // check limit number is coming back as expected
        Assert.assertTrue(primeNumberService.findPrimeNumbers(11).getInitial() == 11);

        // check prime collection is valid
        Assert.assertTrue(primeNumberService.findPrimeNumbers(11).getPrimes().containsAll(Arrays.asList(2,3,5,7,11)));

    }

}
