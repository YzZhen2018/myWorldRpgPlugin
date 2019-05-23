package Game.customEntity;

import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityTypes;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.util.Map;

public enum CustomEntities {

    ZOMBIE("WOWZombie", 121, EntityType.ZOMBIE, CustomZombie.class, CustomZombie.class);

    private String name;
    private int id;
    private EntityType entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;

    CustomEntities(String name, int id, EntityType entityType,
                   Class<? extends EntityInsentient> nmsClass,
                   Class<? extends EntityInsentient> customClass) {
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Class<? extends EntityInsentient> getNMSClass() {
        return nmsClass;
    }

    public Class<? extends EntityInsentient> getCustomClass() {
        return customClass;
    }

    public static void registerEntities() {
        for (CustomEntities entity : values())
            a(entity.getCustomClass(), entity.getName(), entity.getID());
    }

    @SuppressWarnings("rawtypes")
    public static void unregisterEntities() {
        for (CustomEntities entity : values()) {
            try {
                ((Map) getPrivateStatic(EntityTypes.class, "d")).remove(entity.getCustomClass());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                ((Map) getPrivateStatic(EntityTypes.class, "f")).remove(entity.getCustomClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (CustomEntities entity : values())
            try {
                a(entity.getNMSClass(), entity.getName(), entity.getID());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @SuppressWarnings("rawtypes")
    private static Object getPrivateStatic(Class clazz, String f) throws Exception {
        Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);
        return field.get(null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void a(Class paramClass, String paramString, int paramInt) {
        try {
            ((Map) getPrivateStatic(EntityTypes.class, "c")).put(paramString, paramClass);
            ((Map) getPrivateStatic(EntityTypes.class, "d")).put(paramClass, paramString);
            ((Map) getPrivateStatic(EntityTypes.class, "e")).put(paramInt,
                    paramClass);
            ((Map) getPrivateStatic(EntityTypes.class, "f")).put(paramClass,
                    paramInt);
            ((Map) getPrivateStatic(EntityTypes.class, "g")).put(paramString,
                    paramInt);
        } catch (Exception ignored) {
        }
    }
}