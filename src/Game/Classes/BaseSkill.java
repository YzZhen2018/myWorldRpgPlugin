package Game.Classes;

import javax.xml.stream.Location;

public interface BaseSkill {
    //使用等级
    int getUseLevel();
    //目前等级
    int getTollLevel();
    //最高等级
    int getMaxLevel();
    //获取最终伤害
    double getFinalDamage(double value);
    //获取归属职业
    BaseClass getBelongClass();
    //释放效果
    void playEffect(Location location);



}
