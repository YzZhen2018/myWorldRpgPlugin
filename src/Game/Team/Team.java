package Game.Team;


import Game.Team.Manager.TeamManager;
import Game.Team.Players.Teamer;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Team {
    private List<Teamer> teamers=new ArrayList<>();
    //队长
    private Teamer leader;
    //队伍名字
    private String teamName;
    //申请队列
    private List<Teamer> applyList=new ArrayList<>();

    public Team(Teamer leader,String name){
        this.teamName=name;
        this.leader=leader;
        leader.addTeam(this);
        leader.setLeader(true);
    }

    public boolean isInApplyList(Teamer teamer){
        for (Teamer teamer1:applyList){
            if(teamer1.equals(teamer))return true;
        }
        return false;
    }

    public void removeAllApplyList(){
        applyList.clear();
    }

    public void sendMessageToAll(String msg){
        for (Teamer teamer:teamers) {
            teamer.sendMessage(msg);
        }
        leader.sendMessage(msg);
    }

    public void disMissTeam(){
        applyList.clear();
        leader.leaveTeam();
        TeamManager.getInstance().removeTeam(this);
        List<String> names=new ArrayList<>();
        for (Teamer teamer:teamers) {
            names.add(teamer.getName());
            teamer.sendMessage("队长退出了游戏，并解散了队伍");
        }
        for (String name: names) {
            Teamer teamer=TeamManager.getInstance().getPlayerGamaer().get(Bukkit.getPlayer(name));
            teamer.leaveTeam();
        }
    }

    public void clearLeader(){
        leader=null;
    }

    public void removeTeamerFromApplyList(Teamer teamer){
        Iterator iterator=applyList.iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals(teamer)){
                iterator.remove();
                return;
            }
        }
    }

    public void addTeamerToApplyList(Teamer teamer){
        for (Teamer teamer1:applyList){
            if(teamer1.equals(teamer))return;
        }
        applyList.add(teamer);
    }


    public Teamer getLeader() {
        return leader;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Teamer> getTeamers() {
        return teamers;
    }

    public void addTeamer(String playerName){
        Teamer joiner=TeamManager.getInstance().getPlayerGamaer().get(Bukkit.getPlayer(playerName));
        teamers.add(joiner);
        joiner.addTeam(this);
    }

    public boolean changeLeader(String playerName){
        Teamer newLeader=null;
        Iterator iterator=teamers.iterator();
        while (iterator.hasNext()){
            Teamer teamer= (Teamer) iterator.next();
            if(!teamer.getName().equals(playerName))continue;
            newLeader=teamer;
            iterator.remove();
        }
        if(newLeader==null)return false;
        //
        leader.setLeader(false);
        teamers.add(leader);
        //
        leader=  newLeader;
        leader.setLeader(true);
        return true;
    }

    public void removeTeamer(Teamer teamer){
        teamers.remove(teamer);
    }

    @Override
    public String toString() {
        return leader.getName()+teamName;
    }

    public boolean equals(Team obj) {
        if(toString().equals(obj.toString())){
            return true;
        }
        return false;
    }
}
