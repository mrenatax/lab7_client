package SpaceMarineData;
import java.io.Serializable;

/**
 * Класс Weapon содержит перечисление возможных вариантов поля weaponType класса SpaceMarine
 */
public enum Weapon implements Serializable {
    BOLTGUN,
    HEAVY_BOLTGUN,
    GRENADE_LAUNCHER,
    HEAVY_FLAMER,
    MULTI_MELTA;
    private static final long serialVersionUID = 6L;
}

