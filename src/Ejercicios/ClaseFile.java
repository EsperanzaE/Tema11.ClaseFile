package Ejercicios;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ClaseFile {

    //
// En este programa, vamos a pedir el nombre de un fichero o de un directorio. el programa
// comprobará si el fichero o el directorio existe fisicamente o no y mostrará las propiedades
// del fichero o el directorio
//
    static Scanner scanner = new Scanner(System.in);
    static String nombreF = "";
    static File file = null;

    public static void ejercicio1() {
        //Vamos a solicitar el nombre del archivo o directorio
        System.out.println("introduzca el nombre del fichero o del directorio");
        nombreF = scanner.next();

        //introducimos el nombre del fichero o directorio al objeto File
        file = new File(nombreF);

        //aplicamos los métodos de la clase File conforme se nos pide en el ejercicio
        System.out.println("File name :" + file.getName());//devuelve el nombre del fichero o directorio
        System.out.println("Path: " + file.getPath());//Devuelve la ruta con la que se creó el objeto File.
        System.out.println("Absolute path:" + file.getAbsolutePath());//Devuelve la ruta absoluta asociada al objeto File.
        System.out.println("Parent:" + file.getParent());//Devuelve el directorio padre de File o null
        System.out.println("Exists :" + file.exists());//;Devuelve true si el fichero o directorio existe
        if (file.exists()) {
            System.out.println("Es de escritura:" + file.canWrite());
            System.out.println("Es de lectura: " + file.canRead());
            System.out.println("Es un directorio: " + file.isDirectory());
            System.out.println("Tamaño en bytes del fichero: " + file.length());
        }
    }

    //Programa para mostrar todo el contenido de un directorio
    public static void ejercicio2() {
        //Vamos a solicitar el nombre del archivo o directorio y lo devolvemos en un String
        String[] directorio = solicitaDirectorio();
        if (directorio.length != 0) {
            System.out.println(nombreF + " es un directorio, sus elementos son:");
            for (String elemento : directorio) {
                System.out.println(elemento);
            }
        } else {
            System.out.println(nombreF + " no es un directorio");
        }
    }

    //método para solicitar el nombre de un directorio e introducir su contenido en un array de String
// si el directorio ni tiene elementos, o no es un directorio, el array  que devuelve estará vacío
    private static String[] solicitaDirectorio() {
        String[] directorio = new String[0];
        //Vamos a solicitar el nombre del archivo o directorio
        System.out.println("introduzca el nombre del fichero o del directorio");
        nombreF = scanner.next();
        ///creamos el objeto file con el nombre introducido por pantalla
        file = new File(nombreF);
        //declaramos el array y lo llenamos con lo que nos va a devolver el método list()
        if (file.isDirectory()) {
            directorio = file.list();
        }
        return directorio;
    }

    // Partiendo del ejercicio anterior, tome todos los elementos del contenido de directorio e
    // indique si cada elemento es un archivo o un directorio.
    public static void ejercicio3() {
        //Vamos a solicitar el nombre del archivo o directorio y lo devolvemos en un String
        String[] directorio = solicitaDirectorio();
        if (directorio.length == 0) {
            System.out.println(nombreF + " No es un directorio");
        } else {
//metemos el directorio en un array de files porque no me funciona con el array de Strings
            File[] listaficheros = file.listFiles();
            System.out.println(nombreF + " es un directorio, sus elementos son:");

            for (int i = 0; i < listaficheros.length; i++) {
                //File elemento=new File(directorio[i]);
                System.out.print(listaficheros[i] + " es un ");
                if (listaficheros[i].isDirectory()) {
                    System.out.println("directorio ");
                }
                if (listaficheros[i].isFile()) {
                    System.out.println("fichero ");
                }
            }
        }
    }


    //Crear un objeto File ruta asociado al directorio c:/ficheros y un objeto File f asociado al fichero datos.txt
// que se encuentra en ese directorio (ruta). NO debe existir ni el fichero ni el directorio para que se creen.
// Mostrar el tamaño del archivo creado
    public static void ejercicio4() {
        File ruta = new File("c:/ficheros");//creamos el objeto file para la ruta
        File fichero = new File(ruta, "datos.txt");//creamos el objeto File para la ruta\fichero
        //con los siguientes métodos vamos a ver las características de la ruta y el archivo que acabamos de crear
        System.out.println("path del archivo " + fichero.getAbsolutePath());//nos devuelve el path del archivo
        System.out.println("ruta padre del archivo " + fichero.getParent());//nos devuelve la ruta padre del archivo
        System.out.println("el path de la ruta " + ruta.getAbsolutePath());//nos devuelve el path de la ruta
        System.out.println("la ruta padre de la ruta " + ruta.getParent());//nos devuelve la ruta padre de la ruta

        if (fichero.exists()) {//el fichero existe. Mostramos el tamaño
            System.out.println("Fichero " + fichero.getName() + " existe");
            System.out.println("Tamaño " + fichero.length() + " bytes");

        } else {//fichero no existe, si existe el directorio creamos el fichero, si no existe el directorio,
            //creamos el directorio y luego el fichero. La creación del fichero exige el uso de un try/catch o
            //usar throws IOExceptcion
            System.out.println("Fichero " + fichero.getName() + " no existe");
            if (ruta.exists()) {//la ruta existe, creamos el fichero
                System.out.println("la ruta " + ruta + "existe, , vamos a crear el fichero");
                crearFichero(fichero);
            } else {//si no existe la ruta, la creamos y si se crea bien,  creamos el fichero
                if (ruta.mkdir()) {
                    System.out.println("la ruta " + ruta + " no existe, la creamos y luego creamos el fichero");
                    crearFichero(fichero);
                } else {
                    System.out.println("no se ha podido crear la ruta " + ruta);
                }
            }
        }
        if (fichero.exists()) {
            System.out.println("el fichero " + fichero + " se ha creado correctamente y el" +
                    "contenido de su ruta es ");
            String[] contenido = ruta.list();
            for (String elemento : contenido) {
                System.out.println(elemento
                );
            }
        }
    }


    private static void crearFichero(File file) {
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            System.out.println("se ha producido un error inesperado al crear el fichero " + file);
        }

    }

    //Crea un método que reciba la ruta de un archivo de texto y devuelva un array de objetos con los caracteres
// del alfabeto ordenados según su frecuencia de aparición (primero el carácter que más veces aparece, luego
// el segundo que más aparece, etc), indicando también el número de veces que aparece cada carácter.
    public static void ejercicio5() {
// Los caracteres del fichero lo vamos a meter en un ArrayList de objetos CAracteres formados por el carácter
// y un contador
//montaremos el nombre del fichero y lo procesaremos con BufferedReader
        List<Caracteres> listaCaracteres = new ArrayList<>();
        //pedimos la ruta y el archivo y montamos el fichero que vamos a procesar
        File archivo = montarArchivo();
        BufferedReader input;
        String registro = "";//creamos esta variable para poder recorrerla como un array
        if (archivo.exists()) {
            try {
                input = new BufferedReader(new FileReader(archivo));
                registro = input.readLine();
                while (registro != null) {
                    for (int i = 0; i < registro.length(); i++) {//recorremos el registro y vamos añadiéndolo
                        //a la lista de forma ordenada usando binarySearch
                        añadeLista(listaCaracteres, registro.charAt(i));
                    }
                    registro = input.readLine();
                }
                imprime(listaCaracteres);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception exception) {
                System.out.println("error general de archivo");
            }
        }
    }

    private static void añadeLista(List<Caracteres> listaCaracteres, char c) {
        Caracteres caracteres = new Caracteres(c);
        int indiceInsercion = Collections.binarySearch(listaCaracteres, caracteres);
        if (indiceInsercion >= 0) {
            caracteres = listaCaracteres.get(indiceInsercion);
            caracteres.incrementa();
            listaCaracteres.set(indiceInsercion, caracteres);
        } else {
            indiceInsercion = -indiceInsercion - 1;
            listaCaracteres.add(indiceInsercion, caracteres);
        }
    }


    private static void imprime(List<Caracteres> listaCaracteres) {
        System.out.println(listaCaracteres);
    }

    private static File montarArchivo() {
        File ficheroFinal = null;
        String cadena = null;
        System.out.println("introduzca la ruta");
        cadena = scanner.nextLine();
        File ruta = new File(cadena);
        if (ruta.isDirectory()) {
            System.out.println("introduzca el nombre del fichero");
            cadena = scanner.nextLine();
            ficheroFinal = new File(ruta, cadena);
            if (!ficheroFinal.isFile()) {
                System.out.println("el fichero introducido no es tal ");
                ficheroFinal = null;
            }
        } else {
            System.out.println("el directorio introducido no es tal");
        }
        return ficheroFinal;

    }
}

