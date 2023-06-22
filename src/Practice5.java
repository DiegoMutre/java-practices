import java.util.ArrayList;
import java.util.Scanner;

public class Practice5 {
    static class Paciente {
        Scanner scanner = new Scanner(System.in);

        int historiaLaboral;
        int cedula;
        String nombresCompletos;
        int edad;

        ArrayList<String> sintomas = new ArrayList<>();
        String prescripcionMedica;
        String tieneCovid = "NO";

        public void solicitarHistoriaLaboral() {
            String historiaLaboralString;

            System.out.println("Historia laboral:");
            historiaLaboralString = scanner.nextLine();


            while (!historiaLaboralString.matches("^[0-9]{1,4}$")) {
                System.out.println("Historia laboral (solo numeros, minimo 1 y maximo 4):");
                historiaLaboralString = scanner.nextLine();
            }

            historiaLaboral = Integer.parseInt(historiaLaboralString);
        }

        public void solicitarCedula() {
            String cedulaString;

            System.out.println("Cedula:");
            cedulaString = scanner.nextLine();


            while (!cedulaString.matches("^[0-9]{10}$")) {
                System.out.println("Cedula (10 numeros, cedula Ecuatoriana):");
                cedulaString = scanner.nextLine();
            }

            cedula = Integer.parseInt(cedulaString);
        }

        public void solicitarNombresCompletos() {
            System.out.println("Nombres completos:");
            nombresCompletos = scanner.nextLine().trim();


            while (!nombresCompletos.matches("^[a-zA-Z ]+$")) {
                System.out.println("Nombres completos (solo letras):");
                nombresCompletos = scanner.nextLine().trim();
            }
        }

        public void solicitarEdad() {
            String edadString;

            System.out.println("Edad:");
            edadString = scanner.nextLine();


            while (!edadString.matches("^[0-9]{1,3}$")) {
                System.out.println("Edad: (solo numeros, maximo 3 numeros):");
                edadString = scanner.nextLine();
            }

            edad = Integer.parseInt(edadString);
        }

        public void solicitarSintomas() {
            char deseaIngresarSintoma = 's';

            while (deseaIngresarSintoma == 's' && sintomas.size() != 4) {

                System.out.println("Sintoma " + (sintomas.size() + 1) + ":");
                String sintoma = scanner.nextLine().toLowerCase().trim();

                while (!sintoma.matches("^[a-zA-Z ]+$")) {
                    System.out.println("Sintoma " + (sintomas.size() + 1) + " (solo letras):");
                    sintoma = scanner.nextLine().toLowerCase().trim();
                }

                sintomas.add(sintoma);

                if (sintomas.size() != 4) {
                    System.out.println("Desea ingresar otro sintoma? S/N:");
                    deseaIngresarSintoma = scanner.nextLine().toLowerCase().charAt(0);
                }
            }
        }

        public void solicitarPrescripcionMedica() {
            System.out.println("Registre la prescripcion medica:");
            prescripcionMedica = scanner.nextLine().trim();


            while (!prescripcionMedica.matches("^[a-zA-Z ]+$")) {
                System.out.println("Registre la prescripcion medica (solo letras, no puede ir vacio):");
                prescripcionMedica = scanner.nextLine().trim();
            }
        }

        public void determinarSiTieneCovid() {

            if (sintomas.contains("fatiga") && sintomas.contains("fiebre") && sintomas.contains("perdida de olfato y gusto")) {
                tieneCovid = "SI";
            }
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Paciente> pacientes = new ArrayList<>();
        char deseaIngresarPaciente = 's';

        while (deseaIngresarPaciente == 's' && pacientes.size() != 6) {

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

            System.out.println("DESEA INGRESAR OTRO PACIENTE (S/N):");
            deseaIngresarPaciente = scanner.nextLine().toLowerCase().charAt(0);
        }

        imprimirPacientesConCovid(pacientes);

    }

    static void imprimirPacientesConCovid(ArrayList<Paciente> pacientes) {
        int pacientesConCovid = 0;

        System.out.printf("%50s\n", "CENTRO MÉDICO MARÍA AUXILIADORA");

        System.out.printf("%-25s %-25s %-25s %-25s\n", "HIST.CLIN", "CEDULA", "NOMBRES", "COVID-19");

        for (Paciente paciente : pacientes) {
            if (paciente.tieneCovid.equals("SI")) {
                pacientesConCovid++;
                System.out.printf("%-25s %-25d %-25s %-25s\n", paciente.historiaLaboral, paciente.cedula, paciente.nombresCompletos, paciente.tieneCovid);

            }
        }

        System.out.println("CANTIDAD DE PACIENTES CON COVID-19: " + pacientesConCovid);
        System.out.println("---GRACIAS POR USAR EL SISTEMA---");
    }
}
