package com.prime.demo.respository;

import com.prime.demo.entity.PrimeNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimeNumberRepository extends JpaRepository<PrimeNumber, Integer> {

    /**
     * Defining this interface serves two purposes:
     * First, by extending JpaRepository we get a bunch of generic CRUD methods into our type that allows saving Accounts, deleting them and so on.
     * Second, this will allow the Spring Data JPA repository infrastructure to scan the classpath for this interface and create a Spring bean for it.
     */

}