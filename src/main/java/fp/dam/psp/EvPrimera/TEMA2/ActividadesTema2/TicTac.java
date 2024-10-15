package fp.dam.psp.EvPrimera.TEMA2.ActividadesTema2;

//Movida con los ::

// Usando referencias a metodos usando meros lo del "tic::Tarea" es una referencia a estancia.

	
public class TicTac {
    private String sonido;
    
    
    public TicTac(String sonido) {
        this.sonido = sonido;
        
    }
    
    void Tarea() {
        while (true) {
            System.out.println(sonido + " ");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    
    public static void main(String[] args) {
        TicTac tic = new TicTac("TIC");
        TicTac tac = new TicTac("TAC");
        new Thread(tic::Tarea).start();
        new Thread(tac::Tarea).start();
        
        //Modia con "::"
        
    }
    
    //END PROGRAM
    }
    