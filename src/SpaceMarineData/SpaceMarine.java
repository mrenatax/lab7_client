package SpaceMarineData;

import CommandProcessing.Commands;
import ReceivingConnections.Receiver;
import java.io.Serializable;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.time.Instant;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "id", "coordinates", "creationDate", "health", "height", "weaponType", "meleeWeapon", "chapter"})
public class SpaceMarine implements Comparable<SpaceMarine>,Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private Long height; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null
    private String login;

    private static final long serialVersionUID = -6634144303926521364L;
    public SpaceMarine(){} //default no-arg constructor
    public SpaceMarine(String name, int id, Long health, Long height, Chapter chapter, Coordinates coordinates, MeleeWeapon meleeWeapon, Weapon weaponType, String login)  {
        if ((name == null) ||(name.trim().length()==0)) throw new IllegalArgumentException("Поле NAME должно быть не null, строка не может быть пустой ");
        if ((health <= 0)||(health == null)) throw new IllegalArgumentException("Поле HEALTH должно быть больше нуля, не может быть null");
        if (height == null) throw new IllegalArgumentException("Поле HEIGHT не может быть null");
        if (coordinates == null) throw new IllegalArgumentException("Поле COORDINATES не может быть null");
        if(meleeWeapon == null) throw  new IllegalArgumentException("Поле MELEEWEAPON не может быть null");
        this.creationDate = java.util.Date.from(Instant.now());
        this.id = id;
        this.name = name;
        this.health = health;
        this.height = height;
        this.coordinates = coordinates;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
        this.login = login;
        if(id <= 0) throw new IllegalArgumentException("Поле ID должно быть больше нуля");
    }
    public void setLogin(){
        login = Receiver.login;
    }
    public String getLogin(){
        return login;
    }
    public void setNewd(int ix){
        id =ix;
    }
    public void setNewId(){
        id = (int)(Math.random()*(1000+1));
    }
    public void setId(){
        id = (int)(Math.random()*(1000+1));
    }
    public void setCreationDate(){
        creationDate = java.util.Date.from(Instant.now());
    }
    public void setWeaponType(){
        Scanner sv = new Scanner(System.in);
        String s = sv.nextLine();
        switch (s){
            case ("BOLTGUN"):
                weaponType = Weapon.BOLTGUN;
                break;
            case ("boltgun"):
                weaponType = Weapon.BOLTGUN;
                break;
            case ("HEAVY_BOLTGUN"):
                weaponType = Weapon.HEAVY_BOLTGUN;
                break;
            case ("heavy_boltgun"):
                weaponType = Weapon.HEAVY_BOLTGUN;
                break;
            case ("HEAVY_FLAMER"):
                weaponType = Weapon.HEAVY_FLAMER;
                break;
            case ("heavy_flamer"):
                weaponType = Weapon.HEAVY_FLAMER;
                break;
            case ("GRENADE_LAUNCHER"):
                weaponType = Weapon.GRENADE_LAUNCHER;
                break;
            case  ("grenade_launcher"):
                weaponType = Weapon.GRENADE_LAUNCHER;
                break;
            case ("MULTI_MELTA"):
                weaponType = Weapon.MULTI_MELTA;
                break;
            case ("multi_melta"):
                weaponType = Weapon.MULTI_MELTA;
                break;
        }
        if (s.trim().length() == 0) weaponType = null;
    }
    public void setMeleeWeapon(){
        Scanner sv = new Scanner(System.in);
        String s = sv.nextLine();
        switch (s){
            case("CHAIN_AXE"):
                meleeWeapon = MeleeWeapon.CHAIN_AXE;
                break;
            case("chain_axe"):
                meleeWeapon = MeleeWeapon.CHAIN_AXE;
                break;
            case("CHAIN_SWORD"):
                meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                break;
            case("chain_sword"):
                meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                break;
            case("LIGHTING_CLAW"):
                meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
                break;
            case("lighting_claw"):
                meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
                break;
            case ("POWER_FIST"):
                meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
            case ("power_fist"):
                meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
        }
    }
    public void setHealth(){
        try {
            Scanner sv = new Scanner(System.in);
            health =  Long.parseLong(sv.nextLine());
        }
        catch (InputMismatchException e){
            System.out.println("Поле заполнено некорректно, повторите ввод:");
            setHealth();
        }
        catch (NumberFormatException e){
            System.out.println("Поле должно быть целочисленным, повторите ввод");
            setHealth();
        }
    }
    /**
     * Метод устанавливает значение поля height, считывая сканером строку
     * @exception InputMismatchException
     * @exception NumberFormatException
     */
    public void setHeight(){
        try {
            Scanner sv = new Scanner(System.in);
            height = Long.parseLong(sv.nextLine());
        }
        catch (InputMismatchException e){
            System.out.println("Поле заполнено некорректно, повторите ввод: ");
            setHeight();
        }
        catch (NumberFormatException e){
            System.out.println("Поле должно быть целочисленным, повторите ввод");
            setHeight();
        }
    }
    /**
     * Метод устанавливает значение поля name, считывая данные из скрипта
     */
    public void setNameF() throws IOException {
        name = Commands.slmmsk.nextLine();
    }
    /**
     * Метод устанавливает значение поля health, считывая данные из скрипта
     */
    public void setHealthF(){
        try{
            health = Long.parseLong(Commands.slmmsk.nextLine());}
        catch (InputMismatchException  e){
            System.out.println("поле health в скрипте заполнено некоррректно");
        }
    }
    /**
     * Метод устанавливает значение поля height, считывая данные из скрипта
     */
    public void setHeightF(){
        try{
            height = Long.parseLong(Commands.slmmsk.nextLine());}
        catch (InputMismatchException e){
            System.out.println("поле height в скрипте заполнено некорректно");
        }
    }
    /**
     * Метод возвращает значение поля weaponType
     * @return Weapon
     */
    public Weapon getWeapon(){
        return weaponType;
    }

    /**
     * Метод устанавливает значение поля meleeWeapon, считывая данные из скрипта
     */
    public void setMeleeWeaponF() throws IOException {
        String meleeweapon =  Commands.slmmsk.nextLine();
        switch (meleeweapon){
            case("CHAIN_AXE"):
                meleeWeapon = MeleeWeapon.CHAIN_AXE;
                break;
            case("chain_axe"):
                meleeWeapon = MeleeWeapon.CHAIN_AXE;
                break;
            case("CHAIN_SWORD"):
                meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                break;
            case("chain_sword"):
                meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                break;
            case("LIGHTING_CLAW"):
                meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
                break;
            case("lighting_claw"):
                meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
                break;
            case ("POWER_FIST"):
                meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
            case ("power_fist"):
                meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
        }

    }
    /**
     * Метод устанавливает значение поля weaponType, считывая данные из скрипта
     */
    public void setWeaponTypeF(){
        String s = Commands.slmmsk.nextLine();
        switch (s){
            case ("BOLTGUN"):
                weaponType = Weapon.BOLTGUN;
                break;
            case ("boltgun"):
                weaponType = Weapon.BOLTGUN;
                break;
            case ("HEAVY_BOLTGUN"):
                weaponType = Weapon.HEAVY_BOLTGUN;
                break;
            case ("heavy_boltgun"):
                weaponType = Weapon.HEAVY_BOLTGUN;
                break;
            case ("HEAVY_FLAMER"):
                weaponType = Weapon.HEAVY_FLAMER;
                break;
            case ("heavy_flamer"):
                weaponType = Weapon.HEAVY_FLAMER;
                break;
            case ("GRENADE_LAUNCHER"):
                weaponType = Weapon.GRENADE_LAUNCHER;
                break;
            case  ("grenade_launcher"):
                weaponType = Weapon.GRENADE_LAUNCHER;
                break;
            case ("MULTI_MELTA"):
                weaponType = Weapon.MULTI_MELTA;
                break;
            case ("multi_melta"):
                weaponType = Weapon.MULTI_MELTA;
                break;
        }
        if (s.trim().length() == 0) weaponType = null;
    }
    /**
     * Метод устанавливает значение поля coordinates, считывая данные из скрипта
     */
    public Coordinates setCoordinatesF(){
        this.coordinates = new Coordinates();
        coordinates.setXYForScript();
        return coordinates;
    }

    /**
     * Метод устанавливает значение поля chapter, считывая данные из скрипта
     */
    public void setChapterF(){
        this.chapter = new Chapter();
        chapter.setChapterFieldsForScript();
    }
    /**
     * Метод устанавливает значение поля name
     */
    public void setName(){
        Scanner sv = new Scanner(System.in);
        name = sv.nextLine();
    }
    /**
     * Метод устанавливает значение поля coordinates
     */
    public void setCoordinates(){
        this.coordinates = new Coordinates();
        coordinates.setXY();
    }
    /**
     * Метод устанавливает значение поля chapter
     */
    public void setChapter(){
        this.chapter = new Chapter();
        chapter.setChapterFields();
    }
    /**
     * Метод возвращает значение поля name
     * @return String
     */
    public String getName(){
        return name ;
    }
    /**
     * Метод возвращает значение поля coordinates
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
    /**
     * Метод возвращает значение поля height
     * @return Long
     */
    public Long getHeight() {
        return height;
    }
    /**
     * Метод возвращает значение поля health
     * @return Long
     */
    public Long getHealth() {
        return health ;
    }
    /**
     * Метод возвращает значение поля meleeWeapon
     * @return MeleeWeapon
     */
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }
    /**
     * Метод возвращает значение поля chapter
     * @return Chapter
     */
    public Chapter getChapter() {
        return chapter;
    }
    /**
     * Метод возвращает значение поля creationDate
     * @return Date
     */
    public Date getCreationDate() {
        return creationDate;
    }
    /**
     * Метод возвращает значение поля id
     * @return int
     */
    public Integer getId(){
        return id;
    }
    @Override
    public String toString() {
        return ( "\n" + "Название: " + name + "\n" +
                "id: " + id + "\n" +
                "Координаты: " + coordinates + "\n" +
                "chapter: " + chapter + "\n" +
                "health: " + health + "\n" +
                "height: " + height + "\n" +
                "weaponType: " + weaponType + "\n" +
                "meleeWeapon: " + meleeWeapon + "\n" +
                "creationDate: " + creationDate + "\n" +
                "creator's login: " + login
        );
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public int compareTo(SpaceMarine o) {
        return Integer.compare(this.name.length(), o.name.length());
    }
}

