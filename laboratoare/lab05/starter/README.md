# Laborator 5 — Reguli și prima partidă

Lucrați în același proiect din Laboratoarele 1–4. Codul de mai jos se adaugă peste **Laboratorul 4 rezolvat**: șarpele trebuie să se miște, să schimbe direcția cu butoanele și să aibă butoanele Lent și Rapid.

**Ce obțineți:** hrana apare pe tablă, șarpele crește când mănâncă, scorul se vede sub titlu, iar jocul se termină la perete, la obstacol sau când șarpele intră în propriul corp. La final apare un dialog cu scorul și butoanele Restart și Meniu. Mâncarea și finalul au sunet.

## Fișiere noi

| Fișier | Unde îl puneți |
|---|---|
| `java/GameSounds.java` | pachetul vostru, în `kotlin+java` |
| `res/layout/dialog_game_over.xml` | `app/src/main/res/layout/` |
| `res/values/strings_lab05.xml` | `app/src/main/res/values/`, lângă `strings.xml` |
| `res/values/colors_lab05.xml` | `app/src/main/res/values/`, lângă `colors.xml` |

În `GameSounds.java` schimbați prima linie cu pachetul vostru.

Restul laboratorului înseamnă modificări în fișierele voastre: `GameState`, `BoardView`, `GameActivity` și `activity_game.xml`. Pașii de mai jos arată exact ce adăugați și unde.

## Pasul 1. Tag-ul

În toate clasele care au `TAG`, schimbați în:

```java
private static final String TAG = "Lab05";
```

## Pasul 2. `GameState.java` — starea partidei

Adăugați importul:

```java
import java.util.Random;
```

În clasă, la început, adăugați starea partidei:

```java
// RUNNING cat timp se joaca, GAME_OVER la pierdere, WON cand tabla e plina.
public enum Status {
    RUNNING,
    GAME_OVER,
    WON
}
```

Lângă câmpurile existente adăugați:

```java
// Celula cu hrana. Este null doar cand tabla e plina.
public Cell food;

// Dimensiunile tablei, primite in constructor.
private final int cols;
private final int rows;
private final Random random = new Random();

private int score;
private Status status;
```

Câmpurile `dx`, `dy` și `directionChanged` rămân, dar **fără valori inițiale** (`private int dx;`), pentru că le setăm în `reset()`. Listele `snake` și `obstacles` rămân, dar le creați goale: `new ArrayList<>()`.

## Pasul 3. `GameState.java` — constructorul și `reset()`

Înlocuiți constructorul `GameState()` cu:

```java
// Constructorul pastreaza dimensiunile tablei si pregateste prima partida.
public GameState(int cols, int rows) {
    this.cols = cols;
    this.rows = rows;
    reset();
}

// reset() pune toate valorile ca la inceputul partidei.
// O folosim la prima partida si la fiecare Restart.
public void reset() {
    snake.clear();
    snake.add(new Cell(4, 4));
    snake.add(new Cell(3, 4));
    snake.add(new Cell(2, 4));

    obstacles.clear();
    obstacles.add(new Cell(7, 2));
    obstacles.add(new Cell(1, 8));

    dx = 1;
    dy = 0;
    directionChanged = false;
    score = 0;
    status = Status.RUNNING;

    spawnFood();
}

public int getScore() {
    return score;
}

public Status getStatus() {
    return status;
}
```

În `reset()` puneți obstacolele voastre.

## Pasul 4. `GameState.java` — `requestDirection()`

La începutul metodei voastre `requestDirection()` adăugați:

```java
// Dupa final, butoanele nu mai schimba nimic.
if (status != Status.RUNNING) {
    return false;
}
```

## Pasul 5. `GameState.java` — hrana

Adăugați metodele:

```java
// Alege hrana pe o celula libera: nu pe sarpe si nu pe obstacole.
private void spawnFood() {
    List<Cell> free = collectFreeCells();

    // Nicio celula libera: tabla e plina, deci jucatorul a castigat.
    if (free.isEmpty()) {
        food = null;
        status = Status.WON;
        return;
    }

    food = free.get(random.nextInt(free.size()));
}

// Parcurge toata tabla si face lista celulelor libere.
private List<Cell> collectFreeCells() {
    List<Cell> free = new ArrayList<>();

    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            if (!contains(snake, snake.size(), col, row)
                    && !contains(obstacles, obstacles.size(), col, row)) {
                free.add(new Cell(col, row));
            }
        }
    }

    return free;
}

// Verifica daca celula (col, row) se afla in primele limit elemente ale listei.
private boolean contains(List<Cell> cells, int limit, int col, int row) {
    for (int i = 0; i < limit; i++) {
        Cell cell = cells.get(i);
        if (cell.col == col && cell.row == row) {
            return true;
        }
    }
    return false;
}
```

## Pasul 6. `GameState.java` — regulile din `update()`

`update()` nu mai primește parametri: dimensiunile sunt acum în câmpurile `cols` și `rows`. Înlocuiți metoda cu:

```java
public void update() {
    // Daca partida s-a terminat, nu mai mutam nimic.
    if (status != Status.RUNNING) {
        return;
    }

    Cell head = snake.get(0);

    // Fara modulo: marginea devine perete.
    int nextCol = head.col + dx;
    int nextRow = head.row + dy;

    // Regula 1: peretele.
    if (nextCol < 0 || nextCol >= cols || nextRow < 0 || nextRow >= rows) {
        status = Status.GAME_OVER;
        return;
    }

    // Regula 2: obstacolele.
    if (contains(obstacles, obstacles.size(), nextCol, nextRow)) {
        status = Status.GAME_OVER;
        return;
    }

    boolean eating = food != null
            && nextCol == food.col && nextRow == food.row;

    // Regula 3: propriul corp. Daca nu mancam, coada pleaca in acest pas,
    // deci nu verificam ultima celula. eating ? a : b este un if cu else.
    int limit = eating ? snake.size() : snake.size() - 1;
    if (contains(snake, limit, nextCol, nextRow)) {
        status = Status.GAME_OVER;
        return;
    }

    snake.add(0, new Cell(nextCol, nextRow));

    // Daca am mancat, nu scoatem coada: sarpele creste.
    if (eating) {
        score++;
        spawnFood();
    } else {
        snake.remove(snake.size() - 1);
    }

    directionChanged = false;
}
```

## Pasul 7. `BoardView.java`

Modelul primește acum dimensiunile tablei:

```java
private final GameState state = new GameState(COLS, ROWS);
```

Adăugați culoarea hranei, lângă celelalte `Paint`:

```java
private final Paint foodPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
```

și, la finalul metodei `init()`:

```java
foodPaint.setColor(Color.parseColor("#E63946"));
foodPaint.setStyle(Paint.Style.FILL);
```

În `onDraw()`, desenați hrana după grilă și înainte de șarpe:

```java
drawGrid(canvas);
drawFood(canvas);
drawSnake(canvas);
drawObstacles(canvas);
```

Adăugați metoda:

```java
// Hrana este un cerc rosu in centrul celulei.
private void drawFood(Canvas canvas) {
    if (state.food == null) {
        return;
    }

    canvas.drawCircle(
            centerX(state.food),
            centerY(state.food),
            cellSize * 0.32f,
            foodPaint
    );
}
```

În `updateGame()`, apelul devine `state.update();`. Adăugați apoi metodele prin care `GameActivity` află ce se întâmplă:

```java
public void restartGame() {
    state.reset();
    invalidate();
}

public int getScore() {
    return state.getScore();
}

public GameState.Status getStatus() {
    return state.getStatus();
}

public boolean isGameOver() {
    return state.getStatus() != GameState.Status.RUNNING;
}
```

## Pasul 8. `activity_game.xml` — scorul

Sub titlu adăugați:

```xml
<TextView
    android:id="@+id/textScore"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="8dp"
    android:gravity="center"
    android:textSize="20sp" />
```

## Pasul 9. `GameActivity.java`

Importuri noi:

```java
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
```

Câmpuri noi:

```java
private TextView textScore;
private final GameSounds sounds = new GameSounds();
```

În `onCreate()`, după `boardView = findViewById(...)`:

```java
textScore = findViewById(R.id.textScore);
```

Acțiunea primită de `GameLoop` verifică acum și finalul. Înlocuiți `new GameLoop(...)` cu:

```java
gameLoop = new GameLoop(() -> {
    int scoreBefore = boardView.getScore();

    boardView.updateGame();
    refreshGameUi();

    // Scorul a crescut: am mancat.
    if (boardView.getScore() > scoreBefore) {
        sounds.eat();
    }

    // Partida s-a terminat: oprim bucla, ton de final si dialogul.
    if (boardView.isGameOver()) {
        gameLoop.stop();
        Log.d(TAG, "FINAL: " + boardView.getStatus() + ", scor=" + boardView.getScore());
        sounds.gameOver(boardView.getStatus() == GameState.Status.WON);
        showGameOverDialog();
    }
});
```

La finalul lui `onCreate()` afișați scorul inițial:

```java
refreshGameUi();
```

Adăugați metodele:

```java
// Textul de sub titlu, dupa starea partidei.
private void refreshGameUi() {
    int score = boardView.getScore();
    GameState.Status status = boardView.getStatus();

    if (status == GameState.Status.GAME_OVER) {
        textScore.setText(getString(R.string.game_over, score));
    } else if (status == GameState.Status.WON) {
        textScore.setText(getString(R.string.game_won, score));
    } else {
        textScore.setText(getString(R.string.score, score));
    }
}

// Restart: oprim bucla, resetam partida si pornim aceeasi bucla.
// Nu apelam setIntervalMs(), deci viteza aleasa ramane aceeasi.
private void restartGame() {
    Log.d(TAG, "RESTART");
    gameLoop.stop();
    boardView.restartGame();
    refreshGameUi();
    gameLoop.start();
}

// Dialogul de final, peste ecranul jocului.
private void showGameOverDialog() {
    boolean won = boardView.getStatus() == GameState.Status.WON;

    View content = getLayoutInflater().inflate(R.layout.dialog_game_over, null);

    TextView dialogTitle = content.findViewById(R.id.dialogTitle);
    TextView dialogSubtitle = content.findViewById(R.id.dialogSubtitle);
    TextView dialogScore = content.findViewById(R.id.dialogScore);

    dialogTitle.setText(won ? R.string.won_title : R.string.game_over_title);
    dialogSubtitle.setText(won ? R.string.game_over_reason_won : R.string.game_over_reason_lost);
    dialogScore.setText(String.valueOf(boardView.getScore()));

    AlertDialog dialog = new MaterialAlertDialogBuilder(this)
            .setView(content)
            .setCancelable(false)
            .create();

    Button dialogButtonRestart = content.findViewById(R.id.dialogButtonRestart);
    Button dialogButtonMenu = content.findViewById(R.id.dialogButtonMenu);

    dialogButtonRestart.setOnClickListener(v -> {
        dialog.dismiss();
        restartGame();
    });

    dialogButtonMenu.setOnClickListener(v -> {
        dialog.dismiss();
        finish();
    });

    dialog.show();
}
```

În `onResume()` și `onPause()`, sunetele urmează bucla, iar bucla nu mai pornește după final:

```java
@Override
protected void onResume() {
    super.onResume();
    Log.d(TAG, getClass().getSimpleName() + " onResume");
    sounds.start();
    if (!boardView.isGameOver()) {
        gameLoop.start();
    }
}

@Override
protected void onPause() {
    gameLoop.stop();
    sounds.stop();
    super.onPause();
    Log.d(TAG, getClass().getSimpleName() + " onPause");
}
```

## Verificare în Logcat

Filtrul `tag:Lab05`. Lăsați șarpele să meargă spre dreapta, fără să apăsați butoane:

```
START
TICK 1, interval=300 ms
...
TICK 6, interval=300 ms
STOP
FINAL: GAME_OVER, scor=0
```

Pe ecran apare dialogul de final. La **Restart** apar `RESTART`, `STOP`, `START`, iar jocul pornește din nou, cu aceeași viteză.

## Predare

```
git add .
git commit -m "Lab 5: reguli si prima partida completa"
git push
git tag lab-05
git push origin lab-05
```
