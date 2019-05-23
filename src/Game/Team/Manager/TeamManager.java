package Game.Team.Manager;


import Game.Team.Players.Teamer;
import Game.Team.Team;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamManager {

    private static TeamManager manager;
    private List<Team> teams=new ArrayList<>();
    private Map<Player,Teamer> playerGamer=new HashMap<>();

    public static TeamManager getInstance(){
        if(manager==null){
            manager=new TeamManager();
        }
        return manager;
    }

    public void createTeam(Player player,String name){
        Teamer leader= playerGamer.get(player);
        Team team=new Team(leader,name);
        teams.add(team);
    }

    public Team getTeam(String teamName){
        for (Team team:
             teams) {
            if(team.getTeamName().equals(teamName)){
                return team;
            }
        }
        return null;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void removeTeam(Team team){
        teams.remove(team);
    }

    public Map<Player, Teamer> getPlayerGamaer() {
        return playerGamer;
    }

    public void setPlayerGamaer(Player playerGamaer) {
        playerGamer.put(playerGamaer,new Teamer(playerGamaer));
    }

    public void clearTeamer(Player player){
        playerGamer.remove(player);
    }


}
