package com.game.controller;

import com.game.entity.Player;
import com.game.entity.dto.DTO;
import com.game.entity.dto.PlayerCreationDto;
import com.game.entity.dto.PlayerUpdateDto;
import com.game.service.PlayerService;
import com.game.service.criteria.PageCriteria;
import com.game.service.criteria.PlayerCriteria;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    PlayerService playerService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Player create(@DTO(PlayerCreationDto.class) Player player) {
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
            @DTO(PlayerUpdateDto.class) Player player) {
        if (id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!playerService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        player.setId(id);
        Optional<Player> updatedPlayer = playerService.update(player);
        return ResponseEntity.of(updatedPlayer);
    }

    @GetMapping
    public ResponseEntity<List<Player>> search(@Valid PlayerCriteria criteria, @Valid PageCriteria pageCriteria) {
        List<Player> resultList = playerService.search(criteria, pageCriteria).getContent();
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/count")
    public long search(@Valid PlayerCriteria criteria) {
        return playerService.count(criteria);
    }
}
