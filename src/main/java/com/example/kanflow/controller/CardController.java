package com.example.kanflow.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kanflow.dto.UpdateCardDto;
import com.example.kanflow.model.Card;
import com.example.kanflow.service.CardService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/create")
    public Card createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }

    @PostMapping("/{cardId}/update")
    public Card updateCard(@PathVariable UUID cardId, @RequestBody UpdateCardDto card) {
        return cardService.updateCard(cardId, card);
    }

}
