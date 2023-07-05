import java.sql.*;
import java.util.ArrayList;

public class FacturaDAO {
    private ArrayList<Factura> facturas = new ArrayList<Factura>();
    //Esta funcion inserta una factura en la base de datos
    public void guardar(Factura factura){
        //Statement stmt;
        //stmt.executeQuery(INSERT)
        facturas.add(factura);
    }

    //Esta funcion realiza un SELECT de toda la tabla de facturas
    public void todo(){

    }

    //Esta función realiza un SELECT con un predicado(condición)
    public void todoConAlMenos(){

    }
}
