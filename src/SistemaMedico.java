import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaMedico {
    private final Scanner scanner = new Scanner(System.in);
    ArrayList<Paciente> pacientes = new ArrayList<>();

    public void registrarPacientes() {

        System.out.println("----Ingrese datos del paciente----");

        Paciente paciente = new Paciente();

        paciente.solicitarHistoriaLaboral();
        paciente.solicitarCedula();
        paciente.solicitarNombresCompletos();
        paciente.solicitarEdad();

        System.out.println("----Ingrese sintomas del paciente----");

        paciente.solicitarSintomas();
        paciente.solicitarPrescripcionMedica();
        paciente.determinarSiTieneCovid();

        pacientes.add(paciente);


        if (pacientes.size() != 6) {
            System.out.println("DESEA INGRESAR OTRO PACIENTE (S/N):");
            char deseaIngresarOtroPaciente = scanner.nextLine().trim().charAt(0);

            if (deseaIngresarOtroPaciente == 's') {
                registrarPacientes();
            }
        }
    }

    public void imprimirPacientesConCovid() {
        System.out.printf("%50s\n", "CENTRO MÉDICO MARÍA AUXILIADORA");

        // Filtra y retorna solo los pacientes que tienen covid, es decir, `paciente.tieneCovid == "SI"`
        List<Paciente> pacientesConCovid = pacientes.stream().filter(paciente -> paciente.tieneCovid.equals("SI")).toList();

        // TODO: Expandir explicacion
        // Importante: La sintaxis para imprimir este tipo de "tablas" es de la siguiente manera:
        // "%[ancho]tipo", si el ancho es un valor positivo, añade espacios en blanco para rellenar la ancho desde la izquierda
        // Y si es un valor negativo,  añade espacios en blanco desde la derecha
        if (pacientesConCovid.size() > 0) {
            System.out.printf("%-25s %-25s %-40s %-25s\n", "HIST.CLIN", "CEDULA", "NOMBRES", "COVID-19");
            for (Paciente paciente : pacientesConCovid) {
                System.out.printf("%-25s %-25d %-40s %-25s\n", paciente.historiaLaboral, paciente.cedula, paciente.nombresCompletos, paciente.tieneCovid);
            }
        } else {
            System.out.printf("%50s\n", "NO HAY PACIENTES CON COVID!");
        }

        System.out.println("CANTIDAD DE PACIENTES CON COVID-19: " + pacientesConCovid.size());
        System.out.println("---GRACIAS POR USAR EL SISTEMA---");
    }

}
