package com.aps.despesaspessoais;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Despesas.class}, version = 1)
public abstract class DespesasDB extends RoomDatabase {
    public abstract DespesasDAO despesasDAO();
}