package Game.Team.Players;

import Game.Team.Team;
import org.bukkit.entity.Player;


public class Teamer {

    private Player player =null;
    private Team team=null;
    private boolean isLeader=false;

    public Teamer(Player player){
        this.player=player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void addTeam(Team team){
        this.team=team;
    }

    public boolean isLeader(){
        return isLeader;
    }

    public void setLeader(boolean leader){
        this.isLeader=leader;
    }

    //获取玩家名字
    public String getName(){
        return player.getName();
    }


    //是否有队伍
    public boolean hasTeam(){
        if(team==null)return false;
        return true;
    }

    //获取队伍实例
    public Team getTeam(){
        if(team!=null)return team;
        return null;
    }

    public void sendMessage(String message){
        player.sendMessage(message);
    }

    //离开队伍
    public void leaveTeam(){
        if(isLeader()){
            team.clearLeader();
        }else {
            team.removeTeamer(this);
        }
        team=null;
    }

}
