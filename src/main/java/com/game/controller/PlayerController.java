package com.game.controller;

import com.game.entity.Player;
import com.game.service.PlayerService;
import com.game.validator.PlayerValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id) {
        if (id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Player> player = playerService.findById(id);
        return ResponseEntity.of(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Player> delete(@PathVariable Long id) {
        if (id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!playerService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
