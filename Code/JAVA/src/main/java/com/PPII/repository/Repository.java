package com.PPII.repository;

import com.PPII.GUI.DatabaseGui;
import com.PPII.database.CreateDataBase;
import com.PPII.util.SqlConnect;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Repository<E> {
    private ArrayList<E> list;
    SqlConnect db;

    public Repository() {
        this.list = new ArrayList<>();

    }

    public Repository(E... entities) {
        this.list = new ArrayList<>();
        this.list.addAll(Arrays.asList(entities));
    }

    public ArrayList<E> getList() {
        return list;
    }

    public void addEntity(E entity) {
        this.list.add(entity);
    }

    public void addEntities(E... entities) {
        this.list.addAll(Arrays.asList(entities));
    }
    public void displayEntities(){
        for (E entity:this.list){
            DatabaseGui.println(entity.toString());
        }
    }
}
