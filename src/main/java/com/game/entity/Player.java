package com.game.entity;

import com.game.validator.BirthdayConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Entity
@Table(name = "player")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"experience", "untilNextLevel", "banned"})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column(nullable = false, unique = true, length = 12)
    @EqualsAndHashCode.Include
    String name;

    @Column(length = 30)
    String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Race race;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Profession profession;

    @Column(nullable = false, columnDefinition = "int unsigned", precision = 8)
    Integer experience;

    @Column(nullable = false, columnDefinition = "int unsigned")
    Integer level;

    @Column(nullable = false, columnDefinition = "int unsigned", precision = 8)
    Integer untilNextLevel;

    @Column(nullable = false)
    @BirthdayConstraint
    Date birthday;

    @Column(nullable = false, columnDefinition = "boolean default false")
    Boolean banned;

    public enum PlayerField implements Field<Player> {
        ID("id", Long.class, Player::getId),
        NAME("name", String.class, Player::getName, (player, value) -> player.setName((String) value)),
        TITLE("title", String.class, Player::getTitle, (player, value) -> player.setTitle((String) value)),
        RACE("race", Race.class, Player::getRace, (player, value) -> player.setRace((Race) value)),
        PROFESSION("profession", Profession.class, Player::getProfession, (player, value) -> player.setProfession((Profession) value)),
        EXPERIENCE("experience", Integer.class, Player::getExperience, (player, value) -> player.setExperience((Integer) value)),
        LEVEL("level", Integer.class, Player::getLevel),
        UNTIL_NEXT_LEVEL("untilNextLevel", Integer.class, Player::getUntilNextLevel),
        BIRTHDAY("birthday", Date.class, Player::getBirthday, (player, value) -> player.setBirthday((Date) value)),
        BANNED("banned", Boolean.class, Player::getBanned, (player, value) -> player.setBanned((Boolean) value)),
        ;

        private final String fieldName;
        private final Class<?> type;
        private final Function<Player, Object> getter;
        private final BiConsumer<Player, Object> setter;

        PlayerField(String fieldName, Class<?> type, Function<Player, Object> getter) {
            this(fieldName, type, getter, null);
        }

        PlayerField(String fieldName, Class<?> type, Function<Player, Object> getter, BiConsumer<Player, Object> setter) {
            this.fieldName = fieldName;
            this.type = type;
            this.getter = getter;
            this.setter = setter;
        }

        @Override
        public String getFieldName() {
            return fieldName;
        }

        @Override
        public Class<?> getType() {
            return type;
        }

        @Override
        public Object getValue(Player player) {
            if (getter != null) {
                return getter.apply(player);
            }
            return null;
        }

        @Override
        public void setValue(Player player, Object value) {
            if (setter != null) {
                setter.accept(player, value);
            }
        }
    }
}
