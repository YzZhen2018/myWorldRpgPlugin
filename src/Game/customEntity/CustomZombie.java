package Game.customEntity;

import com.google.common.collect.Sets;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import static Game.customEntity.Uitl.getPrivateField;


public class CustomZombie extends EntityZombie {



    public CustomZombie(Location location) {
        super(((CraftWorld) location.getWorld()).getHandle());
        this.setCustomName("我的僵尸");
        this.setCustomNameVisible(true);
        // 需要设置该实体的Position才会出现在对应的Location
        this.setPosition(location.getX(), location.getY(), location.getZ());
    }

    public boolean a() {

        return true;
    }

    public void r(){
        System.out.println("开启生物ai");
    }

//    private static MinecraftKey minecraftKey;
//    static {
//        minecraftKey = new MinecraftKey("my_zombie"); // minecraft:my_zombie
//        // 实体注册
//        EntityTypes.d.add(minecraftKey); // 将此key添加至EntityTypes的列表里
//        EntityTypes.b.a(54, minecraftKey, CustomZombie.class);
//    }
//
//    public CustomZombie(World world) throws IllegalAccessException {
//        super(((CraftWorld)world).getHandle());
//        clearAI();
//    }
//
//
//    public boolean a(EntityHuman entityhuman, EnumHand enumhand,  ItemStack itemstack) {
//        if (itemstack == null) {
//            return false;
//        }
//        if (itemstack.getItem() == Items.APPLE) {
//            if (!entityhuman.abilities.canInstantlyBuild) {
//                itemstack.setCount(itemstack.getCount()-1);
//            }
//            entityhuman.getBukkitEntity().sendMessage("真香");
//        } else {
//            entityhuman.getBukkitEntity().sendMessage("我不喜欢这个...");
//        }
//        return false;
//    }
//
//    public void clearAI() throws IllegalAccessException {
//        Set goalB = (Set )getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
//        Set  goalC = (Set )getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
//        Set  targetB = (Set )getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
//        Set  targetC = (Set )getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
//        this.goalSelector.a(0, new PathfinderGoalFloat(this));
//        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
//        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.2D));
//        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0, true));
//        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 0.2D, false));
//        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 0.2D));
//        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
//
//        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
//        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.20000000298023224D);
//    }
//

}
