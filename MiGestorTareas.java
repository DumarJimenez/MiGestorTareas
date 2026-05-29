import java.util.ArrayList;
import java.util.Scanner;


// Programa gestor de tareas utilizando funciones, modularización y recursividad. //

public class MiGestorTareas {
    
    //  Clase interna para representar una tarea. //
     
    static class Tarea {
        String descripcion;
        boolean completada;

        /**
         * Constructor de la tarea.
         *
         * @param descripcion Descripción de la tarea.
         * @param completada Estado de la tarea.
         */
        public Tarea(String descripcion, boolean completada) {
            this.descripcion = descripcion;
            this.completada = completada;
        }
    }

    /**
     * Inicializa y retorna una lista de tareas de ejemplo.
     *
     * @return Lista de tareas iniciales.
     */
    public static ArrayList<Tarea> obtenerTareas() {

        ArrayList<Tarea> listaTareas = new ArrayList<>();

        listaTareas.add(new Tarea("Estudiar Java", false));
        listaTareas.add(new Tarea("Realizar actividad del curso", false));
        listaTareas.add(new Tarea("Leer documentación", true));

        return listaTareas;
    }

    /**
     * Muestra todas las tareas numeradas.
     *
     * @param listaTareas Lista de tareas.
     */
    public static void mostrarTareas(ArrayList<Tarea> listaTareas) {

        System.out.println("\n****** LISTA DE TAREAS ******");

        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        for (int i = 0; i < listaTareas.size(); i++) {

            Tarea tarea = listaTareas.get(i);

            String estado = tarea.completada ? "[X]" : "[ ]";

            System.out.println((i + 1) + ". " + estado + " " + tarea.descripcion);
        }
    }

    /**
     * Agrega una nueva tarea a la lista.
     *
     * @param listaTareas Lista de tareas.
     * @param descripcion Descripción de la tarea.
     * @return Lista actualizada.
     */
    public static ArrayList<Tarea> agregarTarea(ArrayList<Tarea> listaTareas,String descripcion){

        listaTareas.add(new Tarea(descripcion, false));

        return listaTareas;
    }

    /**
     * Marca una tarea como completada.
     *
     * @param listaTareas Lista de tareas.
     * @param posicion Posición de la tarea.
     */
    public static void marcarCompletada(ArrayList<Tarea> listaTareas,int posicion) {

        try {

            listaTareas.get(posicion - 1).completada = true;

            System.out.println("Tarea marcada como completada.");

        } catch (IndexOutOfBoundsException e) {

            System.out.println("Error: La posición ingresada no existe.");
        }
    }

    /**
     * Cuenta recursivamente las tareas pendientes.
     *
     * @param listaTareas Lista de tareas.
     * @param indice Índice actual.
     * @return Número total de tareas pendientes.
     */
    public static int contarTareasPendientes(ArrayList<Tarea> listaTareas,int indice) {

        // Caso base:
        // Si el índice es igual al tamaño de la lista,
        // significa que ya se revisaron todas las tareas.
        if (indice == listaTareas.size()) {
            return 0;
        }

        // Caso recursivo:
        // Verifica si la tarea actual está pendiente.
        if (!listaTareas.get(indice).completada) {

            return 1 + contarTareasPendientes(listaTareas,indice + 1);
        }

        // Continúa evaluando la siguiente tarea.
        return contarTareasPendientes(listaTareas,indice + 1);
    }

    /**
     * Método principal del programa.
     *
     * @param args Argumentos de ejecución.
     */
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        ArrayList<Tarea> listaTareas = obtenerTareas();

        int opcion;

        do {

            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Ver tareas");
            System.out.println("2. Agregar tarea");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Mostrar total de tareas pendientes");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();

            entrada.nextLine();

            switch (opcion) {

                case 1:

                    mostrarTareas(listaTareas);
                    break;

                case 2:

                    System.out.print("Ingrese la descripción de la tarea: ");

                    String descripcion = entrada.nextLine();

                    agregarTarea(listaTareas, descripcion);

                    System.out.println("Tarea agregada correctamente.");

                    break;

                case 3:

                    mostrarTareas(listaTareas);

                    System.out.print(
                            "Ingrese el número de la tarea completada: ");

                    int posicion = entrada.nextInt();

                    marcarCompletada(listaTareas, posicion);

                    break;

                case 4:

                    int pendientes = contarTareasPendientes(
                            listaTareas,
                            0);

                    System.out.println(
                            "Total de tareas pendientes: "
                                    + pendientes);

                    break;

                case 5:

                    System.out.println("Saliendo del programa...");
                    break;

                default:

                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        entrada.close();
    }
}

