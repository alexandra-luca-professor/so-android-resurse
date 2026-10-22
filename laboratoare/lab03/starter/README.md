# Laborator 3 — Starter: tabla Snake

Lucrați în același proiect din Laboratoarele 1 și 2. Nu creați un proiect nou.

**Pe scurt:** actualizați `so-android-resurse`, copiați fișierele starterului în proiectul vostru, rezolvați TODO-urile în ordine, verificați în Logcat, apoi faceți commit-urile, tag-ul `lab-03` și push.

## Cum luați fișierele

Folderul `so-android-resurse` îl aveți deja de la Laboratorul 2, lângă proiect. Nu îl clonați din nou, doar îl actualizați. Din terminalul proiectului scrieți:

```
cd ../so-android-resurse
git pull
```

Starterul pentru acest laborator este în:

```
so-android-resurse/laboratoare/lab03/starter
```

După `git pull`, închideți terminalul și deschideți unul nou, ca să lucrați din nou în proiectul vostru.

## Ce conține starterul

| Fișier din starter | Unde îl puneți în proiect | Ce faceți |
|---|---|---|
| `java/Cell.java` | pachetul vostru, în `kotlin+java` | fișier nou, complet |
| `java/GameState.java` | pachetul vostru, în `kotlin+java` | fișier nou, cu TODO-uri |
| `java/BoardView.java` | pachetul vostru, în `kotlin+java` | fișier nou, cu TODO-uri |
| `res/layout/activity_game.xml` | `app/src/main/res/layout/` | înlocuiți fișierul existent |

Celelalte fișiere din Lab 2 (`MainActivity`, `GameActivity`, `InstructionsActivity`, `AndroidManifest.xml`, `strings.xml` și celelalte layout-uri) rămân ale voastre. Nu le înlocuiți; în ele faceți doar modificările de la pasul 5.

În `BoardView.java` sunt deja scrise: constructorii, culorile (`Paint`), calculul dimensiunii tablei (`onSizeChanged()`), desenarea grilei (`drawGrid()`), desenarea unei celule (`drawCell()`) și transformarea atingerii în celulă (`cellFromTouch()`). Citiți-le și asigurați-vă că le înțelegeți: le-am explicat în laborator.

Tot în `BoardView.java` este scrisă și `drawSnake()`, cu metodele ei ajutătoare (`drawBodyStroke()`, `drawSpots()`, `drawHead()`, `headAngle()`, `centerX()`, `centerY()`). Ele desenează șarpele final, cu corp continuu, ochi și limbă. Nu trebuie să le modificați și nu trebuie să le explicați linie cu linie.

## Pașii

1. **Copiați fișierele** conform tabelului. Pentru fișierele Java, dați Paste direct pe pachetul vostru în Android Studio.
2. **Schimbați pachetul.** Prima linie din `Cell.java`, `GameState.java` și `BoardView.java` trebuie să fie pachetul vostru, de exemplu `package ro.so.android.popescuandrei;`. La fel, în `activity_game.xml`, la pasul TODO 1, scrieți tag-ul cu pachetul vostru: `<ro.so.android.popescuandrei.BoardView ... />`.
3. **Rezolvați TODO-urile, în ordine.** Până le rezolvați, Android Studio poate afișa cu gri unele importuri, de exemplu `Arrays` sau `Log`, pentru că încă nu sunt folosite. Este normal: nu le ștergeți, le veți folosi în TODO-uri. În Android Studio deschideți View → Tool Windows → TODO:
   - **TODO 1** — `activity_game.xml`: adăugați `BoardView` între titlu și buton. Rulați: trebuie să apară grila de 10 × 10.
   - **TODO 2** — `GameState.java`: lista `snake` cu cele 3 celule.
   - **TODO 3** — `BoardView.java`, metoda `drawSnakeSimple()`: desenați șarpele din pătrate. Rulați: șarpele apare pe rândul 4, cu capul la dreapta.
   - **TODO 4** — `BoardView.java`, metoda `onDraw()`: după ce șarpele din pătrate apare corect, înlocuiți `drawSnakeSimple(canvas)` cu `drawSnake(canvas)`. Rulați: apare șarpele final, în aceleași celule.
   - **TODO 5** — `BoardView.java`, metoda `onTouchEvent()`: scrieți în Logcat celula atinsă.
4. **Tema** — TODO 6 și TODO 7, descrise mai jos.
5. **Modificări în activitățile din Lab 2:**
   - în `MainActivity`, `GameActivity` și `InstructionsActivity` schimbați tag-ul în `"Lab03"`:
     ```java
     private static final String TAG = "Lab03";
     ```
   - în `MainActivity` și `GameActivity`, în `onCreate()`, imediat după blocul `ViewCompat.setOnApplyWindowInsetsListener(...)`, adăugați linia care afișează PID-ul procesului:
     ```java
     Log.d(TAG, getClass().getSimpleName() + " PID=" + android.os.Process.myPid());
     ```

## Tema

Adăugați pe tablă două obstacole.

- **TODO 6** — `GameState.java`: adăugați două celule în lista `obstacles`. Alegeți voi pozițiile: coloana și rândul între 0 și 9, iar celulele să nu fie pe șarpe, adică nu pe (4, 4), (3, 4) sau (2, 4).
- **TODO 7** — `BoardView.java`, metoda `drawObstacles()`: parcurgeți lista `obstacles` cu un `for` și desenați fiecare celulă cu `drawCell(canvas, cell, obstaclePaint)`.

Nu implementați încă mișcarea șarpelui. Aceasta vine la Laboratorul 4.

În README-ul proiectului vostru scrieți pe scurt ce fișiere ați adăugat, ce poziții au obstacolele și ce ați verificat în Logcat.

## Verificare

Rulați aplicația și deschideți Logcat cu filtrul `tag:Lab03`.

La pornire și la apăsarea butonului Start trebuie să apară, printre celelalte mesaje:

```
MainActivity PID=12345
GameActivity PID=12345
```

Numărul diferă de la un telefon la altul, dar trebuie să fie **același** în ambele linii: meniul și jocul rulează în același proces.

Pe ecranul jocului verificați:

- tabla este completă, centrată, cu celule pătrate;
- șarpele ocupă 3 celule pe rândul 4, iar capul, cu ochi, este la (4, 4) și privește spre dreapta;
- cele două obstacole apar în pozițiile alese de voi.

La atingerea unei celule, de exemplu a doua coloană de la stânga și al doilea rând de sus:

```
col=1, row=1
```

La atingerea spațiului din afara tablei:

```
atingere in afara tablei
```

Dacă `col` și `row` par inversate, verificați ordinea în care afișați `cell.col` și `cell.row` în `Log.d()` la TODO 5. Metoda `cellFromTouch()` este deja implementată și nu trebuie modificată.

## Predare

Predarea se face în repository-ul vostru, `nume-prenume-labSO`, la fel ca la Laboratorul 2. Faceți câte un commit după fiecare etapă, nu unul singur la final. Fiecare commit trebuie să conțină o aplicație care pornește.

| Etapa | Mesajul commit-ului |
|---|---|
| Fișierele din starter copiate, tabla apare pe ecran (TODO 1) | `Lab 3: tabla in ecranul jocului` |
| Șarpele desenat (TODO 2, 3 și 4) | `Lab 3: desenarea sarpelui` |
| Atingerea scrisă în Logcat și PID-ul (TODO 5 și pasul 5) | `Lab 3: atingere si PID` |
| Obstacolele (TODO 6 și 7) | `Lab 3: obstacole` |

După fiecare etapă:

```
git status
git add .
git commit -m "Lab 3: desenarea sarpelui"
git push
```

La final, când tema este completă, puneți tag-ul pe ultimul commit și trimiteți-l pe GitHub:

```
git tag lab-03
git push origin lab-03
```

Pe GitHub verificați că la **Commits** apar toate commit-urile, iar la **Tags** apare `lab-03`. Dacă tag-ul nu apare pe GitHub, nu aveți predarea făcută.
