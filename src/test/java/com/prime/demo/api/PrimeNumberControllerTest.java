package com.prime.demo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prime.demo.entity.PrimeNumber;
import com.prime.demo.services.PrimeNumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimeNumberControllerTest {

    @MockBean
    private PrimeNumberService primeNumberService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenValidUpperLimitNumber_WhenServiceIsCalled_ThenValidResponseReturned() throws Exception {

        List<Integer> primeCollection = Arrays.asList(2, 3);
        PrimeNumber primeNumber = new PrimeNumber();
        primeNumber.setInitial(4);
        primeNumber.setPrimes(primeCollection);
        when(primeNumberService.findPrimeNumbers(4)).thenReturn(primeNumber);

        MvcResult result = mvc.perform(get("/primes/{limit}", 4)
                .header("Origin", "http://localhost"))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody =
                result.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(primeNumber);
        assertEquals(expectedResponseBody, actualResponseBody);

    }

    @Test
    public void givenInValidUpperLimitNumber_WhenServiceIsCalled_ThenErrorIsReturned() throws Exception {

        List<Integer> primeCollection = Arrays.asList(2, 3);
        PrimeNumber primeNumber = new PrimeNumber();
        primeNumber.setInitial(4);
        primeNumber.setPrimes(primeCollection);
        when(primeNumberService.findPrimeNumbers(4)).thenReturn(primeNumber);

        MvcResult result = mvc.perform(get("/primes/{limit}", -1)
                .header("Origin", "http://localhost"))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

}
