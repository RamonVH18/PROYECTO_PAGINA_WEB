/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author rocha
 */
public class Validador {

    // Validar campo vacío
    public static boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    // Validar números enteros
    public static boolean esEnteroValido(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validar números decimales
    public static boolean esDecimalValido(String valor) {
        try {
            Double.valueOf(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean esEmailValido(String email) {
        if (estaVacio(email)) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Validar longitud mínima
    public static boolean tieneLongitudMinima(String valor, int min) {
        return valor != null && valor.trim().length() >= min;
    }

    // Validar longitud máxima
    public static boolean tieneLongitudMaxima(String valor, int max) {
        return valor != null && valor.trim().length() <= max;
    }
}
