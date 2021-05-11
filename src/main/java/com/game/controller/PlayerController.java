package com.game.controller;

import com.game.entity.Player;
import com.game.service.PlayerService;
import com.game.validator.PlayerValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerValidator playerValidator;

    public PlayerController(PlayerService playerService, PlayerValidator playerValidator) {
        this.playerService = playerService;
        this.playerValidator = playerValidator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Player create(@RequestBody Player player) {
        playerValidator.throwIfNotValid(player);
        playerService.calculatePlayer(player);
        return playerService.create(player);
    }
}
