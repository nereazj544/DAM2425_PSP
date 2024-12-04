package fp.dam.psp.Other.Ascensores.other;

import java.util.Scanner;

public class Edificio {
    private ascensor ascensor1;
    private ascensor ascensor2;
    private Scanner scanner;

    public Edificio() {
        ascensor1 = new ascensor("ascensor 1", 0);  // Comienza en la planta baja
        ascensor2 = new ascensor("ascensor 2", 10); // Comienza en el piso 10
        scanner = new Scanner(System.in);
    }

    public void iniciarSimulacion() {
        ascensor1.start();
        ascensor2.start();

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            procesarOpcion(opcion);
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- SIMULACIÓN DE ascensorES ---");
        System.out.println("1. Llamar ascensor 1");
        System.out.println("2. Llamar ascensor 2");
        System.out.println("3. Pausar ascensor 1");
        System.out.println("4. Reanudar ascensor 1");
        System.out.println("5. Pausar ascensor 2");
        System.out.println("6. Reanudar ascensor 2");
        System.out.println("7. Finalizar simulación");
        System.out.print("Elige una opción: ");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                llamarascensor(ascensor1);
                break;
            case 2:
                llamarascensor(ascensor2);
                break;
            case 3:
                pausarascensor(ascensor1);
                break;
            case 4:
                reanudarascensor(ascensor1);
                break;
            case 5:
                pausarascensor(ascensor2);
                break;
            case 6:
                reanudarascensor(ascensor2);
                break;
            case 7:
                finalizarSimulacion();
                break;
            default:
                System.out.println("Opción no válida. Intenta de nuevo.");
        }
    }

    private void llamarascensor(ascensor ascensor) {
        System.out.println("Has llamado a " + ascensor.getName());
        // Aquí puedes añadir lógica para la interacción con personas y el destino
    }

    private void pausarascensor(ascensor ascensor) {
        System.out.println("Pausando " + ascensor.getName());
        ascensor.suspender();
    }

    private void reanudarascensor(ascensor ascensor) {
        System.out.println("Reanudando " + ascensor.getName());
        ascensor.reanudar();
    }

    private void finalizarSimulacion() {
        System.out.println("Finalizando simulación...");
        ascensor1.terminar();
        ascensor2.terminar();
        System.exit(0);
    }

    public static void main(String[] args) {
        Edificio edificio = new Edificio();
        edificio.iniciarSimulacion();
    }
}
