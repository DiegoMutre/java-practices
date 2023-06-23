import java.util.ArrayList;
import java.util.Scanner;

public class Paciente {
    private final Scanner scanner = new Scanner(System.in);

    int historiaLaboral;
    long cedula;
    String nombresCompletos;
    int edad;

    ArrayList<String> sintomas = new ArrayList<>();
    String prescripcionMedica;
    String tieneCovid = "NO";

    public void solicitarHistoriaLaboral() {
        String historiaLaboralString;

        System.out.println("Historia laboral:");
        historiaLaboralString = scanner.nextLine();

        while (!historiaLaboralString.matches("^[0-9]{1,4}+$")) {
            System.out.println("Historia laboral (solo digitos numericos, maximo 4):");
            historiaLaboralString = scanner.nextLine();
        }
        historiaLaboral = Integer.parseInt(historiaLaboralString);
    }

    public void solicitarCedula() {
        String cedulaString;

        System.out.println("Cedula:");
        cedulaString = scanner.nextLine();

        while (!cedulaString.matches("^[0-9]{10}$")) {
            System.out.println("Cedula (10 digitos, Cedula Ecuatoriana):");
            cedulaString = scanner.nextLine();
        }

        cedula = Long.parseLong(cedulaString);
    }

    public void solicitarNombresCompletos() {
        System.out.println("Nombres completos:");
        nombresCompletos = scanner.nextLine().trim().toUpperCase();

        // El regex comprueba si son 4 palabras, dos nombres y dos apellidos
        // Tambien permite letras con tildes `\\p{L1}`
        // Y permite la letra `ñ`
        while (!nombresCompletos.matches("([a-zA-Z\\p{L1}ñ]+\\s+){3}[a-zA-Z\\p{L1}ñ]+")) {
            System.out.println("Nombres completos (dos nombres y dos apellidos):");
            nombresCompletos = scanner.nextLine().trim().toUpperCase();
        }
    }

    public void solicitarEdad() {
        String edadString;

        System.out.println("Edad:");
        edadString = scanner.nextLine();

        while (!edadString.matches("^[0-9]{1,3}$")) {
            System.out.println("Edad: (solo digitos numericos, maximo 3 numeros):");
            edadString = scanner.nextLine();
        }

        edad = Integer.parseInt(edadString);
    }

    public void solicitarSintomas() {
        String sintoma;
        String label = "Sintoma ";
        char deseaIngresarSintoma = 's';

        while (deseaIngresarSintoma == 's' && sintomas.size() != 4) {

            System.out.println(label + (sintomas.size() + 1) + ":");
            sintoma = scanner.nextLine().toLowerCase().trim().replaceAll("\\s+", " ");

            while (!sintoma.matches("^[a-zA-Z\\p{L1}ñ ]+$")) {
                System.out.println(label + (sintomas.size() + 1) + " (solo texto):");
                sintoma = scanner.nextLine().toLowerCase().trim().replaceAll("\\s+", " ");
            }

            sintomas.add(sintoma);

            // Este condicional esta aqui debido a que en la linea de arriba
            // Es añadido un nuevo valor en la ejecucion del ciclo while
            // Por lo tanto, el ciclo while aun esta evaluando que `sintomas.size()` es 3 debido a que no ha finalizado
            if (sintomas.size() != 4) {
                System.out.println("Desea ingresar otro sintoma? S/N:");
                deseaIngresarSintoma = scanner.nextLine().toLowerCase().trim().charAt(0);
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
        while (prescripcionMedica.isEmpty()) {
            System.out.println("Registre la prescripcion medica (no puede ir vacio):");
            prescripcionMedica = scanner.nextLine().trim().replaceAll("\\s+", " ");
        }
    }

    public void determinarSiTieneCovid() {
        if (sintomas.contains("fatiga") && sintomas.contains("fiebre") && sintomas.contains("perdida de olfato y gusto")) {
            tieneCovid = "SI";
        }
    }
}
