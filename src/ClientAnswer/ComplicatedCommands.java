package ClientAnswer;

import SpaceMarineData.SpaceMarine;
import java.util.InputMismatchException;
import java.util.Scanner;
class ComplicatedCommands {
    private static void funcForName(SpaceMarine s){
        if ((s.getName() ==null) ||(s.getName().trim().length()==0)){
            System.out.println("Поле NAME заполнено некорректно ");
            System.out.println("Повторите ввод имени NAME: ");
            s.setName();
            funcForName(s);
        }
    }
    private static void funcForHealth(SpaceMarine s){
        if ((s.getHealth() == null)||(s.getHealth() <= 0)||(s.getHealth().toString().trim().length()==0)){
            System.out.println("Поле HEALTH заполнено некорректно: не может быть null, должно быть больше нуля ");
            System.out.println("Повторите ввод поля HEALTH: ");
            s.setHealth();
            funcForHealth(s);
        }
    }
    private static void funcForHeight(SpaceMarine s){
        if (s.getHeight() == null) {
            System.out.println("Поле HEIGHT заполнено некорректно: не может быть null");
            System.out.println("Повторите ввод поля HEIGHT: ");
            s.setHeight();
            funcForHeight(s);
        }
    }
    private static void funcForMeleeWeapon(SpaceMarine s){
        if (s.getMeleeWeapon() == null) {
            System.out.println("Поле MeleeWeapon заполнено некорректно");
            System.out.println("Повторите ввод поля MELEEWEAPON: ");
            s.setMeleeWeapon();
            funcForMeleeWeapon(s);
        }
    }
    private static void funcForCoordinates(SpaceMarine s){
        if (s.getCoordinates()==null){
            System.out.println("Поле coordinates заполнено некорректно: не может быть null");
            System.out.println("Повторите ввод поля COORDINATES: ");
            s.setCoordinates();
            funcForCoordinates(s);
        }
    }
    private static void funcForCoordinatesFields(SpaceMarine s){ // рекурсивная функция для проверки x(coordinates) и вывода сообщения об ошибке ввода
        if(s.getCoordinates().getX() == null){
            System.out.println("Поле coordinates(x) заполнено некорректно: не может быть null");
            System.out.println("Повторите ввод поля COORDINATES(X): ");
            s.setCoordinates();
            funcForCoordinatesFields(s);
        }
    }
    private static void funcForChapterFields(SpaceMarine s){   // рекурсивная функция для проверки полей chapter и вывода сообщения об ошибке ввода
        if((s.getChapter().getChapterName().trim().length()==0)||(s.getChapter().getChapterName()==null)||((s.getChapter().getMarinesCount())>1000)||((s.getChapter().getMarinesCount())<=0)){
            System.out.println("Поля chapter заполнены некорректно: (Chapter.name) не может быть null, строка не может быть пустой, (Chapter.marinescount) больше нуля  и меньше 1000");
            System.out.println("Повторите ввод поля CHAPTER: ");
            s.setChapter();
            funcForChapterFields(s);
        }
    }
    static SpaceMarine add() {
        SpaceMarine sm =new SpaceMarine();
        sm.setCreationDate();
        sm.setLogin();
        sm.setName();
        funcForName(sm);
        System.out.println("Введите значение HEALTH:");
        sm.setHealth();
        funcForHealth(sm);
        System.out.println("Введите значение HEIGHT:");
        sm.setHeight();
        funcForHeight(sm);
        String s ="Доступны следующие поля WEAPON: \n"+
                "BOLTGUN\n" +
                "HEAVY_BOLTGUN\n" +
                "GRENADE_LAUNCHER\n" +
                "HEAVY_FLAMER\n" +
                "MULTI_MELTA\n"+
                "при ввод другого значения поле будет считаться null";
        System.out.println(s);
        System.out.println("Введите значение поля WEAPON:");
        sm.setWeaponType();
        String st = "Доступны следующие поля MELEEWEAPON: \n"+
                "CHAIN_SWORD\n" +
                "CHAIN_AXE\n" +
                "LIGHTING_CLAW\n" +
                "POWER_FIST";
        System.out.println(st);
        System.out.println("Введите значение поля MELEEWEAPON:");
        sm.setMeleeWeapon();
        funcForMeleeWeapon(sm);
        System.out.println("Введите значение поля COORDINATES в формате (x y): ");
        sm.setCoordinates();
        funcForCoordinates(sm);
        funcForCoordinatesFields(sm);
        System.out.println("Введите значение поля Chapter в формате (name marinesCount): ");
        sm.setChapter();
        funcForChapterFields(sm);
        return sm;
    }
    static int updateIdFirstPart() {
        int x = 0;
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите id объекта, которому хотите обновить данные: ");
            x = scan.nextInt();
            return x;
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода, поле должно быть int.");
            updateIdFirstPart();
        }
        return x;
    }

    static SpaceMarine updateIdSecondPart()  {
        System.out.println("Введите новое имя NAME SpaceMarine:");
        SpaceMarine sm =new SpaceMarine();
        sm.setId();
        sm.setCreationDate();
        sm.setName();
        funcForName(sm);
        System.out.println("Введите значение HEALTH:");
        sm.setHealth();
        funcForHealth(sm);
        System.out.println("Введите значение HEIGHT:");
        sm.setHeight();
        funcForHeight(sm);
        String s ="Доступны следующие поля WEAPON: \n"+
                "BOLTGUN\n" +
                "HEAVY_BOLTGUN\n" +
                "GRENADE_LAUNCHER\n" +
                "HEAVY_FLAMER\n" +
                "MULTI_MELTA\n"+
                "при ввод другого значения поле будет считаться null";
        System.out.println(s);
        System.out.println("Введите значение поля WEAPON:");
        sm.setWeaponType();
        String st = "Доступны следующие поля MELEEWEAPON: \n"+
                "CHAIN_SWORD\n" +
                "CHAIN_AXE\n" +
                "LIGHTING_CLAW\n" +
                "POWER_FIST";
        System.out.println(st);
        System.out.println("Введите значение поля MELEEWEAPON:");
        sm.setMeleeWeapon();
        funcForMeleeWeapon(sm);
        System.out.println("Введите значение поля COORDINATES в формате (x y)");
        sm.setCoordinates();
        funcForCoordinates(sm);
        funcForCoordinatesFields(sm);
        System.out.println("Введите значение поля Chapter в формате (name marinesCount)");
        sm.setChapter();
        funcForChapterFields(sm);
        return sm;
    }
    static Long filterGreater(){
        long min= 0L;
        System.out.println("Введите граничное значение для поля height");
        try {
            Scanner scanner = new Scanner(System.in);
            min = scanner.nextLong();

        } catch (InputMismatchException e) {
            System.out.println("Поле заполнено некорректно, повторите: ");
            filterGreater();
        }
        return min;
    }
    static int removeById(){
        int x = 0;
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите id объекта, который хотите удалить");
            x = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Поле заполнено некорректно, повторите ввод:");
            removeById();
        }
        return x;
    }
    static int removeGreater(){
        int j  = 0;
        try {
            System.out.println("Введите номер граничного элемента:");
            Scanner sc = new Scanner(System.in);
            j = sc.nextInt() - 1;
            if (j < 0) {
                System.out.println("Ошибка ввода, число должно быть целым и больше нуля. Повторите ввод:");
                removeGreater();
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода, должно быть целое положительное число. Повторите ввод:");
            removeGreater();
        }
        return j;
    }
}
