package com.aps.despesaspessoais;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.type.Money;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

@Entity
public class Despesas {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "chaveFirebase")
    public String chaveFirebase;

    @ColumnInfo(name = "descricaoDespesa")
    public String descricaoDespesa;

    @ColumnInfo(name = "valorDespesa")
    public String valorDespesa;

    @ColumnInfo(name = "dtVencimento")
    public String dtVencimento;

}
