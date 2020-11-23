/*  PROYECTO FINAL DE PROGRAMACION CONCURRENTE
    FECHA: 22/11/2020
    EQUIPO: CASTILLO AVILA MARTI GALDINO, GARCIA CRUZ RICARDO EMMANUEL, MERLO SIMONI MARIA FERNANDA, SESEÑA ROBLES DIANA SARAHI, VARGAS ARENAS PEDRO
*/

public class Semaforo {
     private int counter;
    public Semaforo() {
        this(0);
    }
    public Semaforo(int i) {
        if (i < 0) throw new IllegalArgumentException(i + " < 0");
        counter = i;
    }
    
    /**
     * Incrementa el contador interno, posiblemente
     * despierte a un hilo que se mantuvo esperando por
     *  acquire().
    */
    public synchronized void release() {    //SALIDA DE LA REGION CRITICA
        if (counter == 0) {
            this.notify();
        }
        counter++;
    }
    /**
     * Decrementa el contador interno, 
     * bloqueandolo si el contador es cero
     * 
     InterruptedException pasa por this.wait().
    */
    public synchronized void acquire() throws InterruptedException {    //ENTRADA A LA REGION CRITICA
        while (counter == 0) {
            this.wait();
        }
        counter--;
    }
}
