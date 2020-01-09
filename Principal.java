//Practica 3. Algoritmo Voraz
//Por Jaime De Román Monleón

import java.util.*;

public class Principal {

    public static ArrayList<Objeto> llenarInventario(ArrayList<Objeto> candidatos, Inventario inventario) {
        int i;
        Objeto candidato;
        ArrayList<Objeto> listaResultado = new ArrayList<Objeto>();
        for (i = 0; i < candidatos.size(); i++) {
            candidato = seleccionarCandidato(candidatos);
            if (esCandidatoFactible(candidato, inventario))
                listaResultado.add(candidato);
        }
        return listaResultado;
    }

    private static Objeto seleccionarCandidato(ArrayList<Objeto> candidatos) {
        Objeto mejorCandidato = candidatos.get(0);
        int index = 0;
        for (int i = 1; i < candidatos.size(); i++) {
            if (candidatos.get(i).getValor() > mejorCandidato.getValor()) {
                mejorCandidato = candidatos.get(i);
                index = i;
            }
        }
        candidatos.remove(index);
        return mejorCandidato;
    }

    private static boolean esCandidatoFactible(Objeto candidato, Inventario inventario) {
        int i, j, x, y, rx = 0, ry = 0;
        boolean available = false;
        for (x = 0; x < inventario.getN() - candidato.getAlto() + 1; x++) {
            for (y = 0; y < inventario.getN() - candidato.getAncho() + 1; y++) {
                available = true;
                for (i = x; i < candidato.getAlto() + x; i++) {
                    for (j = y; j < candidato.getAncho() + y; j++) {
                        if (inventario.getCelda(i, j) != -1) {
                            available = false;
                            i = candidato.getAlto() + x;
                            break;
                        }
                    }
                }
                if (available) {
                    rx = x;
                    ry = y;
                    x = inventario.getN();
                    break;
                }
            }
        }
        if (available) {
            for (i = rx; i < candidato.getAlto() + rx; i++) {
                for (j = ry; j < candidato.getAncho() + ry; j++) {
                    inventario.setCelda(i, j, candidato.getId());
                }
            }
        }
        return available;
    }
}
