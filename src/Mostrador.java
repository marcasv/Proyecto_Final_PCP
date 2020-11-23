/*  PROYECTO FINAL DE PROGRAMACION CONCURRENTE
    FECHA: 22/11/2020
    EQUIPO: CASTILLO AVILA MARTI GALDINO, GARCIA CRUZ RICARDO EMMANUEL, MERLO SIMONI MARIA FERNANDA, SESEÑA ROBLES DIANA SARAHI, VARGAS ARENAS PEDRO
*/
public class Mostrador{

    private Heladeria heladeria;
    
    
    public Mostrador(){}
    
    //Este método verfica si hay helado, servilletas y barquillos para continuar
    //atendiendo, en caso de que no haya, duerme la hilo y rellena lo faltate
    private void verificarProductos(String helado) throws InterruptedException{
        
        //En el método verificarBarquillo se resta el barquillo vendido
        if(!heladeria.verificarBarquillos()){
                System.out.println("Rellenando barquillos");
                // heladeria.bloquearCaja();
                Thread.sleep(2000);
                heladeria.llenarBarquillos();
        }
        
        //En el método verificarServilletas se resta la servilleta vendida
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
    
    //Con este método se atienden a las personas
    public void pedirHelado(int nombre, String sabor, int grupo){
        
        try {
            System.out.print("****Region critica: ");
            System.out.println("El cliente " + nombre + " del grupo "+grupo+ " pidió el helado " + sabor);
            //Antes de atender, verifica el sabor y utensilios
            verificarProductos(sabor);
            //Resta al stock del helado
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
