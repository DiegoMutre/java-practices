import java.util.ArrayList;
import java.util.Scanner;

public class Practice5 {
    static class Paciente {
        Scanner scanner = new Scanner(System.in);

        int historiaLaboral;
        long cedula;
        String nombresCompletos;
        int edad;

        ArrayList<String> sintomas = new ArrayList<>();
        String prescripcionMedica;
        String tieneCovid = "NO";

        public void solicitarHistoriaLaboral() {
            String historiaLaboralString;

            System.out.println("Historia laboral (minimo 1 digito, maximo 4):");
            historiaLaboralString = scanner.nextLine();

            if (!historiaLaboralString.matches("^[0-9]{1,4}+$")) {
                solicitarHistoriaLaboral();
            } else {
                historiaLaboral = Integer.parseInt(historiaLaboralString);
            }
        }

        public void solicitarCedula() {
            String cedulaString;

            System.out.println("Cedula (10 digitos, Cedula Ecuatoriana):");
            cedulaString = scanner.nextLine();

            if (!cedulaString.matches("^[0-9]{10}$")) {
                solicitarCedula();
            } else {
                cedula = Long.parseLong(cedulaString);
            }
        }

        public void solicitarNombresCompletos() {
            System.out.println("Nombres completos (dos nombres y dos apellidos):");
            nombresCompletos = scanner.nextLine().trim().toUpperCase();

            // El regex comprueba si son 4 palabras, dos nombres y dos apellidos
            // Tambien permite letras con tildes `\\p{L1}`
            // Y permite la letra `ñ`
            if (!nombresCompletos.matches("([a-zA-Z\\p{L1}ñ]+\\s+){3}[a-zA-Z\\p{L1}ñ]+")) {
                solicitarNombresCompletos();
            }
        }

        public void solicitarEdad() {
            String edadString;

            System.out.println("Edad: (solo digitos numericos, maximo 3 numeros):");
            edadString = scanner.nextLine();


            if (!edadString.matches("^[0-9]{1,3}$")) {
                solicitarEdad();
            } else {
                edad = Integer.parseInt(edadString);
            }
        }

        public void solicitarSintomas() {
            String sintoma;

            System.out.println("Sintoma " + (sintomas.size() + 1) + " (solo texto):");
            sintoma = scanner.nextLine().toLowerCase().trim().replaceAll("\\s+", " ");

            if (!sintoma.matches("^[a-zA-Z\\p{L1}ñ ]+$")) {
                solicitarSintomas();
            } else {
                sintomas.add(sintoma);

                if (sintomas.size() != 4) {
                    System.out.println("Desea ingresar otro sintoma? S/N:");
                    char deseaIngresarOtroSintoma = scanner.nextLine().toLowerCase().trim().charAt(0);

                    if (deseaIngresarOtroSintoma == 's') {
                        solicitarSintomas();
                    }
                }
            }

        }


        public void solicitarPrescripcionMedica() {
            System.out.println("Registre la prescripcion medica (no puede ir vacio):");
            prescripcionMedica = scanner.nextLine().trim().replaceAll("\\s+", " ");

            // Cualquier texto, numeros, signos, etc. seran permitidos
            // Esto se hace debido a que las prescripciones medicas pueden incluir numeros,
            // signos de puntuacion, letras con acento, etc.
            // Se podria mejorar para evitar caracteres innecesarios como `<>` o `\`
            if (prescripcionMedica.isEmpty()) {
                solicitarPrescripcionMedica();
            }
        }

        public void determinarSiTieneCovid() {
            if (sintomas.contains("fatiga") && sintomas.contains("fiebre") && sintomas.contains("perdida de olfato y gusto")) {
                tieneCovid = "SI";
            }
        }

    }

    static class SistemaMedico {
        Scanner scanner = new Scanner(System.in);
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
            int pacientesConCovid = 0;

            System.out.printf("%50s\n", "CENTRO MÉDICO MARÍA AUXILIADORA");

            System.out.printf("%-25s %-25s %-40s %-25s\n", "HIST.CLIN", "CEDULA", "NOMBRES", "COVID-19");

            for (Paciente paciente : pacientes) {
                if (paciente.tieneCovid.equals("SI")) {
                    pacientesConCovid++;
                    System.out.printf("%-25s %-25d %-40s %-25s\n", paciente.historiaLaboral, paciente.cedula, paciente.nombresCompletos, paciente.tieneCovid);

                }
            }

            System.out.println("CANTIDAD DE PACIENTES CON COVID-19: " + pacientesConCovid);
            System.out.println("---GRACIAS POR USAR EL SISTEMA---");
        }

    }

    public static void main(String[] args) {
        SistemaMedico sistemaMedico = new SistemaMedico();

        sistemaMedico.registrarPacientes();
        sistemaMedico.imprimirPacientesConCovid();
    }
}
