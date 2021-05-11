package com.game.service;

import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import com.game.util.Calculator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements EntityCrudService<Player, Long> {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player create(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return playerRepository.existsById(id);
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public long count() {
        return playerRepository.count();
    }

    public void calculatePlayer(Player player) {
        Integer experience = player.getExperience();
        int level = Calculator.calculateLevel(experience);
        int untilNextLevel = Calculator.calculateUntilNextLevel(level, experience);
        player.setLevel(level);
        player.setUntilNextLevel(untilNextLevel);
    }
}
