# Laborator 4 — Starter: input și mișcarea șarpelui

Lucrați în același proiect din Laboratoarele 1, 2 și 3. Nu creați un proiect nou.

**Pe scurt:** actualizați `so-android-resurse`, copiați fișierele starterului în proiect, rezolvați TODO-urile în ordine, verificați în Logcat, apoi faceți commit-urile, tag-ul `lab-04` și push.

**Ce obțineți la final:** șarpele se mișcă singur, câte o celulă la fiecare 300 ms. Îi schimbați direcția cu butoanele ▲ ▼ ◀ ▶. Când iese printr-o margine, apare pe partea opusă. Când apăsați Home, se oprește.

## Cum funcționează, pe scurt

Sunt două lucruri separate, care nu se apelează unul pe altul:

```
1. Apăsați un buton   →  GameActivity  →  BoardView.changeDirection()  →  GameState.requestDirection()
                         (se schimbă DOAR direcția: dx și dy)

2. La fiecare 300 ms  →  GameLoop (tick)  →  BoardView.updateGame()  →  GameState.update()  →  invalidate()  →  onDraw()
                         (șarpele se mută cu o celulă în direcția salvată, apoi se redesenează)
```

Butonul nu mută șarpele. El doar schimbă direcția. Mutarea o face game loop-ul, la următorul tick.

Câteva cuvinte pe care le folosim:

| Cuvânt | Ce înseamnă |
|---|---|
| `dx`, `dy` | Cu cât se schimbă coloana (`dx`) și rândul (`dy`) la un pas. Dreapta = (1, 0), stânga = (-1, 0), sus = (0, -1), jos = (0, 1). |
| tick | Un singur pas al jocului: șarpele se mută cu o celulă. |
| game loop | Codul care repetă tick-ul la un interval fix (aici 300 ms). |
| `Handler` | Programează un cod să ruleze peste un anumit timp, pe firul principal. |
| `invalidate()` | Îi spune lui Android: „tabla s-a schimbat, redesenează-o”. Android apelează apoi `onDraw()`. |

## Cum luați fișierele

Din terminalul proiectului scrieți:

```
cd ../so-android-resurse
git pull
```

Starterul pentru acest laborator este în:

```
so-android-resurse/laboratoare/lab04/starter
```

După `git pull`, închideți terminalul și deschideți unul nou, ca să lucrați din nou în proiectul vostru.

## Ce conține starterul

| Fișier din starter | Unde îl puneți în proiect | Ce faceți |
|---|---|---|
| `java/GameLoop.java` | pachetul vostru, în `kotlin+java` | fișier **nou**, complet. Doar îl citiți. |
| `java/GameState.java` | pachetul vostru, în `kotlin+java` | **înlocuiți** fișierul vostru. Are TODO-uri. |
| `java/BoardView.java` | pachetul vostru, în `kotlin+java` | **înlocuiți** fișierul vostru. Are TODO-uri. |
| `java/GameActivity.java` | pachetul vostru, în `kotlin+java` | **înlocuiți** fișierul vostru. Are TODO-uri. |
| `res/layout/activity_game.xml` | `app/src/main/res/layout/` | **înlocuiți** fișierul vostru. Complet. |
| `res/values/strings_lab04.xml` | `app/src/main/res/values/` | fișier **nou**, lângă `strings.xml`. Complet. |

`Cell.java`, `MainActivity`, `InstructionsActivity`, `AndroidManifest.xml` și `strings.xml` rămân ale voastre. Nu le înlocuiți.

**De ce înlocuim `GameState`, `BoardView` și `GameActivity`?** Ca să nu căutați voi locul fiecărei linii noi. Fișierele din starter conțin deja tot ce ați făcut la Lab 3 (șarpele, obstacolele, atingerea în Logcat, PID-ul). Obstacolele sunt aceleași pentru toți: (7, 2) și (1, 8). În plus, `BoardView` are o reparație: când șarpele trece prin margine, nu mai apare o linie peste toată tabla.

## Pașii

### 1. Copiați fișierele

Copiați fișierele conform tabelului. Pentru fișierele Java, le copiați din folderul `starter/java` și dați Paste direct pe pachetul vostru în Android Studio. Când vă întreabă dacă suprascrieți fișierul, alegeți **Overwrite**.

Pentru `strings_lab04.xml`: dați Paste pe folderul `res/values`. Trebuie să aveți în acel folder **atât** `strings.xml`, **cât și** `strings_lab04.xml`.

### 2. Schimbați pachetul

- Prima linie din `GameLoop.java`, `GameState.java`, `BoardView.java` și `GameActivity.java` trebuie să fie pachetul vostru, de exemplu `package ro.so.android.popescuandrei;`.
- În `activity_game.xml`, tag-ul tablei trebuie să aibă pachetul vostru: `<ro.so.android.popescuandrei.BoardView ... />`.

### 3. Schimbați tag-ul în `"Lab04"`

În `MainActivity` și `InstructionsActivity` schimbați:

```java
private static final String TAG = "Lab04";
```

În `GameActivity`, `BoardView` și `GameLoop` tag-ul este deja `"Lab04"`.

### 4. Rezolvați TODO-urile, în ordine

Deschideți View → Tool Windows → TODO ca să le vedeți pe toate. Fiecare TODO are în comentariu, pas cu pas, ce trebuie scris.

**Până rezolvați TODO 1, `GameState.java` apare cu roșu**, pentru că metoda `requestDirection()` folosește câmpul `directionChanged`, care încă nu există. Este normal. Rezolvați întâi TODO 1.

#### Etapa A — șarpele se mișcă singur (TODO 1–5)

- **TODO 1** — `GameState.java`: cele trei câmpuri `dx`, `dy` și `directionChanged`.
- **TODO 2** — `GameState.java`, metoda `update()`: mutați șarpele cu o celulă.
- **TODO 3** — `BoardView.java`, metoda `updateGame()`: apelați `state.update(COLS, ROWS)` și apoi `invalidate()`.
- **TODO 4** — `GameActivity.java`, în `onCreate()`: creați `gameLoop` și setați intervalul la 300 ms.
- **TODO 5** — `GameActivity.java`: `gameLoop.start()` în `onResume()` și `gameLoop.stop()` în `onPause()`.

Faceți TODO 4 **înainte** de TODO 5. Dacă apelați `gameLoop.start()` fără să fi creat `gameLoop`, aplicația se închide cu `NullPointerException`.

**Rulați.** Apăsați Start joc. Șarpele trebuie să meargă singur spre dreapta, să iasă prin marginea din dreapta și să reapară în stânga, pe același rând. Butoanele încă nu fac nimic. Este normal.

#### Etapa B — butoanele de direcție (TODO 6–8)

- **TODO 6** — `GameState.java`, metoda `requestDirection()`: refuzați sensul opus și direcția identică; altfel salvați direcția nouă.
- **TODO 7** — `BoardView.java`, metoda `changeDirection()`: trimiteți direcția la `GameState`.
- **TODO 8** — `GameActivity.java`: butoanele Jos, Stânga și Dreapta. Butonul Sus este scris deja, ca exemplu.

**Rulați.** Verificați:

- ▲ ▼ ◀ ▶ schimbă direcția șarpelui;
- dacă șarpele merge spre dreapta și apăsați ◀, nu se întâmplă nimic (sensul opus este refuzat);
- șarpele trece prin toate cele patru margini și reapare pe partea opusă.

### 5. Tema — TODO 9

Pe ecran, deasupra săgeților, sunt deja două butoane: **Lent** și **Rapid**. Faceți-le să funcționeze, în `GameActivity.java`, la TODO 9:

- luați butoanele cu `findViewById(R.id.buttonSlow)` și `findViewById(R.id.buttonFast)`, la fel ca butoanele de direcție;
- **Lent** apelează `gameLoop.setIntervalMs(300L);`
- **Rapid** apelează `gameLoop.setIntervalMs(150L);`

Rezolvați TODO 9 după TODO 4, adică după ce `gameLoop` a fost creat.

**Rulați.** La Rapid, șarpele se mișcă vizibil mai repede. La Lent revine la viteza inițială.

În README-ul proiectului vostru scrieți pe scurt ce fișiere ați adăugat sau înlocuit și ce ați verificat în Logcat.

## Verificare în Logcat

Rulați aplicația și deschideți Logcat cu filtrul `tag:Lab04`.

**1. La intrarea în joc** trebuie să apară, printre celelalte mesaje:

```
VITEZA: interval=300 ms
START
TICK 1, interval=300 ms
TICK 2, interval=300 ms
TICK 3, interval=300 ms
```

`START` trebuie să apară **o singură dată**.

**2. Apăsați Home.** Trebuie să apară `STOP`, iar mesajele `TICK` se opresc.

**3. Reveniți în aplicație.** Trebuie să apară **un singur** `START`, iar `TICK` continuă numerotarea de unde rămăsese (de exemplu `TICK 18`, `TICK 19`...). Șarpele nu se mișcă mai repede decât înainte.

Dacă ieșiți cu **Înapoi la meniu** și apăsați din nou **Start joc**, ecranul jocului se creează din nou, deci `TICK` pornește iar de la 1. Și asta este corect.

**4. Apăsați Rapid.** Trebuie să apară `VITEZA: interval=150 ms`, iar apoi mesajele `TICK ..., interval=150 ms`, de două ori mai dese. Apăsați Lent: revine la `interval=300 ms`.

## Probleme frecvente

| Ce vedeți | Cauza probabilă |
|---|---|
| Aplicația se închide la Start joc, cu `NullPointerException` la `gameLoop` | `gameLoop.start()` este apelat, dar `gameLoop` nu a fost creat. Verificați TODO 4. |
| Aplicația se închide la Start joc, cu `ClassNotFoundException` / `Error inflating class` | În `activity_game.xml` tag-ul tablei nu are pachetul vostru (pasul 2). |
| Aplicația nu compilează, cu o eroare de tipul `resource string/arrow_up not found` | Lipsește `strings_lab04.xml` din `res/values` (pasul 1). |
| Șarpele nu se mișcă, dar în Logcat apar `TICK`-uri | Lipsește `invalidate()` în `updateGame()` (TODO 3) sau `update()` este goală (TODO 2). |
| Șarpele nu se mișcă și nu apare nici `START` | Lipsește `gameLoop.start()` din `onResume()` (TODO 5). |
| Șarpele iese din tablă și nu mai revine | Lipsește `+ cols` / `% cols` (sau `+ rows` / `% rows`) din formulă (TODO 2). |
| Șarpele se lungește la fiecare pas | Lipsește `snake.remove(snake.size() - 1);` (TODO 2). |
| Butoanele nu schimbă direcția | `changeDirection()` încă face `return false;` (TODO 7) sau `requestDirection()` nu salvează direcția (TODO 6). |
| Se poate întoarce direct în sens opus | Lipsește verificarea `newDx == -dx && newDy == -dy` (TODO 6). |
| După o apăsare, butoanele nu mai merg deloc | Lipsește `directionChanged = false;` la finalul lui `update()` (TODO 2). |
| După Home și revenire, șarpele merge mai repede | Lipsește `gameLoop.stop()` din `onPause()` (TODO 5). |

## Predare

Predarea se face în repository-ul vostru, `nume-prenume-labSO`, la fel ca la laboratoarele anterioare. Faceți câte un commit după fiecare etapă. Fiecare commit trebuie să conțină o aplicație care pornește.

| Etapa | Mesajul commit-ului |
|---|---|
| Fișierele copiate, pachetul și tag-ul schimbate, TODO 1 rezolvat (aplicația pornește) | `Lab 4: fisierele din starter` |
| Șarpele se mișcă singur (TODO 2–5) | `Lab 4: miscarea sarpelui` |
| Butoanele de direcție (TODO 6–8) | `Lab 4: butoane de directie` |
| Butoanele Lent și Rapid (TODO 9) | `Lab 4: viteza` |

După fiecare etapă:

```
git status
git add .
git commit -m "Lab 4: miscarea sarpelui"
git push
```

La final, când tema este completă, puneți tag-ul pe ultimul commit și trimiteți-l pe GitHub:

```
git tag lab-04
git push origin lab-04
```

Pe GitHub verificați că la **Commits** apar toate commit-urile, iar la **Tags** apare `lab-04`. Dacă tag-ul nu apare pe GitHub, nu aveți predarea făcută.
