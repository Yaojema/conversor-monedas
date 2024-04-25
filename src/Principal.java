import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Bienvenido a compara ratio");

        Scanner lectura = new Scanner(System.in);

        ConsultaExchange consulta = new ConsultaExchange();

        List<Exchange> list = new ArrayList<>();
//        list.add(exchange);
//        System.out.println("La lista es: " + list);

        int salir = 1;
        do {
            try {
                System.out.println("****************************************");
                System.out.println("--- CONVERSOR DE DIVISAS ---");
                System.out.println("****************************************");
                System.out.println("Escriba el par de divisas, a convertir -> ejemplo USD -> EUR");
                System.out.println("Ingresar divisa base");
                var baseDivisa = lectura.next();
                System.out.println("Ingresar divisa target");
                var targetDivisa = lectura.next();
                System.out.println("Ingresar monto a convertir");
                var monto = lectura.nextInt();
                // cast
//            var monto = Integer.valueOf(lectura.nextLine());

                Exchange exchange = consulta.buscarPar(baseDivisa, targetDivisa, monto);
                System.out.println("De: " + exchange.base_code() + " >> " + exchange.target_code() + " Es:");
                System.out.println(exchange.conversion_result());

                System.out.println("****************************************");

                // Guardar en Json
                GeneradorDeArchivo generador = new GeneradorDeArchivo();
                generador.guardarJson(exchange);

                list.add(exchange);

            } catch (NumberFormatException | IOException e) {
                System.out.println("No encontrado " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando la aplicacion");
            }

            System.out.println(list);
            System.out.println("Deseas continuar? \nsi = 1 | no = 0");

            salir = lectura.nextInt();

        } while(salir != 0);
        System.out.println("Gracias por usar el servicio!");
    }
}
