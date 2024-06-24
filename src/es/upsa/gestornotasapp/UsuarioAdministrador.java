/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.upsa.gestornotasapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author administrador
 */
public class UsuarioAdministrador extends Usuario implements InterfazGestionarNota {

    private Gestor gestor;

    //Constructor, accedemos a la clase de la que heredamos con la palabra super para llamar a su constrcutor
    public UsuarioAdministrador(String nombre, String password, Gestor gestor) {
        super(nombre, password);
        this.gestor = gestor;
    }

    //Metodo extendido de la clase abstracta Usuario
    @Override
    public void mostrarMenu() {
        System.out.println("\nOpciones de Administración:");
        System.out.println("1 - Ver listado de notas");
        System.out.println("2 -  Agregar Nota (como administrador)");
        System.out.println("3 - Editar Nota");
        System.out.println("4 - Eliminar Nota");
        System.out.println("5 - Salir\n");
    }

    //Metodos abstractos de la InterfazGestionarNotas
    @Override
    public void visualizarNotas() {
        List<Categoria> categorias = gestor.getCategorias();

        // Comprobamos si todavía no se ha introducido ninguna nota
        if (categorias.isEmpty()) {
            System.out.println("\nTodavía no se ha agregado ninguna nota\n");
            return;
        }

        // Definir el formateador para las fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\nNotas del administrador:");
        for (Categoria categoria : categorias) {
            for (Nota nota : categoria.getNotas()) {
                System.out.println("Título: " + nota.getTitulo());
                System.out.println("Categoría: " + categoria.getNombre());
                System.out.println("Contenido: " + nota.getContenido());
                System.out.println("Fecha creación: " + nota.getFechaCreacion().format(formatter));
                System.out.println("Fecha modificación: " + nota.getFechaUltimaModificacion().format(formatter));
                System.out.println("Usuario: " + nota.getUsuario().getNombre());
                System.out.println();
            }
        }
    }

    @Override
    public void agregarNota() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el título de la nota:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese el contenido de la nota:");
        String contenido = scanner.nextLine();

        System.out.println("Ingrese la categoría de la nota:");
        String nombreCategoria = scanner.nextLine();
        
        // Obtener la fecha y hora actual
        LocalDateTime fechaActual = LocalDateTime.now();

        Nota nuevaNota = new Nota(titulo, contenido, fechaActual, fechaActual, this);
        gestor.agregarNota(nombreCategoria, nuevaNota);
    }

    @Override
    public void editarNota(Nota nota) {

    }

    @Override
    public void eliminarNota(Nota nota) {
    }

}
