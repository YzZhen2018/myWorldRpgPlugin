package Main;

import Game.Team.Command.TeamCMD;
import Game.Team.EventListener.TeamListener;
import Game.customEntity.CustomEntities;
import Game.customEntity.CustomEntityRegistry;
import Game.customEntity.CustomZombie;
import Game.customEntity.NMSUtils;
import net.minecraft.server.v1_12_R1.BiomeBase;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityTypes;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.List;

public class PP extends JavaPlugin{
    private static MinecraftKey minecraftKey;

    @Override
    public void onEnable() {
        this.getCommand("t").setExecutor(new TeamCMD(this));
        getServer().getPluginManager().registerEvents(new TeamListener(),this);
        System.out.println("载入自定义实体");
//        CustomEntities.registerEntities();
        MinecraftKey key = new MinecraftKey("ranged_zombie");
        EntityTypes.b.a(54, key, CustomZombie.class);
        EntityTypes.d.add(key);

//        if (!override || type.isSpecial()) {
//            return;
//        }
//        Field field;
//        if ((field = type.getMeta().getField()) == null) {
//            return;
//        }
//        try {
//            field.setAccessible(true);
//            for (BiomeBase base : NMSUtils.BIOMES) {
//                List<BiomeBase.BiomeMeta> list = (List<BiomeBase.BiomeMeta>) field.get(base);
//                for (BiomeBase.BiomeMeta meta : list) {
//                    if (meta.b == type.getNMSClass()) {
//                        meta.b = (Class<? extends EntityInsentient>) customClass;
//                        break;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        NMSUtils.registerEntity("ranged_zombie", NMSUtils.Type.ZOMBIE, CustomZombie.class, false);
        System.out.println("载入自定义实体");

//            // 给我们的自定义实体做一个MinecraftKey
//            minecraftKey = new MinecraftKey("customZombie"); // minecraft:my_zombie
//            // 实体注册
//            EntityTypes.d.add(minecraftKey); // 将此key添加至EntityTypes的列表里
//            EntityTypes.b.a(54, minecraftKey, CustomZombie.class); //

    }

    public static MinecraftKey getMinecraftKey() {
        return minecraftKey;
    }


    @Override
    public void onDisable() {
        super.onDisable();
    }
}
