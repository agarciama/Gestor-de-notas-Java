/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.upsa.gestornotasapp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author administrador
 */


public class Gestor {
    private List<Categoria> categorias;
    
    public Gestor() {
        this.categorias = new ArrayList<>();
    }
    
    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
    
    public void eliminarCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }
    
    public void agregarNota(String nombreCategoria, Nota nota) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                categoria.agregarNota(nota);
                return;
            }
        }
        // Si no existe la categoría, crearla y agregar la nota
        Categoria nuevaCategoria = new Categoria(nombreCategoria);
        nuevaCategoria.agregarNota(nota);
        categorias.add(nuevaCategoria);
    }
    
    public void eliminarNota(String nombreCategoria, Nota nota) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                categoria.eliminarNota(nota);
                return;
            }
        }
    }
    
    public List<Nota> getNotas(String nombreCategoria) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                return categoria.getNotas();
            }
        }
        return new ArrayList<>();
    }
}

