package Ejercicios;

public class Caracteres implements Comparable<Caracteres> {
    private Character caracter;
    int veces=0;

    public Caracteres(Character caracter ) {
        this.caracter = caracter;
        this.veces = 1;
    }

    public int getVeces() {
        return veces;
    }

public String toString(){
        return "\nEl caracter "+  this.caracter + " aparece "+this.veces+ " veces.";

}
    @Override
    public int compareTo(Caracteres caracteres) {
            return this.caracter.compareTo(caracteres.caracter);
        }

    public void incrementa() {
        this.veces +=1;
    }
}
