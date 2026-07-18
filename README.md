# Employee Manager

A JavaFX desktop application for keeping a small, in-memory employee register. The project demonstrates how an `ArrayList` can support basic record management, lookup, reporting, table presentation, and text-file import/export.

## Functionality

The source implements:

- insertion of employees with ID, name, age, department, joining date, and gender;
- duplicate-ID prevention during manual insertion;
- employee search and deletion by ID;
- counts grouped by gender or department, plus age-based counts;
- a JavaFX `TableView` of the current records;
- import of comma-separated records through a file chooser; and
- export of the current list as comma-separated text.

Data is held only for the current application session unless it is exported.

## Stack and data structure

- Java
- JavaFX Controls
- Java Platform Module System
- `ArrayList<employee>` as the employee store
- `Scanner`, `FileChooser`, `FileWriter`, and `PrintWriter` for file I/O

## Repository structure

```text
.
├── README.md
└── ProjectOne/
    ├── build.fxbuild
    ├── out.txt
    └── src/
        ├── module-info.java
        └── application/
            ├── Main.java
            ├── employee.java
            └── application.css
```

`Main.java` contains the JavaFX interface and application workflow. `employee.java` is the record model. The committed `out.txt` is a sample/output artifact; the application does not automatically load it.

## Run

Prerequisites:

- a JDK compatible with JavaFX modules; and
- a JavaFX SDK whose `lib` directory is available locally.

From `ProjectOne` in PowerShell:

```powershell
$env:PATH_TO_FX = "C:\path\to\javafx-sdk\lib"
New-Item -ItemType Directory -Force out | Out-Null
$sources = (Get-ChildItem -Recurse src -Filter *.java).FullName
javac --module-path $env:PATH_TO_FX --add-modules javafx.controls,javafx.fxml -d out $sources
java --module-path "$env:PATH_TO_FX;out" -m jhhj/application.Main
```

Alternatively, configure the JavaFX SDK in a Java IDE, use `ProjectOne/src` as the source root, and run `application.Main`.

## Usage

1. Use **OP** to insert, delete, search, or display employee statistics.
2. Use **Other → Read Screen**, then **Load data**, to choose an input file.
3. The importer skips the first line as a header. Each following line must be:

   ```text
   ID,Name,Age,Department,JoiningDate,Gender
   ```

4. Use **Other → Tabel view** to inspect all records.
5. Use **Other → Save To File** to export the current list.

> [!IMPORTANT]
> The export destination is currently a hard-coded Windows path in `Main.java`. Saving will fail on another machine until that code is made portable.

## Project report

[Project1.pdf](https://github.com/user-attachments/files/19896214/Project1.pdf)

## Future improvements

- Replace the hard-coded export path with a save-file chooser.
- Validate empty fields, numeric ages, and missing gender selections without throwing UI exceptions.
- Correct the table's department property binding and make age filtering use the entered value.
- Add automated tests for parsing, duplicate handling, search, deletion, and statistics.
- Introduce a build tool such as Maven or Gradle for reproducible JavaFX setup.
