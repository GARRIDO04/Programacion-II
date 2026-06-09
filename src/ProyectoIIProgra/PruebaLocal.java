package ProyectoIIProgra;

public class PruebaLocal {
    public static void PruebaLocal(String[] args) {
        ComponenteDAO dao = new ComponenteDAO();

        dao.listarComponentes();

        System.out.println("\nRegistrando uso de 5 componentes (ID 2) para el Proyecto 1...");
        dao.registrarUso(1, 2, 5);

        System.out.println("\nInventario despues del uso:");
        dao.listarComponentes();

        //FORZAMOS EL ERROR (Intentamos usar más de las que hay)
        System.out.println("\nIntentando usar 100 componentes (ID 2)...");
        dao.registrarUso(1, 2, 100); 
    }
}
