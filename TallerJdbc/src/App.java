import CRUD.EstudianteCRUD;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        EstudianteCRUD dao = new EstudianteCRUD();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("MENU ");
            System.out.println("1. Insertar Estudiante");
            System.out.println("2. Actualizar Estudiante");
            System.out.println("3. Eliminar Estudiante");
            System.out.println("4. Consultar todos los Estudiantes");
            System.out.println("5. Consultar Estudiante por correo");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt(); sc.nextLine();
                    System.out.print("Estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
                    String estadoCivil = sc.nextLine();
                    dao.insertar(nombre, apellido, correo, edad, estadoCivil);
                }
                case 2 -> {
                    System.out.print("Correo del estudiante a actualizar: ");
                    String correo = sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nuevo apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Nueva edad: ");
                    int edad = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo estado civil: ");
                    String estadoCivil = sc.nextLine();
                    dao.actualizar(correo, nombre, apellido, edad, estadoCivil);
                }
                case 3 -> {
                    System.out.print("Correo del estudiante a eliminar: ");
                    String correo = sc.nextLine();
                    dao.eliminar(correo);
                }
                case 4 -> dao.consultarTodos();
                case 5 -> {
                    System.out.print("Correo a buscar: ");
                    String correo = sc.nextLine();
                    dao.consultarPorCorreo(correo);
                }
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 6);

        sc.close();
    }
}