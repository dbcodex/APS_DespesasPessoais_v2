package com.aps.despesaspessoais;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface DespesasDAO {

    @Query("SELECT * FROM Despesas")
    List<Despesas> getAll();

    @Query("SELECT * FROM Despesas WHERE chaveFirebase=:chaveFirebase")
    List<Despesas> getByChaveFirebase (String chaveFirebase);
//
//    @Query("SELECT * FROM Cadastro WHERE nome LIKE :first LIMIT 1")
//    Cadastro findByName(String first, String last);

    @Insert
    void insert(Despesas... despesas);

    @Delete
    void delete(Despesas despesas);
}