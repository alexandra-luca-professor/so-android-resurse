package ro.so.android.numeprenume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GameState {

    // TODO 2: inlocuieste lista goala de mai jos cu o lista care contine
    // cele 3 celule ale sarpelui, in aceasta ordine:
    //   new Cell(4, 4)  - capul
    //   new Cell(3, 4)  - corpul
    //   new Cell(2, 4)  - coada
    // Folositi new ArrayList<>(Arrays.asList(...)), ca lista sa poata fi
    // modificata la Lab 4.
    public final List<Cell> snake = new ArrayList<>();

    public final List<Cell> obstacles = new ArrayList<>();

    // TODO 6 (tema): adauga in lista obstacles doua celule.
    // Alegeti voi pozitiile, cu doua conditii:
    //   - col si row sunt intre 0 si 9;
    //   - nu sunt pe sarpe: (4, 4), (3, 4), (2, 4).
    // Scrieti un constructor GameState() si, in el, adaugati obstacolele, de exemplu:
    //   public GameState() {
    //       obstacles.add(new Cell(..., ...));
    //       obstacles.add(new Cell(..., ...));
    //   }
}
