package com.game.controller;

import com.game.entity.Player;
import com.game.service.PlayerService;
import com.game.service.criteria.PageCriteria;
import com.game.service.criteria.PlayerCriteria;
import com.game.validator.PlayerValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/{id}")
    public ResponseEntity<Player> update(
            @PathVariable Long id,
            @RequestBody Player player) {
        if (id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!playerService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerValidator.throwIfNotValid(player);
        player.setId(id);
        Optional<Player> updatedPlayer = playerService.update(player);
        return ResponseEntity.of(updatedPlayer);
    }

    @GetMapping
    public ResponseEntity<List<Player>> search(PlayerCriteria criteria, PageCriteria pageCriteria) {
        List<Player> resultList = playerService.search(criteria, pageCriteria).getContent();
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/count")
    public long search(PlayerCriteria criteria) {
        return playerService.count(criteria);
    }
}
