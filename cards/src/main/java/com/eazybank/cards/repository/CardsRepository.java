package com.eazybank.cards.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.eazybank.cards.entity.Cards;

@Repository
public interface CardsRepository {

	
	Optional<Cards> findByMobileNumber (String mobileNumber);
	Optional<Cards> findByCardNumber (String cardNumber);
	
}
