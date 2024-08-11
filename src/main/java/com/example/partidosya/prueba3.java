package com.example.partidosya;

import java.io.IOException;


public class prueba3 {
    public static void main(String[] args) throws IOException {

       String resultado = "";

       int contador = 0;
       int numero = 1;
       while (contador < 100) {
            if (esprimo(numero)) {
                resultado += numero + " ";
                contador++;
            }
            numero++;
        }
        System.out.println("los primeros cien numeros primos son : " + resultado);
        }

        private static boolean esprimo(Integer numero) {


            boolean esNumeroPrimo = true;

            for (int i = 2; i < numero; i++) {

                if (numero % i ==0) {
                    esNumeroPrimo = false;
                    break;
                }
            }

            return esNumeroPrimo;
        }

    }

