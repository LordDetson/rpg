package com.game.validator;

import com.game.entity.Player;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PlayerValidator implements Validator<Player> {

    @Override
    public List<String> validate(Player player) {
        List<String> errors = new ArrayList<>();
        if (player.getName() != null && !validateName(player.getName())) {
            errors.add("name can't be empty and less 12 characters");
        }
        if (player.getTitle() != null && !validateTitle(player.getTitle())) {
            errors.add("title can't be empty and less 30 characters");
        }
        if (player.getExperience() != null && !validateExperience(player.getExperience())) {
            errors.add("experience should be more than 0 and less 10000000");
        }
        if (player.getBirthday() != null &&!validateBirthday(player.getBirthday())) {
            errors.add("birthday should be more than 2000 and less 3000");
        }
        return errors;
    }

    private boolean validateName(String name) {
        return StringUtils.hasText(name) && name.length() <= 12;
    }

    private boolean validateTitle(String title) {
        return StringUtils.hasText(title) && title.length() <= 30;
    }

    private boolean validateExperience(Integer experience) {
        return experience >= 0 && experience <= 10000000;
    }

    private boolean validateBirthday(Date birthday) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(birthday.toInstant(), ZoneId.systemDefault());
        int year = dateTime.getYear();
        return year >= 2000 && year <= 3000;
    }
}
