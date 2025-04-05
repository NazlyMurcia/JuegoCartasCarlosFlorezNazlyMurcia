import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DetectorEscaleras {

    public enum Grupo {
        VACIO,
        NON,
        PAR,
        TERNA,
        CUARTA,
        QUINTA,
        SEXTA,
        SEPTIMA,
        OCTAVA,
        NOVENA,
        DECIMA
    }
    public static String getEscaleras(Carta[] cartas) {
        String mensaje = "No se encontraron escaleras de la misma pinta";
        List<String> todasLasEscaleras = new ArrayList<>(); //almacenamiento de escaleras
        StringBuilder escaleraActual = new StringBuilder();
        int contadorEscalera = 1;

        Carta[] cartasOrdenadas = Arrays.copyOf(cartas, cartas.length);
        Arrays.sort(cartasOrdenadas, (c1, c2) -> {
            // Primero comparar por pinta
            int pintaComparison = c1.getPinta().ordinal() - c2.getPinta().ordinal();
            if (pintaComparison != 0) {
                return pintaComparison;
            }
            // Si las pintas son iguales, comparar por valor
            return c1.getNombre().ordinal() - c2.getNombre().ordinal();
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
                    todasLasEscaleras.add(clasificarEscalera(escaleraActual.toString(), contadorEscalera));
                }
                contadorEscalera = 1;
                escaleraActual.setLength(0);
            }
        }
        if (contadorEscalera >= 2) {
            escaleraActual.append(" de ").append(cartasOrdenadas[cartasOrdenadas.length - 1].getPinta());
            todasLasEscaleras.add(clasificarEscalera(escaleraActual.toString(), contadorEscalera));
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
    private static String clasificarEscalera(String escalera, int longitud) {
        Grupo grupo = Grupo.VACIO;
        switch (longitud) {
            case 2:
                grupo = Grupo.PAR;
                break;
            case 3:
                grupo = Grupo.TERNA;
                break;
            case 4:
                grupo = Grupo.CUARTA;
                break;
            case 5:
                grupo = Grupo.QUINTA;
                break;
            case 6:
                grupo = Grupo.SEXTA;
                break;
            case 7:
                grupo = Grupo.SEPTIMA;
                break;
            case 8:
                grupo = Grupo.OCTAVA;
                break;
            case 9:
                grupo = Grupo.NOVENA;
                break;
            case 10:
                grupo = Grupo.DECIMA;
                break;
        }
        return grupo.toString() + ": " + escalera;

    }}