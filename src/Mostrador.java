/*  PROYECTO FINAL DE PROGRAMACION CONCURRENTE
    FECHA: 22/11/2020
    EQUIPO: CASTILLO AVILA MARTI GALDINO, GARCIA CRUZ RICARDO EMMANUEL, MERLO SIMONI MARIA FERNANDA, SESEÑA ROBLES DIANA SARAHI, VARGAS ARENAS PEDRO
*/
public class Mostrador{

    private Heladeria heladeria;
    
    
    public Mostrador(){
        
    }
        
    //Revisar
    
    private void verificarProductos(String helado) throws InterruptedException{
        
        if(!heladeria.verificarBarquillos()){
                System.out.println("Rellenando barquillos");
                // heladeria.bloquearCaja();
                Thread.sleep(2000);
                heladeria.llenarBarquillos();
        }
        if(!heladeria.verificarServilletas()){
                System.out.println("Rellenando servilletas");
                // heladeria.bloquearCaja();
                Thread.sleep(2000);
                heladeria.llenarServilletas();
        }
        if(!heladeria.verificarSabor(helado)){
                System.out.println("Rellenando sabor " + helado);
                // heladeria.bloquearCaja(); //Es opcional, se debe ver que proceso se bloquea
                Thread.sleep(2000);
                heladeria.llenarSabor(helado);
        }
        
    }
    
    //Implementar semáforos
    
    
    public void pedirHelado(int nombre, String sabor, int grupo){
        try {
            System.out.print("****Region critica: ");
            System.out.println("El cliente " + nombre + " del grupo "+grupo+ " pidió el helado " + sabor);
            verificarProductos(sabor);
            heladeria.restarHelado(sabor);
            Thread.sleep(2000);// El cliente se tomara su tiempo para probarse la ropa.
            System.out.print("****Region critica: ");
            System.out.println("El cliente " + nombre + " del grupo "+grupo+ " recibió su helado " + sabor);
        } catch (InterruptedException E) {
            System.out.println("Se genero una excepcion pidiendo helado");
        }
    }
    public void pedirGrupo(int nombre){
        System.out.println("El grupo "+nombre+ " se comienza a atender");
    }

    public String [] getSabores(){
        return heladeria.getSabores();
    }
    public void setHeladeria(Heladeria heladeria){
        this.heladeria = heladeria;
    }
}
