package Game.Team.Command;

import Game.Team.Language.Language;
import Game.Team.Manager.TeamManager;
import Game.Team.Players.Teamer;
import Game.Team.Team;

import Game.customEntity.CustomZombie;
import Main.PP;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftZombie;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class TeamCMD implements CommandExecutor {



    public static Map<Player,Team> playerApplyList=new HashMap<>();

    public static Map<Player,Team> teamApplyList=new HashMap<>();

    private final PP plugin;

    public TeamCMD(PP plugin){
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length<1)return true;
        if(strings[0].equals("create"))createTeam(commandSender,strings[1]);
        if(strings[0].equals("kick"))kickPlayer(commandSender,strings[1]);
        if(strings[0].equals("changeleader"))changeLeader(commandSender,strings[1]);
        if(strings[0].equals("apply"))sendApplyToTeamer(commandSender,strings[1]);
        if(strings[0].equals("join"))SendApplyToPLayerJoinTeam(commandSender,strings[1]);
        if(strings[0].equals("quit"))quitTeam(commandSender);
        if(strings[0].equals("agreejoin"))agreeJoin(commandSender);
        if(strings[0].equals("disagreejoin"))disAgreeJoin(commandSender);
        if(strings[0].equals("agreeapply"))AgreeApply(commandSender,strings[1]);
        if(strings[0].equals("disagreeapply"))disAgreeApply(commandSender,strings[1]);
        if(strings[0].equals("test")) {
            try {
                test(commandSender);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public void test(CommandSender sender) throws IllegalAccessException {
        Player player= (Player) sender;
        Location location = player.getLocation();
// 对自定义进行实例化
        CustomZombie myZombie = new CustomZombie(location);
// 生成至世界内

        CraftWorld craftWorld = (CraftWorld) location.getWorld();
        craftWorld.addEntity(myZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);

    }

    public void AgreeApply(CommandSender sender,String applyName){
        Teamer teamer=TeamManager.getInstance().getPlayerGamaer().get(sender);
        Teamer applyer=TeamManager.getInstance().getPlayerGamaer().get(Bukkit.getPlayer(applyName));
        //请求超时
        if(!teamer.getTeam().isInApplyList(applyer)){
            sender.sendMessage(Language.leaderCMDLeaderAgreeApply4);
            return;
        }
        //你不是队长或你没有队伍
        if(!teamer.isLeader()||!teamer.hasTeam()){
            teamer.sendMessage(Language.leaderCMDLeaderAgreeApply1);
            return;
        }
        //你同意的玩家不在线
        if(!Bukkit.getPlayer(applyName).isOnline()){
            teamer.sendMessage(Language.leaderCMDLeaderAgreeApply2);
            return;
        }
        //申请者已经有队伍了
        if(applyer.hasTeam()){
            teamer.sendMessage(Language.leaderCMDLeaderAgreeApply3);
            return;
        }
        //
        teamer.getTeam().addTeamer(applyName);
        teamer.getTeam().removeTeamerFromApplyList(TeamManager.getInstance().getPlayerGamaer().get(Bukkit.getPlayer(applyName)));
        applyer.sendMessage(Language.leaderCMDLeaderAgreeApply5);
        teamer.sendMessage(Language.leaderCMDLeaderAgreeApply7.replace("<player>",applyName));
    }

    public void disAgreeApply(CommandSender sender,String applyName){
        Teamer teamer=TeamManager.getInstance().getPlayerGamaer().get(sender);
        Teamer applyer=TeamManager.getInstance().getPlayerGamaer().get(Bukkit.getPlayer(applyName));
        //请求超时
        if(!teamer.getTeam().isInApplyList(applyer)){
            sender.sendMessage(Language.leaderCMDLeaderAgreeApply4);
            return;
        }
        //你不是队长或你没有队伍
        if(!teamer.isLeader()||!teamer.hasTeam()){
            teamer.sendMessage(Language.leaderCMDLeaderAgreeApply1);
            return;
        }
        //你同意的玩家不在线
        if(!Bukkit.getPlayer(applyName).isOnline()){
            teamer.sendMessage(Language.leaderCMDLeaderAgreeApply2);
            return;
        }
        //申请者已经有队伍了
        if(applyer.hasTeam()) {
            teamer.sendMessage(Language.leaderCMDLeaderAgreeApply3);
            return;
        }
        teamer.getTeam().removeTeamerFromApplyList(TeamManager.getInstance().getPlayerGamaer().get(Bukkit.getPlayer(applyName)));
        Bukkit.getPlayer(applyName).sendMessage(Language.leaderCMDLeaderAgreeApply6);
        teamer.sendMessage(Language.leaderCMDLeaderAgreeApply8);
    }



    public void agreeJoin(CommandSender sender){
        if(playerApplyList.get(sender)==null){
            sender.sendMessage(Language.leaderCMDAgreeMessag1);
            return;
        }
        Team team=playerApplyList.get(sender);
        team.addTeamer(sender.getName());
        team.getLeader().sendMessage(sender.getName()+"同意加入你的队伍");
        sender.sendMessage("你同意加入了"+team.getLeader().getName()+"的队伍"+team.getTeamName());
        playerApplyList.remove(sender);
    }

    public void disAgreeJoin(CommandSender sender){
        if(playerApplyList.get(sender)==null){
            sender.sendMessage(Language.leaderCMDAgreeMessag1);
            return;
        }
        Team team=playerApplyList.get(sender);
        sender.sendMessage("你拒绝加入队伍");
        team.getLeader().sendMessage(sender.getName()+"拒绝加入队伍");
        playerApplyList.remove(sender);

    }

    //邀请玩家进入队伍
    public void SendApplyToPLayerJoinTeam(CommandSender sender,String playerName){
        //
        Teamer leader=TeamManager.getInstance().getPlayerGamaer().get(sender);
        if(Bukkit.getPlayer(playerName)==null){
            leader.sendMessage(Language.leaderCMDSendPlayerApplyMessageError1);
            return;
        }
        Teamer teamer=TeamManager.getInstance().getPlayerGamaer().get(Bukkit.getPlayer(playerName));
        //邀请玩家不在线
        if(!Bukkit.getPlayer(playerName).isOnline()){
            leader.sendMessage(Language.leaderCMDSendPlayerApplyMessageError1);
            return;
        }
        //你不是队长
        if(!leader.isLeader()){
            teamer.sendMessage(Language.leaderCMDSendPlayerApplyMessageError2);
            return;
        }
        //邀请的玩家已经有队伍了
        if(teamer.hasTeam()){
            leader.sendMessage(Language.leaderCMDSendPlayerApplyMessageError3);
            return;
        }
        //如果正在邀请中
        if(playerApplyList.get(teamer.getPlayer())!=null){
            leader.sendMessage(Language.leaderCMDSendPlayerApplyMessageError5);
            return;
        }
        //
        TextComponent a1 = new TextComponent(("&7[&a同意&7]  ".replace("&", "§")));
        a1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team agreejoin"));
        a1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("同意邀请!").color(ChatColor.BLUE).create()));
        System.out.println(a1.toString());
        TextComponent a2 = new TextComponent(("&7[&a拒绝&7]  ").replace("&", "§"));
        a2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team disagreejoin"));
        a2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("拒绝邀请!").color(ChatColor.BLUE).create()));
        leader.sendMessage("正在请求"+teamer.getName()+"同意!");
        teamer.getPlayer().sendMessage(("&6&m&l|&a&m&l---&8&m&l---------&7&l[&b来自队伍"+leader.getTeam().getTeamName()+"的"+leader.getName()+"组队邀请&7&l]&8&m&l----------&a&m&l---&6&m&l|").replace("&", "§"));
        teamer.getPlayer().spigot().sendMessage(a1);
        teamer.getPlayer().spigot().sendMessage(a2);
        teamer.getPlayer().sendMessage(("&6&m&l|&a&m&l--------------------------------&6&m&l|").replace("&", "§"));
        playerApplyList.put(teamer.getPlayer(),leader.getTeam());
        //移除队列
        new BukkitRunnable(){
            @Override
            public void run() {
                if(playerApplyList.get(teamer.getPlayer()) == null) {
                    cancel();
                }else if(playerApplyList.get(teamer.getPlayer())!=null){
                    playerApplyList.remove(teamer.getPlayer());
                    cancel();
                }
            }
        }.runTaskLater(plugin,200);
    }

    //退出队伍
    public void quitTeam(CommandSender sender){
        Teamer teamer=TeamManager.getInstance().getPlayerGamaer().get(sender);
        //如果你没有队伍
        if(!teamer.hasTeam()){
            teamer.sendMessage(Language.leaderCMDQuitMessag1);
            return;
        }
        if(teamer.isLeader()){
            teamer.sendMessage(Language.leaderCMDQuitMessag2);
            return;
        }
        teamer.leaveTeam();
        teamer.sendMessage(Language.leaderCMDQuitMessag3);
    }

    public void createTeam(CommandSender sender,String teamName){
        Player player= (Player) sender;
        if(!player.hasPermission("PP.TEAM.CREATE"))return;
        Teamer teamer=TeamManager.getInstance().getPlayerGamaer().get(player);
        if(teamer.hasTeam()){
            teamer.sendMessage(Language.leaderCMDCreateTeamMessageError2);
            return;
        }
        //这个队伍已经创建
        for (Team team:TeamManager.getInstance().getTeams()) {
            if(team.getTeamName().equals(teamName)){
                sender.sendMessage(Language.leaderCMDCreateTeamMessageError);
                return;
            }
        }
        //
        TeamManager.getInstance().createTeam(player,teamName);
        player.sendMessage(Language.leaderCMDCreateTeamMessageError3+": "+teamName);
    }

    //踢出玩家
    public void kickPlayer(CommandSender sender,String playerName){
        Player player= (Player) sender;
        if(!player.hasPermission("PP.TEAM.KICK"))return;
        Teamer teamer= TeamManager.getInstance().getPlayerGamaer().get(player);
        Teamer kicked=TeamManager.getInstance().getPlayerGamaer().get(Bukkit.getPlayer(playerName));
        //踢出玩家不存在或者不在线
        if(kicked==null||!kicked.getPlayer().isOnline()){
            kicked.sendMessage(Language.leaderCMDKickPLayerKickedMessag8);
            return;
        }
        //踢出的人是你自己
        if(teamer.getName().equals(playerName)){
            teamer.sendMessage(Language.leaderCMDKickPLayerKickedMessag7);
            return;
        }
        //被踢玩家没有队伍
        if(!kicked.hasTeam()){
            teamer.sendMessage(Language.leaderCMDKickPlayerMessageError3);
            return;
        }
        //你没有队伍
        if(!teamer.hasTeam()){
            teamer.sendMessage(Language.leaderCMDKickPlayerMessageError);
            return;
        }
        //你不是队长
        if(!teamer.isLeader()){
            teamer.sendMessage(Language.leaderCMDKickPlayerMessageError2);
            return;
        }
        //被踢玩家没有在你的队伍中
        if(!teamer.getTeam().equals(kicked.getTeam())){
            teamer.sendMessage(Language.leaderCMDKickPlayerMessageError4);
            return;
        }
        //玩家中移除队伍
        kicked.leaveTeam();
        kicked.sendMessage(Language.leaderCMDKickPLayerKickedMessage);
        teamer.sendMessage(Language.leaderCMDKickPLayerKickedMessag9.replace("<player>",kicked.getName()));
    }

    //改变队长
    public void changeLeader(CommandSender sender,String playerName){
        Teamer oldLeader=TeamManager.getInstance().getPlayerGamaer().get(sender);
        //你没有队伍
        if(!oldLeader.hasTeam()){
            oldLeader.sendMessage(Language.leaderCMDChangeLeaderMessageError1);
            return;
        }
        //玩家不存在
        if( Bukkit.getPlayer(playerName)==null){
            oldLeader.sendMessage(Language.leaderCMDChangeLeaderMessageError5);
            return;
        }
        Player player = Bukkit.getPlayer(playerName);
        if(!player.isOnline()){
            oldLeader.sendMessage(Language.leaderCMDChangeLeaderMessageError5);
            return;
        }
        Teamer newLeader=TeamManager.getInstance().getPlayerGamaer().get(player);
        //你不是队长
        if(!oldLeader.isLeader()){
            oldLeader.sendMessage(Language.leaderCMDChangeLeaderMessageError2);
            return;
        }
        //不能任命自己
        if(oldLeader.getName().equals(newLeader.getName())){
            oldLeader.sendMessage(Language.leaderCMDChangeLeaderMessageError3);
            return;
        }
        //你任命的玩家不在队伍里，或者没有队伍
        if(!newLeader.hasTeam()
                ||!newLeader.getTeam().getTeamName().equals(oldLeader.getTeam().getTeamName())){
            oldLeader.sendMessage(Language.leaderCMDChangeLeaderMessageError4);
            return;
        }
        oldLeader.getTeam().changeLeader(playerName);
        oldLeader.sendMessage("你将队长给与了"+newLeader.getName());
        newLeader.sendMessage("你被"+oldLeader.getTeam()+"任命为了队长");
    }

    //申请加入队伍
    public void sendApplyToTeamer(CommandSender sender,String teamName){
        //如果你已经有队伍
        Teamer applyer=TeamManager.getInstance().getPlayerGamaer().get(sender);
        if(applyer.hasTeam()){
            applyer.sendMessage(Language.leaderCMDsendApplyToTeamer2);
            return;
        }
        //如果队伍不存在
        if(TeamManager.getInstance().getTeam(teamName)==null){
            sender.sendMessage(Language.leaderCMDsendApplyToTeamer1);
            return;
        }
        Team team=TeamManager.getInstance().getTeam(teamName);
        //如果你已经申请了
        if(team.isInApplyList(applyer)){
            sender.sendMessage(Language.leaderCMDsendApplyToTeamer3);
            return;
        }
        TextComponent a1 = new TextComponent(("&7[&a同意&7]   &m&l|&b同意"+sender.getName()+"&7进入你的队伍").replace("&", "§"));
        a1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team agreeapply "+sender.getName()));
        a1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("同意申请!").color(ChatColor.BLUE).create()));
        TextComponent a2 = new TextComponent(("&7[&a拒绝&7]    &m&l|&7拒绝!"+sender.getName()+"&7进入你的队伍").replace("&", "§"));
        a2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team disagreeapply "+sender.getName()));
        a2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("拒绝申请!").color(ChatColor.BLUE).create()));
        team.getLeader().getPlayer().sendMessage(("&6&m&l|&a&m&l---&8&m&l---------&7&l[&b"+sender.getName()+"的入队申请&7&l]&8&m&l----------&a&m&l---&6&m&l|").replace("&", "§"));
        team.getLeader().getPlayer().spigot().sendMessage(a1);
        team.getLeader().getPlayer().spigot().sendMessage(a2);
        team.getLeader().getPlayer().sendMessage(("&6&m&l|&a&m&l--------------------------------&6&m&l|").replace("&", "§"));
        team.addTeamerToApplyList(applyer);
        sender.sendMessage("你申请了队伍"+teamName+"等待队长审批");
        //移除队列
        new BukkitRunnable(){
            @Override
            public void run() {
                if(!team.isInApplyList(applyer)) {
                    cancel();
                }else{
                    team.removeTeamer(applyer);
                    cancel();
                }
            }
        }.runTaskLater(plugin,200);
    }



}
