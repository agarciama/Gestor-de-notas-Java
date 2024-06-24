/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.upsa.gestornotasapp;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    //Utilizamos un Hashset para almacenar los usuarios para asegurarnos que no hay usuarios repetidos
    private static Set<UsuarioNormal> usuarios = new HashSet<>();
    private static Gestor gestor = new Gestor();

    //Creamos al administrador y a un usuario de prueba
    private static UsuarioAdministrador admin = new UsuarioAdministrador("admin", "1234", gestor);
    private static UsuarioNormal usuarioPrueba = new UsuarioNormal("alvaro", "123", gestor);

    public static void main(String[] args) {

        //Añadimos el usuario de prueba a la lsita de usuarios
        usuarios.add(usuarioPrueba);

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("1 - Registrarse");
            System.out.println("2 - Iniciar sesión");
            System.out.println("3 - Salir\n");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarUsuario(scanner);
                    break;
                case 2:
                    String user = iniciarSesion(scanner);
                    if (user != null) {
                        if (user.equals("admin")) {
                            // Menú para el administrador
                            boolean continuarAdmin = true;
                            while (continuarAdmin) {

                                admin.mostrarMenu();

                                System.out.println("1 - Agregar nota");
                                System.out.println("2 - Editar nota");
                                System.out.println("3 - Eliminar nota");
                                System.out.println("4 - Visualizar notas");
                                System.out.println("5 - Cerrar sesión");
                                int adminOpcion = scanner.nextInt();
                                scanner.nextLine(); // Consumir la nueva línea

                                switch (adminOpcion) {
                                    case 1:
                                        admin.visualizarNotas();
                                        break;
                                    case 2:
                                        admin.agregarNota();
                                        break;
                                    case 3:
                                        // Lógica para editar nota
                                        System.out.println("Función editar nota no implementada.");
                                        break;
                                    case 4:
                                        // Lógica para eliminar nota
                                        System.out.println("Función eliminar nota no implementada.");
                                        break;
                                    case 5:
                                        continuarAdmin = false;
                                        break;
                                    default:
                                        System.out.println("Opción no válida.");
                                }
                            }
                        } else {
                            // Menú para el usuario normal
                            UsuarioNormal usuarioLogueado = buscarUsuarioPorNombre(user);
                            if (usuarioLogueado != null) {
                                boolean continuarUsuario = true;
                                while (continuarUsuario) {
                                    usuarioLogueado.mostrarMenu();
                                    int userOpcion = scanner.nextInt();
                                    scanner.nextLine(); // Consumir la nueva línea

                                    switch (userOpcion) {
                                        case 1:
                                            usuarioLogueado.visualizarNotas();
                                            break;
                                        case 2:
                                            usuarioLogueado.agregarNota();
                                            break;
                                        case 3:
                                            // Lógica para editar nota
                                            System.out.println("Función editar nota no implementada.");
                                            break;
                                        case 4:
                                            // Lógica para eliminar nota
                                            System.out.println("Función eliminar nota no implementada.");
                                            break;
                                        case 5:
                                            continuarUsuario = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                    }
                                }
                            }
                        }
                    }
                    ;
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();

    }

    //Funciones para gestionar los usuarios
    private static void registrarUsuario(Scanner scanner) {
        System.out.println("Ingrese su nombre de usuario:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        UsuarioNormal nuevoUsuario = new UsuarioNormal(nombre, password, gestor);
        if (usuarios.add(nuevoUsuario)) {
            System.out.println("Usuario registrado con éxito.");
        } else {
            System.out.println("El nombre de usuario ya existe. Intente con otro nombre.");
        }
    }

    private static String iniciarSesion(Scanner scanner) {
        System.out.println("Ingrese su nombre de usuario:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        if (admin.getNombre().equals(nombre) && admin.validarPassword(password)) {
            System.out.println("Inicio de sesión como Administrador exitoso.");
            return "admin";
        } else {
            UsuarioNormal usuario = buscarUsuario(nombre, password);
            if (usuario != null) {
                System.out.println("Inicio de sesión como Usuario exitoso.");
                return usuario.getNombre();
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos.");
                return null;
            }
        }
    }

    private static UsuarioNormal buscarUsuario(String nombre, String password) {
        for (UsuarioNormal usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.validarPassword(password)) {
                return usuario;
            }
        }
        return null;
    }

    private static UsuarioNormal buscarUsuarioPorNombre(String nombre) {
        for (UsuarioNormal usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        return null;
    }

}
