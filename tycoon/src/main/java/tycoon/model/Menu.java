package tycoon.model;

import java.util.ArrayList;
import java.util.Dictionary;

import tycoon.model.items.Bank;
import tycoon.model.items.ChickenCoop;
import tycoon.model.items.Factory;
import tycoon.model.items.FarmHouse;
import tycoon.model.items.FishHut;
import tycoon.model.items.Item;
import tycoon.model.items.MiddleClassHouse;
import tycoon.model.items.PeasantHouse;
import tycoon.model.items.Shop;
import tycoon.model.items.WealthyHouse;

public class Menu {
    ArrayList<Item> items;
    Dictionary<Item, Boolean> access;
    Dictionary<Item, Integer> price;
    int money;
    public Menu(){
        money = 100;
        items.add(new ChickenCoop(0, 0, 1));
        items.add(new FishHut(0, 0, 2));
        items.add(new PeasantHouse(0, 0, 3));
        items.add(new FarmHouse(0, 0, 4));
        items.add(new MiddleClassHouse(0, 0, 5));
        items.add(new Shop(0, 0, 6));
        items.add(new Factory(0, 0, 7));
        items.add(new Bank(0, 0, 8));
        items.add(new WealthyHouse(0, 0, 9));
        for (int i = 0; i < items.size(); i++){
            access.put(items.get(i), false);
            price.put(items.get(i), items.get(i).getLevel()*100);
        }
    }
    public void updateAccess(int level){

    }
    public ArrayList<Item> getitems(){
        return items;
    }
    public Dictionary<Item, Boolean> getAccess(){
        return access;
    }
    public Dictionary<Item, Integer> getPrice(){
        return price;
    }


}
