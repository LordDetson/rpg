package com.game.entity.dto;

import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.validator.BirthdayConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class PlayerUpdateDto {

    @Size(min = 2, max = 12, message = "Name length must be between 2 and 12 characters")
    String name;

    @Size(max = 30, message = "Title must not exceed 30 characters")
    String title;

    Race race;

    Profession profession;

    @Range(min = 0, max = 10_000_000, message = "Experience must be between 0 and 10_000_000")
    Integer experience;

    @BirthdayConstraint
    Date birthday;

    Boolean banned;
}
