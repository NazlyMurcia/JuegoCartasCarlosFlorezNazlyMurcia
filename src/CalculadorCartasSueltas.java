import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CalculadorCartasSueltas {

    public static String calcularSumaCartasSueltas(Carta[] cartas) {
        Carta[] cartasOrdenadas = Arrays.copyOf(cartas, cartas.length);
        Arrays.sort(cartasOrdenadas, (c1, c2) -> {
            int nombreComparison = c1.getNombre().ordinal() - c2.getNombre().ordinal();
            if (nombreComparison == 0) {
                return c1.getPinta().ordinal() - c2.getPinta().ordinal();
            }
            return nombreComparison;
        });

        List<Integer> cartasEnEscalera = new ArrayList<>();
        for (int i = 0; i < cartasOrdenadas.length - 1; i++) {
            if (cartasOrdenadas[i].getNombre().ordinal() + 1 == cartasOrdenadas[i + 1].getNombre().ordinal() &&
                cartasOrdenadas[i].getPinta() == cartasOrdenadas[i + 1].getPinta()) {
                if (!cartasEnEscalera.contains(i)) cartasEnEscalera.add(i);
                if (!cartasEnEscalera.contains(i + 1)) cartasEnEscalera.add(i + 1);
            }
        }

        List<Integer> cartasEnGrupo = new ArrayList<>();
        for (int i = 0; i < cartasOrdenadas.length - 1; i++) {
            if (cartasOrdenadas[i].getNombre().ordinal() == cartasOrdenadas[i + 1].getNombre().ordinal()) {
                cartasEnGrupo.add(i);
                cartasEnGrupo.add(i + 1);
            }
        }

        int sumaCartasSueltas = 0;
        StringBuilder cartasSueltas = new StringBuilder();

        for (int i = 0; i < cartasOrdenadas.length; i++) {
            if (!cartasEnEscalera.contains(i) && !cartasEnGrupo.contains(i)) {
                int valorCarta = cartasOrdenadas[i].getNombre().ordinal() + 1;
                // Aplicar la regla de que las figuras valen 10
                if (valorCarta == 0 + 1) { // Si el valor ordinal es 0 (As)
                    valorCarta = 10;
                }
                // Aplicar la regla de que las figuras valen 10
                if (valorCarta > 10) {
                    valorCarta = 10;
                }

                sumaCartasSueltas += valorCarta;
                cartasSueltas.append(cartasOrdenadas[i].getNombre()).append(" de ").append(cartasOrdenadas[i].getPinta()).append(", ");
            }
        }

        if (sumaCartasSueltas > 0) {
            if (cartasSueltas.length() > 2) {
                cartasSueltas.delete(cartasSueltas.length() - 2, cartasSueltas.length());
            }
            return "Cartas sueltas: " + cartasSueltas.toString() + "\nSuma de cartas sueltas: " + sumaCartasSueltas;
        } else {
            return "¡GANADOR! No hay cartas sueltas.";
        }
    }
}