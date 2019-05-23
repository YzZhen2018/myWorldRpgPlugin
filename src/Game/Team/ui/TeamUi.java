package Game.Team.ui;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.VexInventoryGui;
import lk.vexview.gui.components.*;
import lk.vexview.newinv.VexPage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamUi {
    public static void openCreateTeamGui(Player p) {
//        VexGui vg = new VexGui("[local]gui.png", -1, -1, 300, 200, 200, 80);

//        VexTextField vexTextField=new VexTextField(-1, 25, 140, 16, 64, -999);
//        vg.addComponent(vexTextField);
//        VexScrollingList vexScrollingList=new VexScrollingList(-1, -1, 190,70,500);
//        vg.addComponent(vexScrollingList);
//        vexScrollingList.addComponent(new VexText(-1, 0, Arrays.asList("商店")));
//        vexScrollingList.addComponent(new VexButton(-1005, "商品1", "[local]button.png", "[local]button_.png", 0, 10, 200, 20, (pp) -> {
////            System.out.println(vexTextField.getTypedText());
//            pp.closeInventory();
//        }));
//        vexScrollingList.addComponent(new VexButton(-1001, "商品2", "[local]button.png", "[local]button_.png", 0, 30, 200, 20, (pp) -> {
////            System.out.println(vexTextField.getTypedText());
//            pp.closeInventory();
//        }));
//        vexScrollingList.addComponent(new VexButton(-1003, "商品3", "[local]button.png", "[local]button_.png", 0, 50, 200, 20, (pp) -> {
////            System.out.println(vexTextField.getTypedText());
//            pp.closeInventory();
//        }));

//        VexViewAPI.openGui(p,new VexInventoryGui("[local]gui.png",-1,-1,100,100,200,200,3,110));
//        VexViewAPI.openGui(p,vg);
//        VexViewAPI.sendFlowView(p,"xxx",30);
//        VexViewAPI.sendFlowView(p,"xxx",20);
//        VexViewAPI.sendFlowView(p,"xxx",10);
        List<VexText> texts=new ArrayList<>();
        texts.add(new VexText(1, 100, Arrays.asList("商店")));
        texts.add(new VexText(1, 120, Arrays.asList("商店")));
        List<VexImage> images=new ArrayList<>();
        images.add(new VexImage("[local]point.png",10, 10,20, 20,10, 10,new VexHoverText(Arrays.asList("§c点击立即解散队伍"), 30, 50, 68, 16)));
        List<VexButton> buttons=new ArrayList<>();
        buttons.add(new VexButton(-1003, "商品1", "[local]button.png", "[local]button_.png", 0, 50, 10, 20));
        buttons.add(new VexButton(-102, "商品2", "[local]button.png", "[local]button_.png", 0, 50, 10, 20));
        VexViewAPI.addSideBarPage(new VexPage(texts,images,buttons));



//        p.openInventory(p.getInventory());
    }
}
