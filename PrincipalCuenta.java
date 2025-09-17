
/**
 * 
 * @author Adrian Ernesto Cascante Perez 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalCuenta
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cuenta> cuentas = new ArrayList<>();
        int actual = -1; // indice de la cuenta seleccionada
        
        System.out.println("==================================");
        System.out.println("    CLI de proyecto - Clase Cuenta");
        System.out.println("==================================");
        
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de Cuentas Creadas");
            System.out.println("3) Listar Cuentas");
            System.out.println("4) Seleccionar Cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Consultar Saldo");
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("0) Salir");
            System.out.println("Opción: ");
            String op = sc.nextLine().trim();
            
            switch (op) {
                case "1": { // Crear Cuenta
                    System.out.print("Nombre de la cuenta habiente (enter si no desea): ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Saldo inicial (enter para 0.0): ");
                    String linea = sc.nextLine().trim();
                    Cuenta c;
                    if (linea.isEmpty()) {
                        c = nombre.isEmpty() ? new Cuenta(0.0) : new Cuenta(nombre, 0.0);
                    } else {
                        double p;
                        try {
                            p = Double.parseDouble(linea);
                        } catch (NumberFormatException e) {
                            System.out.println("Número inválido, se usará 0.0");
                            p = 0.0;
                        }
                        c = nombre.isEmpty() ? new Cuenta(p) : new Cuenta(nombre, p);
                    }
                    cuentas.add(c);
                    actual = cuentas.size() -1;
                    System.out.println("Cuenta creada y seleccionada (índice" + actual + ").");
                    break;
                }
                case "2": { // Conocer cantidad de Cuentas creadas
                    System.out.println("Cantidad de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;
                }
                case "3": { // Listar Cuentas
                    if (cuentas.isEmpty())
                        System.out.println("No hay cuentas creadas");
                    else
                        System.out.println("Índice | Código | Saldo");
                        for (int i = 0; i < cuentas.size(); i++) {
                            Cuenta c = cuentas.get(i);
                            System.out.printf("  %d    | %s | %.2f%n", i, c.getCodCuenta(), c.getSaldo());
                        }
                    break;
                }
                case "4": { // Seleccionar Cuenta actual
                    if (cuentas.isEmpty()) {
                        System.out.println("Se debe crear una cuenta primero.");
                        break;   
                    }
                    
                    System.out.print("Índice de la cuenta a seleccionar: ");
                    try {
                        int idx = Integer.parseInt(sc.nextLine().trim());
                        if (idx >= 0 && idx < cuentas.size()) {
                            actual = idx;
                            System.out.println("Cuenta índice " + actual + " seleccionada.");
                        } else {
                            System.out.println("Índice fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                    }
                    break;
                }
                case "5": { // Depositar
                    if (actual < 0 || cuentas.isEmpty()){
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a depositar: ");
                    try {
                        double monto = Double.parseDouble(sc.nextLine().trim());
                        cuentas.get(actual).depositar(monto);
                        System.out.println("Saldo actual: " + cuentas.get(actual).getSaldo());
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido.");
                    }
                    break;
                }
                case "6": { // Retirar
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a retirar: ");
                    try {
                        double monto = Double.parseDouble(sc.nextLine().trim());
                        cuentas.get(actual).retirar(monto);
                        System.out.println("Saldo actual: " + cuentas.get(actual).getSaldo());
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido.");
                    }
                    break;
                 }
                 case "7": { // Consultar Saldo
                     if (actual < 0 || cuentas.isEmpty()) {
                         System.out.println("Debe crear y seleccionar una cuenta primero.");
                         break;
                     }
                     System.out.println("Saldo disponible: " + cuentas.get(actual).getSaldo());
                     break;
                 }
                 case "8": { // Consultar estado (toString)
                     if (actual < 0 || cuentas.isEmpty()) {
                         System.out.println("Debe crear y seleccionar una cuenta primero.");
                         break;
                     }    
                     System.out.println(cuentas.get(actual).toString());
                     break;
                 }
                 case "0": {
                     salir = true;
                     System.out.println("Saliendo...");
                     break;
                 }
                 default:
                     System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}