package com.game.controller;

import com.game.entity.Player;
import com.game.service.PlayerService;
import com.game.validator.PlayerValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Player create(@RequestBody Player player) {
        playerService.calculatePlayer(player);
        return playerService.create(player);
    }
}
