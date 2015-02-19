package com.mentormate.academy.challenger.utils;
import com.mentormate.academy.challenger.models.DrawerItem;

import java.util.ArrayList;
import java.util.List;


public class DrawerItemManager {
    public static List<DrawerItem> generateDrawerItemsList(){
        List<DrawerItem> drawerItemsList=new ArrayList<DrawerItem>();

        DrawerItem drawerItem1=new DrawerItem();
        drawerItem1.setName(Constants.DRAWER_MENU_ITEM_PROFILE);
        drawerItem1.setIcon("dashboard_user");
        drawerItemsList.add(drawerItem1);

        DrawerItem drawerItem2=new DrawerItem();
        drawerItem2.setName(Constants.DRAWER_MENU_ITEM_CHALLENGES);
        drawerItem2.setIcon("dashboard_star");
        drawerItemsList.add(drawerItem2);

        DrawerItem drawerItem3=new DrawerItem();
        drawerItem3.setName(Constants.DRAWER_MENU_ITEM_ACHIEVEMENTS);
        drawerItem3.setIcon("dashboard_achievements");
        drawerItemsList.add(drawerItem3);

        DrawerItem drawerItem4=new DrawerItem();
        drawerItem4.setName(Constants.DRAWER_MENU_ITEM_RANKING);
        drawerItem4.setIcon("dashboard_ranking");
        drawerItemsList.add(drawerItem4);

        DrawerItem drawerItem5=new DrawerItem();
        drawerItem5.setName(Constants.DRAWER_MENU_ITEM_REVIEW);
        drawerItem5.setIcon("dashboard_review");
        drawerItemsList.add(drawerItem5);

        DrawerItem drawerItem6=new DrawerItem();
        drawerItem6.setName(Constants.DRAWER_MENU_ITEM_LOGOUT);
        drawerItem6.setIcon("dashboard_logout");
        drawerItemsList.add(drawerItem6);

        return drawerItemsList;
    }
}
