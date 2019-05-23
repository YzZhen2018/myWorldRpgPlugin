package Game.Classes;

import org.bukkit.entity.Player;

public abstract class CareerClasser implements BaseClass{
    private Player player;
    private int agile;
    private int power;
    private int magic;
    private int heal;
    private int endurance;

    private int physical_damage;
    private int  magic_damage;

    public CareerClasser(Player player){
        agile=0;
        power=0;
        magic=0;
        heal=0;
        endurance=0;
        this.player=player;
    }

    public abstract void setAgile();
    public abstract void setPower();
    public abstract void setMagic();
    public abstract void setHeal();
    public abstract void setEndurance();
    public abstract void setPhysicalDamage();
    public abstract void setMagicDamage();

    public Player getPlayer() {
        return player;
    }

    public int getAgile() {
        return agile;
    }

    public int getPower() {
        return power;
    }

    public int getMagic() {
        return magic;
    }

    public int getHeal() {
        return heal;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getPhysical_damage() {
        return physical_damage;
    }

    public int getMagic_damage() {
        return magic_damage;
    }
}
