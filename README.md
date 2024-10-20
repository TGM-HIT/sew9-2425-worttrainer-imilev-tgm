# Worttrainer ReadMe

## Autor
**Ivan Milev am 20.10.2024**

## Projektbeschreibung

Der **Worttrainer** ist eine Anwendung, die Kindern hilft, die Rechtschreibung von Wörtern zu trainieren. Das Programm zeigt ein Bild an, und die Kinder sollen das passende Wort dazu eingeben. Die Eingabe wird mit der korrekten Schreibweise verglichen, und entsprechende Rückmeldungen werden gegeben. Darüber hinaus wird eine Statistik geführt, die die Anzahl richtiger und falscher Versuche dokumentiert. Das Programm speichert den aktuellen Zustand des Trainers, sodass die Session später fortgesetzt werden kann.

## Anforderungen

### Entwicklungsprozess:
- Die Entwicklung erfolgt in einem Git-Repository unter Verwendung von **Gradle** als Build-System.
- Jede Funktion wird in mehreren Schritten umgesetzt: 
  - Zuerst wird der Entwurf (UML-Diagramm) aktualisiert und committet.
  - Danach erfolgt die Implementierung, inklusive der Erstellung von Tests und Dokumentation.
- Der Entwicklungsprozess wird durch automatisierte **JUnit-Tests** und **Javadoc**-Kommentare unterstützt.

### Funktionalität:
- Verwaltung von Wort-Bild-Paaren: Die Paare sind über URLs verknüpft.
- Rechtschreibtrainer: Ein Wort-Bild-Paar kann ausgewählt werden, und das zugehörige Wort muss korrekt eingegeben werden.
- Statistik: Es wird gezählt, wie oft ein Wort richtig oder falsch geraten wurde.
- Persistenz: Der Zustand des Trainers wird gespeichert und kann beim nächsten Programmstart geladen werden.
- Grafische Oberfläche: Eine einfache GUI über **JOptionPane** erlaubt die Interaktion mit dem Worttrainer.

## Verwendete Technologien
- **Github**: Versionierung und Zusammenarbeit
- **Gradle**: Build-System
- **IntelliJ**: Entwicklungsumgebung
- **ChatGPT**: Unterstützung bei der Softwareentwicklung
- **Dependencies**:
  dependencies {
    testImplementation platform('org.junit:junit-bom:5.9.1')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.0'
    implementation 'com.fasterxml.jackson.core:jackson-core:2.15.0'
    implementation 'com.fasterxml.jackson.core:jackson-annotations:2.15.0'
  }
  Jackson wird benötigt um Java Objects als JSON Objekte abzuspeichern.
