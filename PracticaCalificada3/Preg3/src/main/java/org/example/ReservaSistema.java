package org.example;

import java.util.ArrayList;

public class ReservaSistema {
    ArrayList<int[]> reservas = new ArrayList<int[]>();
    public ArrayList<int[]> reservar(int[] horas){
        if (!reservas.contains(horas)){
            reservas.add(horas);
        }

        
        return reservas;
    }
}
