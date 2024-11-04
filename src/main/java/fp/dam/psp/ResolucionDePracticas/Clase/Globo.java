package fp.dam.psp.ResolucionDePracticas.Clase;

import static fp.dam.psp.ResolucionDePracticas.Clase.Main.Consola.*;
import static fp.dam.psp.ResolucionDePracticas.Clase.EstadoGlobo.*;

public class Globo {

    String nombre;
    private EstadoGlobo es = DESHINCHADO;
    int VL_MAX = 5;
    int vl = 0;
//    private cmd cmd;
    Consola consola;

    //* Creacion del constructor
    public Globo(int id, int VL_MAX, Consola consola){
        this.nombre = "GL" + id;
        this.VL_MAX = VL_MAX;
        this.consola = consola;
    }


    //! En estos dos metodos tendriamos conducion de carrera. se arregla poniendo synchronized y a chuparla
    public synchronized void hinchar(){
        vl++;
        if (vl > VL_MAX){
            es = EXPLOTADO;
            consola.print(nombre + " Explotado");
        }
    }

    public synchronized  void pinchar(){
        vl = 0;
        es = PINCHADO;
    }
}
