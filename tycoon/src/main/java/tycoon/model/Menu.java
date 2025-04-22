package tycoon.model;

import java.util.ArrayList;
import java.util.Dictionary;

import tycoon.model.objects.Bank;
import tycoon.model.objects.ChickenCoop;
import tycoon.model.objects.Factory;
import tycoon.model.objects.FarmHouse;
import tycoon.model.objects.FishHut;
import tycoon.model.objects.MiddleClassHouse;
import tycoon.model.objects.Objects;
import tycoon.model.objects.PeasantHouse;
import tycoon.model.objects.Shop;
import tycoon.model.objects.WealthyHouse;

public class Menu {
    ArrayList<Objects> objects;
    Dictionary<Objects, Boolean> access;
    Dictionary<Objects, Integer> price;
    public Menu(){
        objects.add(new ChickenCoop(0, 0, 0));
        objects.add(new FishHut(0, 0, 2));
        objects.add(new PeasantHouse(0, 0, 3));
        objects.add(new FarmHouse(0, 0, 4));
        objects.add(new MiddleClassHouse(0, 0, 5));
        objects.add(new Shop(0, 0, 6));
        objects.add(new Factory(0, 0, 7));
        objects.add(new Bank(0, 0, 8));
        objects.add(new WealthyHouse(0, 0, 9));
    }


}
