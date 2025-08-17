package com.eazybank.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybank.accounts.entity.customer;

@Repository
public interface CustomerReposiotry extends JpaRepository<customer, Long>{
	
	Optional<customer> findBymobileNumber(String mobileNumber);
}
