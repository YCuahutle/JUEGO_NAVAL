import java.util.*;
public class JUEGO_NAVAL {

    public static int puntaje;
    public static int puntaje2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        System.out.println("Cual es tu nombre jugador 1?");
        String nombre = sc.nextLine();
        System.out.println("Suerte, " + nombre);
        System.out.println(" ");
        System.out.println("Cual es tu nombre jugador 2?");
        String nombre2 = sc.nextLine();
        System.out.println("Suerte, " + nombre2);
        System.out.println(" ");
        ArrayList<String> nombres = new ArrayList<String>();
        ArrayList<Integer> puntos = new ArrayList<Integer>();
        ArrayList<ArrayList> lista = new ArrayList<ArrayList>();
        nombres.add(nombre);
        nombres.add(nombre2);
        puntos.add(puntaje);
        puntos.add(puntaje2);
        lista.add(nombres);
        lista.add(puntos);
        boolean levelOne = false;
        boolean perdido = false;
        char[][] matriz = null;
        char[][] matrizBarco = null;
        char[][] matrizBarco2 = null;
        int numeroBarcos = 0;
        int c = 11;
        while (c == 11) {
            mostrarMenu(nombre);
            System.out.println("Elija opcion");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Escoje el tamaño del tablero");
                    int tamano = sc.nextInt();


                    System.out.println("Escogue cuantos barcos quieres, menos de 4");
                    int barcos = sc.nextInt();

                    matriz = new char[tamano][tamano];
                    matrizBarco = new char[tamano][tamano];
                    matrizBarco2 = new char[tamano][tamano];
                    if (barcos <= 4) {
                        numeroBarcos = barcos;
                    } else {
                        System.out.println("ingrese un numero valido");
                        System.out.println(" ");
                    }
                    levelOne = true;
                    break;
                case 2:
                    if (levelOne == false) {
                        System.out.println("----------- Debes eligir la difilcutad primero, saliendo... ----------------");
                        System.out.println(" ");
                        break;
                    } else {
                        generarTablero(matriz);
                        generarTablero(matrizBarco);
                        generarTablero(matrizBarco2);
                        break;
                    }
                case 3:
                    if (levelOne == false) {
                        System.out.println("----------- Debes eligir la difilcutad primero, saliendo... ----------------");
                        System.out.println(" ");
                        break;
                    } else {
                        imprimirTablero(matriz);

                        break;
                    }
                case 4:
                    generarBarcos(matrizBarco, numeroBarcos);
                    System.out.println("------------------ Matriz del jugador 2 ---------------------------------");
                    System.out.println(" ");
                    generarBarcos(matrizBarco2, numeroBarcos);
                    break;
                case 5:
                    if (levelOne == false) {
                        System.out.println("----------- Debes eligir la difilcutad primero, saliendo... ----------------");
                        System.out.println(" ");
                        break;
                    } else {
                        imprimirTablero(matrizBarco);
                        System.out.println(" ");
                        System.out.println("------------------ Matriz del jugador 2 ---------------------------------");
                        imprimirTablero(matrizBarco2);

                        break;
                    }
                case 6:
                    int cordX, cordY;
                    if (levelOne == false) {
                        System.out.println("-----------  Debes eligir la difilcutad primero, saliendo... ----------------");
                        System.out.println(" ");
                        break;
                    } else {
                       int lol = numeroBarcos;
                        while (lol <= 4){
                            System.out.println("En que fila?");
                        cordX = sc.nextInt();
                        System.out.println("En que columna?");
                        cordY = sc.nextInt();
                        cordX -= 1;
                        cordY -= 1;
                            perdido = apuntarDatosTablero(cordX, cordY, matriz, matrizBarco2, perdido);

                        if (perdido == true) {
                            System.out.println("Perdiste la partida ");
                            System.out.println("--------turno del siguiente jugador ------------");
                                perdido = apuntarDatosTablero2(cordX, cordY, matriz, matrizBarco, perdido);
                            opcion = 0;
                            c = 1;
                        }
                    }
                        break;
                    }
                case 0:
                    c = 12;
            }
        }
    }

    public static void mostrarMenu(String nombre) {

        System.out.println("1.-Para eligir tu tablero de este juego ");
        System.out.println("2.-Para generar el tablero");
        System.out.println("3.-Para imprimir el tablero ");
        System.out.println("4.-Para generar Barcos");
        System.out.println("5.-Para imprimir tablero con los barcos");
        System.out.println("6.-Empezamos el juego " + nombre + " , deberá introducir coordenadas");
        System.out.println("");
        System.out.println("0.-Para salir");

    }

    public static void generarTablero(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {

                matriz[i][j] = '-';

            }

        }
    }

    public static void imprimirTablero(char[][] matriz) {
        int b = 0;
        int c = 0;
        System.out.print("  ");
        for (int i = 0; i < matriz.length; i++) {
            c++;
            if (i <= 8) {
                System.out.print(0);
            }
            System.out.print(c + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < matriz.length; i++) {
            b++;
            if (i <= 8) {
                System.out.print(0);
            }
            System.out.print(b);
            for (int j = 0; j < matriz[0].length; j++) {

                if (j == 0 || j == matriz.length - 1) {
                    if (j == 0) {
                        System.out.print("| " + matriz[i][j]);

                    }
                    if (j == matriz.length - 1) {
                        System.out.print(matriz[i][j] + " |");
                    }
                } else {
                    System.out.print(" " + matriz[i][j] + " ");
                }
            }
            System.out.println("");
        }

    }

    public static void generarBarcos(char[][] matriz, int numeroBarcos) {
        boolean unaVez;
        Scanner sc = new Scanner(System.in);
        int ls = 28;
        while (ls == 28) {
            for (int i = 0; i < matriz.length && numeroBarcos > 0; i++) {
                unaVez = false;
                for (int j = 0; j < matriz[0].length && numeroBarcos > 0; j++) {

                    if (unaVez == false) {
                        /*matriz[i][(int) (Math.random() * matriz[0].length)] = '.';
                        numeroBarcos--;*/

                        System.out.println(" ingresa la fila ");
                        int cordX = sc.nextInt();
                        System.out.println(" ingresa la columna ");
                        int cordY = sc.nextInt();
                        cordY-=1;
                        cordX-=1;
                        matriz[cordX][cordY] = '.';
                        numeroBarcos--;
                    }
                    unaVez = true;
                    ls = 30;
                }
            }

        }
        if (numeroBarcos > 0) ;
        {
            ls = 30;
        }

    }

    public static boolean apuntarDatosTablero(int cordX, int cordY, char[][] matriz, char[][] matrizBarco, boolean perdido) {
        if (matriz[cordX][cordY] == '#' || matrizBarco[cordX][cordY] == '.') {
            if (matrizBarco[cordX][cordY] == '.') {
                cordX-=1;
                cordY-=1;
                puntaje += 1;
                System.out.println("Hay una bomba");
                System.out.println("ACERTASTE TU PUNTAJE ES " + puntaje);
                imprimirTablero(matrizBarco);
                return perdido;
            }

            if (matrizBarco[cordX][cordY] == '#') {
                System.out.println("Ya introduciste en esa casilla");
                //generarBarcos(matrizBarco);
                imprimirTablero(matrizBarco);
            }

        } else {
            matrizBarco[cordX][cordY] = '#';
            System.out.println("No hay bomba");
            imprimirTablero(matrizBarco);
            perdido = true;
        }


        return perdido;
    }

    public static boolean apuntarDatosTablero2(int cordX, int cordY, char[][] matriz, char[][] matrizBarco2, boolean perdido) {
        if (matriz[cordX][cordY] == '*' || matrizBarco2[cordX][cordY] == '.') {
            if (matrizBarco2[cordX][cordY] == '.') {
                cordX -= 1;
                cordY -= 1;
                puntaje2 += 1;
                System.out.println("Hay una bomba");
                System.out.println("ACERTASTE TU PUNTAJE ES " + puntaje2);
            imprimirTablero(matrizBarco2);
                return perdido;
            }

            if (matrizBarco2[cordX][cordY] == '*') {
                System.out.println("Ya introduciste en esa casilla");
                imprimirTablero(matrizBarco2);
            }

        } else {
            matrizBarco2[cordX][cordY] = '*';
            System.out.println("No hay bomba");
            imprimirTablero(matrizBarco2);
            perdido = true;
        }


        return perdido;
    }
}