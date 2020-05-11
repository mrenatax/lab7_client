package ClientAnswer;

import SpaceMarineData.SpaceMarine;
import java.io.Serializable;
public class ComplicatedObject implements Serializable {
    private String command;
    private SpaceMarine spaceMarine;
    private int id;
    private Long param;
    private int p;
    private String history;
    ComplicatedObject(String command){
        this.command = command;
    }
    ComplicatedObject(String command, SpaceMarine spaceMarine){
        this.command = command;
        this.spaceMarine = spaceMarine;
    }
    ComplicatedObject(String command, String history){
        this.command = command;
        this.history = history;
    }
    ComplicatedObject(String command, int id, SpaceMarine spaceMarine){
        this.command = command;
        this.id = id;
        this.spaceMarine = spaceMarine;
    }
    ComplicatedObject(String command, Long param){
        this.command = command;
        this.param = param;
    }
    ComplicatedObject(String command, int p){
        this.command = command;
        this.p = p;
    }
    public String getCommand(){
        return command;
    }
    public SpaceMarine getSpaceMarine(){
        return spaceMarine;
    }
    public Long getParam(){return param;}
    public int getId(){
        return id;
    }
    public int getP(){return p;}
    public String getHistory(){return history;}
    @Override
    public String toString() {
        return ("command: " + command + "\n" );
    }
}
