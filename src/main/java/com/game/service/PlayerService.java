package com.game.service;

import com.game.entity.Field;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import com.game.service.criteria.PageCriteria;
import com.game.service.criteria.PlayerCriteria;
import com.game.util.Calculator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.game.repository.PrimitiveSpecifications.*;

@Service
public class PlayerService implements EntityCrudService<Player, Long>, SearchService<PlayerCriteria, Player> {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player create(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Optional<Player> update(Player player) {
        Optional<Player> playerOptional = findById(player.getId());
        if (playerOptional.isPresent()) {
            Player playerById = playerOptional.get();
            for (Field<Player> field : Player.PlayerField.values()) {
                Object value = field.getValue(player);
                Object valueFromDb = field.getValue(playerById);
                if (value != null && !value.equals(valueFromDb)) {
                    field.setValue(playerById, value);
                    if (field == Player.PlayerField.EXPERIENCE) {
                        calculatePlayer(playerById);
                    }
                }
            }
            return Optional.of(playerRepository.save(playerById));
        }
        return Optional.empty();
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

    @Override
    public Specification<Player> buildSpecification(PlayerCriteria criteria) {
        return Specification
                .where(like(Player.PlayerField.NAME, criteria.getName()))
                .and(like(Player.PlayerField.TITLE, criteria.getTitle()))
                .and(between(Player.PlayerField.BIRTHDAY, criteria.getStartDate(), criteria.getFinishDate()))
                .and(greaterThan(Player.PlayerField.BIRTHDAY, criteria.getStartDate()))
                .and(lessThan(Player.PlayerField.BIRTHDAY, criteria.getFinishDate()))
                .and(between(Player.PlayerField.EXPERIENCE, criteria.getMinExperience(), criteria.getMaxExperience()))
                .and(greaterThan(Player.PlayerField.EXPERIENCE, criteria.getMinExperience()))
                .and(lessThan(Player.PlayerField.EXPERIENCE, criteria.getMaxExperience()))
                .and(between(Player.PlayerField.LEVEL, criteria.getMinLevel(), criteria.getMaxLevel()))
                .and(greaterThan(Player.PlayerField.LEVEL, criteria.getMinLevel()))
                .and(lessThan(Player.PlayerField.LEVEL, criteria.getMaxLevel()))
                .and(equal(Player.PlayerField.BANNED, criteria.getBanned()))
                .and(equal(Player.PlayerField.RACE, criteria.getRace()))
                .and(equal(Player.PlayerField.PROFESSION, criteria.getProfession()));
    }

    @Override
    public Pageable buildPageable(PageCriteria pageCriteria) {
        Sort sort = Sort.by(pageCriteria.getOrder());
        return PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), sort);
    }

    @Override
    public JpaSpecificationExecutor<Player> getSpecificationExecutor() {
        return playerRepository;
    }
}
