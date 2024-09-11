package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;
    public static int[] calculado;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: El Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+ calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }
    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }

    public static void solicitarDatos(){
        int i=0;
        do{
            System.out.println("\nDigite el precio del producto " + (i+1) + ": ");
            precios[i]=reader.nextDouble();
            System.out.println("\nDigite la cantidad del producto " + (i+1) + ": ");
            unidades[i]=reader.nextInt();
            i++;
        }while(i<precios.length);
    }
    /** 
     * Descripcion: Este metodo se encarga de calcular el total de las unidades vendidas.
     * @return int El numero del total de unidades vendidas. 
     * pre: El arreglo `unidades` debe estar inicializado y no ser nulo.
     * pos: El valor de retorno es la suma de todos los elementos del arreglo unidades.
    */
    public static int calcularTotalUnidadesVendidas(){
        int total=0;
        int i=0;
        do{
            total += unidades[i];
            i++;
        }while(i<unidades.length);
        return total;

    }
    /** 
     * Descripcion: Este metodo se encarga de calcular el precio promedio de los productos.
     * @return double el precio promedio. 
     * pre: El arreglo precios debe estar inicializado y no ser nulo.
     * pos: El valor de retorno es el promedio de todos los elementos del arreglo precios.
    */
    public static double calcularPrecioPromedio(){
        int i= 0;
        double sumatoria = 0;
        double promedio = 0;
        do{
            sumatoria += precios[i];
            i++;
        }while(i<precios.length);
        promedio = sumatoria / precios.length;
        return promedio;

    }
    /** 
     * Descripcion: Este metodo se encarga de calcular las ventas totales.
     * @return double las ventas totales. 
     * pre: Los arreglos `precios` y `unidades` deben estar inicializados y no ser nulos, y deben tener la misma longitud.
     * pos: El valor de retorno es la suma de la multiplicación de cada elemento correspondiente de `precios` y `unidades`.
    */
    public static double calcularVentasTotales(){
        double totalVentas=0;
        int i=0;
        do {
            totalVentas += precios[i] * unidades[i];
            i++;
        } while (i < precios.length);
        return totalVentas;

    }
 /** 
     * Descripcion: Este metodo se encarga de consultar la cantidad de referencias con un precio superior al límite especificados.
     * @return double las ventas totales. 
     * @param limite El límite de precio a partir del cual se contarán las referencias.
     * pre: El arreglo `precios` debe estar inicializado y no ser nulo.
     * pos: El valor de retorno es la cantidad de elementos en `precios` que son mayores a `limite`.
    */
    public static int consultarReferenciasSobreLimite(double limite){
        calculado = new int[precios.length];
        int i= 0;
        int mayorLimite = 0;

        do{
            int totalVenta = 0;
            totalVenta += (precios[i]*unidades[i]);
            calculado[i]=totalVenta;
            if(calculado[i]>limite){
                mayorLimite++;
            }
        if (i==precios.length) {
            break;
        }
        i++;
    }while(i<precios.length);

    return mayorLimite;
}

}
