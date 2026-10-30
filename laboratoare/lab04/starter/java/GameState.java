package ro.so.android.numeprenume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GameState {

    public final List<Cell> snake = new ArrayList<>(Arrays.asList(
            new Cell(4, 4),
            new Cell(3, 4),
            new Cell(2, 4)
    ));

    public final List<Cell> obstacles = new ArrayList<>();

    // TODO 1: declarati trei campuri private pentru directie.
    //
    //   - un int cu numele dx si valoarea 1
    //     (cu cat se schimba COLOANA la un pas);
    //   - un int cu numele dy si valoarea 0
    //     (cu cat se schimba RANDUL la un pas);
    //   - un boolean cu numele directionChanged si valoarea false
    //     (devine true dupa ce am acceptat o directie noua, pana la pasul
    //     urmator).
    //
    // dx = 1 si dy = 0 inseamna ca sarpele porneste spre dreapta.
    // Exemplu pentru primul camp:
    //     private int dx = 1;


    public GameState() {
        obstacles.add(new Cell(7, 2));
        obstacles.add(new Cell(1, 8));
    }

    public boolean requestDirection(int newDx, int newDy) {
        if (directionChanged) {
            return false;
        }

        // TODO 6: primul if este scris deja, ca exemplu: refuzam a doua
        // schimbare de directie inainte ca sarpele sa faca un pas.
        // Scrieti restul metodei, in ordinea de mai jos.
        //
        //   Regula 2: refuzam sensul opus.
        //     Sensul opus are ambele valori cu semn schimbat.
        //     Exemplu: mergem spre dreapta (1, 0) si se cere stanga (-1, 0).
        //     Scrieti un if care verifica:
        //         newDx == -dx && newDy == -dy
        //     si, daca este adevarat, face return false;
        //
        //   Regula 3: daca se cere aceeasi directie pe care o avem deja,
        //     nu avem ce schimba. Scrieti un if care verifica:
        //         newDx == dx && newDy == dy
        //     si, daca este adevarat, face return false;
        //
        //   Daca am ajuns aici, directia este buna:
        //     - puneti newDx in dx si newDy in dy;
        //     - puneti true in directionChanged;
        //     - inlocuiti linia "return false;" de la final cu return true;

        return false;
    }

    public void update(int cols, int rows) {
        // TODO 2: scrieti metoda in 4 pasi.
        //
        //   Pasul 1. Luati capul (elementul 0 din lista):
        //       Cell head = snake.get(0);
        //
        //   Pasul 2. Calculati coloana si randul noului cap:
        //       int nextCol = (head.col + dx + cols) % cols;
        //       int nextRow = (head.row + dy + rows) % rows;
        //
        //     De ce "+ cols" si "% cols"? Ca sarpele sa apara pe partea
        //     cealalta cand iese din tabla:
        //       - coloana 9, spre dreapta:  (9 + 1 + 10) % 10 = 0
        //       - coloana 0, spre stanga:   (0 - 1 + 10) % 10 = 9
        //       - in mijloc, nimic nu se schimba: (4 + 1 + 10) % 10 = 5
        //     Adunam 10 ca sa nu avem niciodata un numar negativ: in Java,
        //     -1 % 10 da -1, nu 9.
        //
        //   Pasul 3. Adaugati noul cap la inceputul listei si scoateti
        //   ultima celula (coada). Asa sarpele "inainteaza", dar ramane
        //   la fel de lung:
        //       snake.add(0, new Cell(nextCol, nextRow));
        //       snake.remove(snake.size() - 1);
        //
        //     Exemplu: [(4,4), (3,4), (2,4)]  ->  [(5,4), (4,4), (3,4)]
        //
        //   Pasul 4. Am facut pasul, deci putem accepta din nou o directie:
        //       directionChanged = false;
    }
}
