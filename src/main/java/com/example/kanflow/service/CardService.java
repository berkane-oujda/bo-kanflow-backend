package com.example.kanflow.service;

import com.example.kanflow.dto.UpdateCardDto;
import com.example.kanflow.model.Card;
import com.example.kanflow.repository.CardRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(UUID cardId, UpdateCardDto updateCardDto) {
        Card card = cardRepository.findById(cardId).orElse(null);
        if (card != null) {
            card.setTitle(updateCardDto.getTitle());
            card.setDescription(updateCardDto.getDescription());
            return cardRepository.save(card);
        } else {
            throw new RuntimeException("Card not found");
        }
    }
}