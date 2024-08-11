package com.example.partidosya;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prueba2 {
    public static void main(String[] args) throws IOException {
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

    if (valor1 % 2 == 0) {
        System.out.println("El número " + valor1 + " es par");
    } else {
        System.out.println("El número " + valor1 + " es impar");
    }
        if (valor2 % 2 == 0) {
            System.out.println("El número " + valor2 + " es par");
        } else {
            System.out.println("El número " + valor2 + " es impar");
        }
        if (valor3 % 2 == 0) {
            System.out.println("El número " + valor3 + " es par");
        } else {
            System.out.println("El número " + valor3 + " es impar");
        }
}

}
