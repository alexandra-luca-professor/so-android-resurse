# Laborator 2 — Starter: meniu și navigare

Lucrați în proiectul din Laboratorul 1. Nu creați un proiect nou.

**Pe scurt:** actualizați repository-ul de resurse, copiați fișierele starterului în proiectul vostru, rezolvați TODO-urile, verificați aplicația, apoi faceți commit-urile, tag-ul `lab-02` și push.

## Cum luați fișierele

### De ce avem un folder separat

Starterele și materialele de laborator sunt în repository-ul `so-android-resurse`, iar proiectul vostru este în repository-ul vostru, `nume-prenume-labSO`. Sunt două repository-uri diferite, fără legătură între ele.

`git pull` aduce modificări numai din repository-ul din care ați clonat folderul. Dacă îl rulați în proiectul vostru, aduce doar ce ați pus voi pe GitHub, nu starterul. De aceea:

- `so-android-resurse` este folderul din care **luați** fișierele;
- proiectul vostru este folderul în care **lucrați** și pe care îl **predați**.

### Prima dată (o singură dată, acum)

În terminalul din Android Studio (View → Tool Windows → Terminal) scrieți:

```
cd ..
git clone https://github.com/alexandra-luca-professor/so-android-resurse.git
```

`cd ..` vă duce din folderul proiectului în folderul de deasupra (de obicei `AndroidStudioProjects`). Acolo apare folderul `so-android-resurse`, lângă proiectul vostru.

După clonare, în `AndroidStudioProjects` veți avea două foldere, unul lângă altul:

```
AndroidStudioProjects/
├── Lab01HelloAndroid/      ← proiectul vostru: aici lucrați și predați
└── so-android-resurse/     ← resursele: de aici doar copiați fișierele
```

Starterul pentru acest laborator este în:

```
so-android-resurse/laboratoare/lab02/starter
```

**Păstrați acest folder.** Nu îl ștergeți, nu îl mutați și nu îl redenumiți: îl folosiți la fiecare laborator următor.

### La fiecare laborator următor

Nu mai clonați din nou. Actualizați folderul existent:

Din terminalul proiectului scrieți:

```
cd ../so-android-resurse
git pull
```

Starterul nou apare în `so-android-resurse/laboratoare/labXX/starter`.

### Atenție

- Nu clonați resursele în folderul proiectului vostru. Terminalul din Android Studio pornește în proiect, de aceea scrieți întâi `cd ..`.
- Nu deschideți `so-android-resurse` în Android Studio. Este doar folderul din care copiați fișierele.
- Nu modificați fișierele din `so-android-resurse`. Copiați-le în proiect și lucrați acolo. Dacă le modificați, `git pull` poate da erori la laboratorul următor.
- După `git clone` sau `git pull`, închideți terminalul și deschideți unul nou. Înainte să folosiți `git add`, `git commit` sau `git push`, verificați că terminalul se află în proiectul vostru: calea afișată la începutul liniei trebuie să se termine cu numele proiectului, de exemplu `...\AndroidStudioProjects\Lab01HelloAndroid>`, nu cu `so-android-resurse`. Dacă nu sunteți siguri, rulați `git remote -v`: adresa afișată trebuie să fie a repository-ului vostru, `nume-prenume-labSO`.

## Tema

Toată tema se face în proiectul vostru, pornind de la acest starter. Tot ce este marcat cu TODO în fișiere este de rezolvat de voi. Tema are două părți:

1. **Meniul și ecranul jocului**, ca în laborator: pașii de mai jos.
2. **Ecranul „Instrucțiuni”**: TODO-urile marcate cu „(tema)”, descrise în secțiunea următoare.

### Partea 1: meniul și ecranul jocului

Toți ați creat proiectul la Laboratorul 1 cu aceleași setări (`Lab01HelloAndroid`, Empty Views Activity, Java). De aceea fișierele de mai jos se potrivesc direct în proiectul vostru. Singura diferență este pachetul `ro.so.android.numeprenume`.

| Fișier din starter | Unde îl puneți în proiect | Ce faceți |
|---|---|---|
| `res/values/strings.xml` | `app/src/main/res/values/` | înlocuiți fișierul existent |
| `res/layout/activity_main.xml` | `app/src/main/res/layout/` | înlocuiți fișierul existent |
| `res/layout/activity_game.xml` | `app/src/main/res/layout/` | fișier nou |
| `AndroidManifest.xml` | `app/src/main/` | înlocuiți fișierul existent |
| `java/MainActivity.java` | pachetul vostru, în `kotlin+java` | înlocuiți fișierul existent |
| `java/GameActivity.java` | pachetul vostru, în `kotlin+java` | fișier nou |
| `res/layout/activity_instructions.xml` | `app/src/main/res/layout/` | fișier nou (partea 2) |
| `java/InstructionsActivity.java` | pachetul vostru, în `kotlin+java` | fișier nou (partea 2) |

1. **Copiați fișierele** conform tabelului. Pentru fișierele Java, copiați fișierul și dați Paste direct pe pachetul vostru în Android Studio.
2. **Verificați prima linie** din `MainActivity.java`, `GameActivity.java` și `InstructionsActivity.java`. Trebuie să fie pachetul vostru, de exemplu `package ro.so.android.popescuandrei;`.
3. **Completați TODO-urile.** În Android Studio deschideți View → Tool Windows → TODO:
   - `MainActivity`: TODO 1 și 2 (butonul Start și Intent-ul), TODO 3 și 4 (`onStop()` și `onRestart()`);
   - `GameActivity`: TODO 1, 2 și 3 (layout-ul, butonul Înapoi și `finish()`), TODO 4 și 5 (`onPause()` și `onDestroy()`);
   - `activity_main.xml` și `activity_game.xml`: butoanele `buttonStart` și `buttonBack`;
   - `AndroidManifest.xml`: declarația pentru `GameActivity`.

   Începeți cu layout-urile: ID-urile butoanelor sunt folosite apoi în Java.
4. **Verificați.** Rulați aplicația și deschideți Logcat cu filtrul `tag:Lab02`:

   La pornire:
   ```
   MainActivity onCreate
   MainActivity onStart
   MainActivity onResume
   ```
   La apăsarea butonului Start:
   ```
   Deschidem GameActivity
   MainActivity onPause
   GameActivity onCreate
   GameActivity onStart
   GameActivity onResume
   MainActivity onStop
   ```
   La apăsarea butonului „Înapoi la meniu”:
   ```
   Inchidem GameActivity
   GameActivity onPause
   MainActivity onRestart
   MainActivity onStart
   MainActivity onResume
   GameActivity onStop
   GameActivity onDestroy
   ```
   Ordinea poate avea mici variații de la un dispozitiv la altul, dar toate aceste mesaje trebuie să apară. La prima creare a unui ecran apar, în această ordine, `onCreate()`, `onStart()` și `onResume()`. La revenirea la un ecran deja creat pot apărea `onRestart()`, `onStart()` și `onResume()`. Între două ecrane, momentul unor pași, de exemplu `MainActivity onStop`, depinde de versiunea Android, de viteza dispozitivului și de animația de tranziție.

Atenție: `git pull` dat în proiectul vostru nu aduce starterul. Starterul îl luați numai din `so-android-resurse`.

### Partea 2: ecranul „Instrucțiuni”

TODO-urile marcate cu „(tema)” și fișierele pentru ecranul „Instrucțiuni”:

- `activity_instructions.xml`: titlul, textul cu regulile și butonul de revenire (TODO 1–3). Textele sunt deja în `strings.xml`: `instructions_title`, `instructions_text` și `back_to_menu`.
- `InstructionsActivity.java`: încărcarea layout-ului, butonul de revenire și `finish()` (TODO 1–3), apoi metodele lifecycle, la fel ca în `GameActivity` (TODO 4).
- `activity_main.xml`: al doilea buton din meniu, `buttonInstructions`, cu textul `open_instructions`.
- `MainActivity.java`: listener-ul pentru `buttonInstructions`, care pornește `InstructionsActivity`.
- `AndroidManifest.xml`: declarația pentru `InstructionsActivity`.

Verificare în Logcat (`tag:Lab02`):

- la apăsarea butonului „Instrucțiuni”: `Deschidem InstructionsActivity`, `MainActivity onPause`, `InstructionsActivity onCreate`, `onStart`, `onResume`, apoi `MainActivity onStop`;
- la revenire: `Inchidem InstructionsActivity`, `InstructionsActivity onPause`, `MainActivity onRestart`, `onStart`, `onResume`, apoi `InstructionsActivity onStop` și `onDestroy`.

În README-ul proiectului vostru descrieți pe scurt fișierele schimbate și verificările făcute.

## Predare

Predarea se face în repository-ul vostru, `nume-prenume-labSO`, nu în `so-android-resurse`. Toate comenzile de mai jos se scriu în terminalul din Android Studio, deschis în proiectul vostru.

### 1. Faceți câte un commit după fiecare etapă

Nu faceți un singur commit la final. După fiecare etapă terminată faceți un commit separat. Fiecare commit trebuie să conțină o aplicație care pornește. Faceți cel puțin patru commit-uri:

| Etapa | Mesajul commit-ului |
|---|---|
| Fișierele din starter copiate, aplicația pornește | `Lab 2: fisierele din starter` |
| Butoanele din layout, Start și Înapoi funcționează | `Lab 2: navigare intre meniu si joc` |
| Metodele lifecycle completate, verificate în Logcat | `Lab 2: metode lifecycle` |
| Ecranul „Instrucțiuni” și butonul din meniu | `Lab 2: ecranul Instructiuni` |

### 2. Comenzile pentru fiecare commit

După fiecare etapă rulați, în ordine:

```
git status
```
Afișează fișierele modificate de la ultimul commit. Verificați că apar doar fișierele la care ați lucrat.

```
git add .
```
Selectează toate modificările afișate de `git status` pentru următorul commit. Punctul înseamnă „tot din folderul curent”.

```
git commit -m "Lab 2: navigare intre meniu si joc"
```
Salvează modificările selectate ca o versiune nouă în istoricul proiectului. Textul dintre ghilimele este mesajul commit-ului: scrieți ce ați făcut în etapa respectivă, folosind mesajele din tabel.

```
git push
```
Trimite commit-ul pe GitHub. Până la `git push`, commit-ul există doar pe calculatorul vostru.

### 3. Tag-ul de predare, o singură dată, la final

Un tag este o etichetă cu nume pusă pe un commit, ca un semn de carte. Tag-ul `lab-02` îmi spune exact ce commit predați pentru Laboratorul 2. Puteți face oricâte commit-uri în timp ce lucrați. Tag-ul se pune o singură dată, pe ultimul commit, când tema este completă.

```
git tag lab-02
```
Pune eticheta `lab-02` pe commit-ul la care vă aflați acum, adică pe ultimul commit.

```
git push origin lab-02
```
Trimite eticheta pe GitHub. `git push` simplu trimite doar commit-urile, nu și tag-urile, de aceea este nevoie de această comandă separată.

La fiecare laborator se procedează la fel, cu un tag nou: `lab-03` la Laboratorul 3, `lab-04` la Laboratorul 4. Tag-urile vechi rămân pe commit-urile lor.

### 4. Verificare pe GitHub

Deschideți repository-ul vostru pe GitHub și verificați:

- la **Commits** apar toate commit-urile, cu mesajele din tabel;
- la **Tags** apare `lab-02`;
- fișierele de pe GitHub sunt cele din varianta finală.

Dacă tag-ul nu apare pe GitHub, nu aveți predarea făcută.
