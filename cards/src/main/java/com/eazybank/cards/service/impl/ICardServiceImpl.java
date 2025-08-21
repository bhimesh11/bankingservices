package com.eazybank.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybank.cards.constants.CardsConstants;
import com.eazybank.cards.dto.CardsDTO;
import com.eazybank.cards.entity.Cards;
import com.eazybank.cards.exception.CardAlreadyExistsException;
import com.eazybank.cards.exception.ResourceNotFoundException;
import com.eazybank.cards.mapper.CardsMapper;
import com.eazybank.cards.repository.CardsRepository;
import com.eazybank.cards.service.IcardsService;

@Service
public class ICardServiceImpl implements IcardsService {

	private CardsRepository cardsRepository;

	public ICardServiceImpl(CardsRepository cardsRepository) {
		super();
		this.cardsRepository = cardsRepository;
	}

	@Override
	public void createCard(String mobileNumber) {

		Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
		if (optionalCards.isPresent()) {
			throw new CardAlreadyExistsException("Card already registered with another mobile number");
		}
		cardsRepository.save(createNewCard(mobileNumber));

	}

	private Cards createNewCard(String mobileNumber) {

		Cards newCard = new Cards();
		long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
		newCard.setCardNumber(Long.toString(randomCardNumber));
		newCard.setMobileNumber(mobileNumber);
		newCard.setCardType(CardsConstants.CREDIT_CARD);
		newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
		newCard.setAmountUsed(0);
		newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
		return newCard;
	}

	@Override
	public CardsDTO fetchCard(String mobileNumber) {

		Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		return CardsMapper.maptoCardsDto(cards, new CardsDTO());
	}

	@Override
	public boolean updateCard(CardsDTO cardsDTO) {
		Cards cards = cardsRepository.findByCardNumber(cardsDTO.getCardNumber())
				.orElseThrow(() -> new ResourceNotFoundException("card", "cardNumber", cardsDTO.getCardNumber()));
		CardsMapper.mapTOCards(cardsDTO, cards);
		cardsRepository.save(cards);
		return true;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {

		Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		cardsRepository.deleteById(cards.getCarId());
		return true;

	}

}
