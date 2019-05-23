package Game.Team.EventListener;

import Game.Team.Manager.TeamManager;
import Game.Team.Players.Teamer;
import Game.Team.Team;
import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.components.VexHoverText;
import lk.vexview.gui.components.VexImage;
import lk.vexview.gui.components.VexText;
import lk.vexview.newinv.VexPage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamListener implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        TeamManager.getInstance().setPlayerGamaer(event.getPlayer());

       
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent entitySpawnEvent){
        System.out.println(entitySpawnEvent.getLocation().toString());
        System.out.println(Bukkit.getPlayer("t1").getLocation().toString());
        System.out.println(entitySpawnEvent.getEntityType().getName());
        System.out.println(entitySpawnEvent.getEntityType().getTypeId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Teamer teamer=TeamManager.getInstance().getPlayerGamaer().get(event.getPlayer());
        if(teamer.isLeader()){
            //解散队伍
            teamer.getTeam().disMissTeam();
        }else if(teamer.hasTeam()){
            teamer.getTeam().sendMessageToAll(teamer.getName()+"离开了队伍");
            teamer.leaveTeam();
        }
        TeamManager.getInstance().clearTeamer(event.getPlayer());
    }
}
