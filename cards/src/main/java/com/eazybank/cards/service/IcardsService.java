package com.eazybank.cards.service;

import com.eazybank.cards.dto.CardsDTO;

public interface IcardsService {

	void createCard(String mobileNumber);
	CardsDTO fetchCard(String mobileNumber);
	boolean updateCard(CardsDTO cardsDTO);
	boolean deleteCard(String mobileNumber);
}
