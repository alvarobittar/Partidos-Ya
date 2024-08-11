package com.example.partidosya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class pruebas {
    public static void main(String[] args ) throws IOException {
        InputStreamReader capturateclado = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(capturateclado);


        System.out.println("Introduce un número: ");
        String strValor1 = teclado.readLine();
        Integer valor1 = Integer.parseInt(strValor1);

        System.out.println("Introduce el segundo número: ");
        String strValor2 = teclado.readLine();
        Integer valor2 = Integer.parseInt(strValor2);

        System.out.println("Introduce el tercer numero número: ");
        String strValor3 = teclado.readLine();
        Integer valor3 = Integer.parseInt(strValor3);

        if (valor1 > valor2 && valor1 > valor3) {
            System.out.println("El número mayor es: " + valor1);
        } else if (valor2 > valor1 && valor2 > valor3) {
            System.out.println("El número mayor es: " + valor2);
        } else {
            System.out.println("El número mayor es: " + valor3);
        }

        if (valor1 < valor2 && valor1 < valor3) {
            System.out.println("El número menor es: " + valor1);
        } else  if (valor2 < valor1 && valor2 < valor3) {
            System.out.println("El número menor es: " + valor2);
        } else {
            System.out.println("El número menor es: " + valor3);
        }
        }
    }

