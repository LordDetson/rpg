package com.game.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12)
    private String name;

    @Column(nullable = false, length = 30)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Race race;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Profession profession;

    @Column(nullable = false, columnDefinition = "int unsigned", precision = 8)
    private Integer experience;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false, columnDefinition = "int unsigned", precision = 8)
    private Integer untilNextLevel;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean banned;

    protected Player() {
    }

    public Player(String name, String title, Race race, Profession profession, Integer experience, Date birthday) {
        this(name, title, race, profession, experience, birthday, false);
    }

    public Player(String name, String title, Race race, Profession profession, Integer experience, Date birthday, Boolean banned) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.birthday = birthday;
        this.banned = banned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", race=").append(race);
        sb.append(", profession=").append(profession);
        sb.append(", level=").append(level);
        sb.append('}');
        return sb.toString();
    }

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
