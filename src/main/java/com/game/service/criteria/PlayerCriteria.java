package com.game.service.criteria;

import com.game.entity.Profession;
import com.game.entity.Race;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class PlayerCriteria {

    @Size(min = 2, max = 12, message = "Name length must be between 2 and 12 characters")
    String name;

    @Size(max = 30, message = "Title must not exceed 30 characters")
    String title;

    Race race;

    Profession profession;

    Long after;

    Long before;

    Boolean banned;

    @Range(min = 0, max = 10_000_000, message = "minExperience must be between 0 and 10_000_000")
    Integer minExperience;

    @Range(min = 0, max = 10_000_000, message = "maxExperience must be between 0 and 10_000_000")
    Integer maxExperience;

    @PositiveOrZero
    Integer minLevel;

    @PositiveOrZero
    Integer maxLevel;

    public Date getStartDate() {
        if (after != null) {
            return new Date(after);
        }
        return null;
    }

    public Date getFinishDate() {
        if (before != null) {
            return new Date(before);
        }
        return null;
    }
}
