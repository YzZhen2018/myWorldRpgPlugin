package Game.Classes.archer;

import Game.Classes.BaseClass;
import Game.Classes.BaseSkill;
import Game.Classes.Career;
import Game.Classes.CareerClasser;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Archer extends CareerClasser{


    List<BaseSkill> skills=new ArrayList<>();
    private Career career;
    private String careerName;

    private int level;
    private int maxLevel;

    public Archer(Player player){
        super(player);
        career=Career.ARCHER;
        careerName=career.getCareerName();
        level=0;
    }

    @Override
    public void setAgile() {

    }

    @Override
    public void setPower() {

    }

    @Override
    public void setMagic() {

    }

    @Override
    public void setHeal() {

    }

    @Override
    public void setEndurance() {

    }

    @Override
    public void setPhysicalDamage() {

    }

    @Override
    public void setMagicDamage() {

    }

    @Override
    public String getCareerName() {
        return careerName;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }
}
