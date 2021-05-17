package com.prime.demo.services;

import com.prime.demo.entity.PrimeNumber;
import com.prime.demo.respository.PrimeNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is the core service to find prime numbers within given upper limit
 *
 * @author Gee Singh Sagoo
 */

@Service
@CacheConfig(cacheNames = "prime-number")
public class PrimeNumberService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * JPA repository to store prime number collections in database
     */
    @Autowired
    private PrimeNumberRepository primeNumberRepository;

    /**
     * This is the methid where we handle prime number core logic
     *
     * @param limit - the upper limit to find prime numbers in
     * @return PrimeNumber object with limit and prime number collection list
     * @Cacheable implements simple caching, storing using key,value map whereby the key = {methodName + parameters}
     */

    @Transactional
    @Cacheable(key = "{#root.methodName,#limit}")
    public PrimeNumber findPrimeNumbers(Integer limit) {

        LOG.info("Finding prime numbers for upper limit [{}]", limit);

        // using optional to handle null/empty values, cleaner approach and less code
        return primeNumberRepository.findById(limit).orElseGet(() -> {

            IntStream intStream = IntStream.range(2, limit+1);
            IntFunction<IntPredicate> sieve = n -> i -> i == n || i % n != 0;
            intStream = intStream.filter(sieve.apply(2));

            for (int i = 3; i * i <= limit; i += 2) {
                intStream = intStream.filter(sieve.apply(i));
            }

            List<Integer> primeCollection = intStream.boxed().collect(Collectors.toList());
            PrimeNumber primeNumber = new PrimeNumber();
            primeNumber.setInitial(limit);
            primeNumber.setPrimes(primeCollection);

            primeNumber = primeNumberRepository.save(primeNumber);

            LOG.info("Successfully calculated prime numbers [values={}]", primeNumber.getPrimes());

            return primeNumber;
        });
    }

}
