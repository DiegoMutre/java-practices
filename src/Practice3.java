import java.util.ArrayList;
import java.util.Scanner;

public class Practice3 {

    static Scanner scanner = new Scanner(System.in);

    private static class Cliente {
        String nombre_apellido, correo, cedula, direccion;
        ArrayList<Articulo> articulos = new ArrayList<>();
    }

    private static class Articulo {
        String nombre, codigo;
        int cantidad;
        double precio;
    }

    public static void main(String[] args) {

        ArrayList<Cliente> clientes = new ArrayList<>();
        char desea_ingresar_cliente = 'S';

        while (desea_ingresar_cliente == 'S') {
            char desea_ingresar_articulo = 'S';

            Cliente cliente = solicitar_cliente();
            clientes.add(cliente);

            while (cliente.articulos.size() < 5 && desea_ingresar_articulo == 'S') {

                cliente.articulos.add(solicitar_articulo());

                if (cliente.articulos.size() == 5) break;

                System.out.println("Desea ingresar otro articulo S/N:");

                // Convierte el string a mayusculas y toma el primer caracter
                // TODO: Validar si el caracter es S o N
                desea_ingresar_articulo = scanner.nextLine().toUpperCase().charAt(0);
            }

            imprimir_facturas(clientes);

            System.out.println("DESEA INGRESAR OTRO CLIENTE (S/N):");
            desea_ingresar_cliente = scanner.nextLine().toUpperCase().charAt(0);
        }

        System.out.println("--- GRACIAS POR USAR EL SISTEMA ---");

    }

    static Cliente solicitar_cliente() {
        Cliente nuevo_cliente = new Cliente();

        System.out.println("-----Ingrese datos del cliente-----");

        System.out.println("Cedula:");
        nuevo_cliente.cedula = scanner.nextLine();

        while (!nuevo_cliente.cedula.matches("^[0-9]{10}$")) {
            System.out.println("Cedula (10 numeros):");
            nuevo_cliente.cedula = scanner.nextLine();
        }

        System.out.println("Nombres y Apellidos:");
        nuevo_cliente.nombre_apellido = scanner.nextLine().trim();

        while (!nuevo_cliente.nombre_apellido.matches("^[a-zA-Z ]+$")) {
            System.out.println("Nombres y Apellidos (solo letras):");
            nuevo_cliente.nombre_apellido = scanner.nextLine().trim();
        }

        System.out.println("Correo electronico:");
        nuevo_cliente.correo = scanner.nextLine().trim().toLowerCase();

        while (!nuevo_cliente.correo.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) {
            System.out.println("Correo electronico (debe seguir el formato \"example@example.com\"): ");
            nuevo_cliente.correo = scanner.nextLine().trim().toLowerCase();
        }

        System.out.println("Direccion:");
        nuevo_cliente.direccion = scanner.nextLine().trim();

        while (!nuevo_cliente.direccion.matches("^[a-zA-Z0-9 ]+$")) {
            System.out.println("Direccion (solo letras y numeros):");
            nuevo_cliente.direccion = scanner.nextLine().trim();
        }

        return nuevo_cliente;
    }

    static Articulo solicitar_articulo() {
        Articulo articulo_nuevo = new Articulo();
        String precio_string, cantidad_string;

        System.out.println("-----Ingrese datos de los articulos-----");

        System.out.println("Nombre:");
        articulo_nuevo.nombre = scanner.nextLine().trim();

        while (!articulo_nuevo.nombre.matches("^[a-zA-Z0-9 ]+$")) {
            System.out.println("Nombre (solo letras):");
            articulo_nuevo.nombre = scanner.nextLine().trim();
        }

        System.out.println("Codigo:");
        articulo_nuevo.codigo = scanner.nextLine().trim();

        while (!articulo_nuevo.codigo.matches("^[0-9]+$")) {
            System.out.println("Codigo (solo numeros):");
            articulo_nuevo.codigo = scanner.nextLine().trim();
        }

        System.out.println("Precio:");
        precio_string = scanner.nextLine().trim();

        while (!precio_string.matches("^[0-9]+(\\.[0-9]{2})?$")) {
            System.out.println("Precio (solo numeros, ejemplo: 1 o 1.99):");
            precio_string = scanner.nextLine().trim();
        }

        System.out.println("Cantidad:");
        cantidad_string = scanner.nextLine().trim();

        while (!cantidad_string.matches("^[0-9]+$")) {
            System.out.println("Cantidad (solo numeros):");
            cantidad_string = scanner.nextLine().trim();
        }

        articulo_nuevo.precio = Double.parseDouble(precio_string);
        articulo_nuevo.cantidad = Integer.parseInt(cantidad_string);

        return articulo_nuevo;
    }

    static void imprimir_facturas(ArrayList<Cliente> clientes) {
        int total_articulos = 0;
        double total_precio = 0;

        System.out.printf("%25s \n", "COMERCIAL PEPITO S.A.");

        for(Cliente cliente : clientes) {
            int index = clientes.indexOf(cliente) + 1;
            System.out.printf("%25s #%d\n", "FACTURA", index);

            // Imprimir datos del cliente
            System.out.printf("CEDULA: " + "%-25s", cliente.cedula);
            System.out.printf("CLIENTE: " + "%-25s\n", cliente.nombre_apellido);

            System.out.printf("CORREO: " + "%-25s", cliente.correo);
            System.out.printf("DIRECCION: " + "%-25s\n", cliente.direccion);

            System.out.println("----------ARTICULOS----------");
            System.out.printf("%-15s %-30s %-15s %-15s %-15s\n", "COD", "ARTICULO", "P.UNIT", "CANT.", "TOTAL");

            // Imprimir todos los articulos de cliente
            for (Articulo articulo : cliente.articulos) {
                total_articulos += articulo.cantidad;
                total_precio += articulo.precio * articulo.cantidad;
                System.out.printf("%-15s %-30s %-15.2f %-15d %-15.2f\n", articulo.codigo, articulo.nombre, articulo.precio, articulo.cantidad, articulo.precio * articulo.cantidad);
            }

            System.out.printf("%-60s %-15d %-15.2f\n", "TOTAL----------", total_articulos, total_precio);

            total_articulos = 0;
            total_precio = 0;
        }
    }

}
