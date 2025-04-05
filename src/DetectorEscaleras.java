import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DetectorEscaleras {

    public static String getEscaleras(Carta[] cartas) {
        String mensaje = "No se encontraron escaleras de la misma pinta";
        List<String> todasLasEscaleras = new ArrayList<>(); // Lista para almacenar todas las escaleras encontradas
        StringBuilder escaleraActual = new StringBuilder();
        int contadorEscalera = 1;

        Carta[] cartasOrdenadas = Arrays.copyOf(cartas, cartas.length);
        Arrays.sort(cartasOrdenadas, (c1, c2) -> {
            int nombreComparison = c1.getNombre().ordinal() - c2.getNombre().ordinal();
            if (nombreComparison == 0) {
                return c1.getPinta().ordinal() - c2.getPinta().ordinal();
            }
            return nombreComparison;
        });

        for (int i = 0; i < cartasOrdenadas.length - 1; i++) {
            if (cartasOrdenadas[i].getNombre().ordinal() + 1 == cartasOrdenadas[i + 1].getNombre().ordinal() &&
                cartasOrdenadas[i].getPinta() == cartasOrdenadas[i + 1].getPinta()) {
                if (contadorEscalera == 1) {
                    escaleraActual.append(cartasOrdenadas[i].getNombre());
                }
                escaleraActual.append(", ").append(cartasOrdenadas[i + 1].getNombre());
                contadorEscalera++;
            } else {
                if (contadorEscalera >= 2) {
                    escaleraActual.append(" de ").append(cartasOrdenadas[i].getPinta());
                    todasLasEscaleras.add(escaleraActual.toString()); // Agregar la escalera a la lista
                }
                contadorEscalera = 1;
                escaleraActual.setLength(0);
            }
        }
        if (contadorEscalera >= 2) {
            escaleraActual.append(" de ").append(cartasOrdenadas[cartasOrdenadas.length - 1].getPinta());
            todasLasEscaleras.add(escaleraActual.toString()); // Agregar la última escalera a la lista
        }

        if (!todasLasEscaleras.isEmpty()) {
            StringBuilder mensajeFinal = new StringBuilder("Se encontraron las siguientes escaleras de la misma pinta:\n");
            for (String escalera : todasLasEscaleras) {
                mensajeFinal.append(escalera).append("\n");
            }
            mensaje = mensajeFinal.toString();
        }

        String sumaCartasSueltas = CalculadorCartasSueltas.calcularSumaCartasSueltas(cartas);
        return mensaje + "\n" + sumaCartasSueltas;
    }
}