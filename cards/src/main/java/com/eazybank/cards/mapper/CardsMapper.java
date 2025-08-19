package com.eazybank.cards.mapper;

import com.eazybank.cards.dto.CardsDTO;
import com.eazybank.cards.entity.Cards;

public class CardsMapper {
	
	public static CardsDTO maptoCardsDto(Cards cards, CardsDTO cardsDTO)
	{
		cardsDTO.setCardNumber(cards.getCardNumber());
		cardsDTO.setCardType(cards.getCardType());
		cardsDTO.setMobileNumber(cards.getMobileNumber());
		cardsDTO.setTotalLimit(cards.getTotalLimit());
		cardsDTO.setAvailableAmount(cards.getAvailableAmount());
		cardsDTO.setAmountUsed(cards.getAmountUsed());
		return cardsDTO;
	}
	
	public static Cards mapTOCards(CardsDTO cardsDTO,Cards cards)
	{
		cards.setCardNumber(cardsDTO.getCardNumber());
		cards.setCardType(cardsDTO.getCardType());
		cardsDTO.setMobileNumber(cardsDTO.getMobileNumber());
		cardsDTO.setTotalLimit(cardsDTO.getTotalLimit());
		cardsDTO.setAvailableAmount(cardsDTO.getAvailableAmount());
		cardsDTO.setAmountUsed(cardsDTO.getAmountUsed());
		return cards;
	}

}
