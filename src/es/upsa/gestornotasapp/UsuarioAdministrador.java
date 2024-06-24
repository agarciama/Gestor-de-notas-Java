/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.upsa.gestornotasapp;

import java.time.LocalDateTime;
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
        System.out.println("Opciones de Administración");
        System.out.println("1 - Ver listado de notas");
        System.out.println("2 -  Agregar Nota (como administrador)");
        System.out.println("3 - Editar Nota");
        System.out.println("4 - Eliminar Nota");
        System.out.println("5 - Salir");
    }

    
    //Metodos abstractos de la InterfazGestionarNotas
    @Override
    public void visualizarNotas() {
        List<Categoria> categorias = gestor.getCategorias();

        for (Categoria categoria : categorias) {
            for (Nota nota : categoria.getNotas()) {
                System.out.println(nota.getTitulo());
                System.out.println(categoria.getNombre());
                System.out.println(nota.getContenido());
                System.out.println(nota.getFechaCreacion());
                System.out.println(nota.getFechaUltimaModificacion());
                System.out.println(nota.getUsuario().getNombre());
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

        Nota nuevaNota = new Nota(titulo, contenido, LocalDateTime.MIN, LocalDateTime.MIN, this);
        gestor.agregarNota(nombreCategoria, nuevaNota);
    }

    @Override
    public void editarNota(Nota nota) {

    }

    @Override
    public void eliminarNota(Nota nota) {
    }

}
