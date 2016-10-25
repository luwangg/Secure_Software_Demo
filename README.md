[![Build Status](https://travis-ci.org/shreaker/Secure_Software_Demo.svg?branch=master)](https://travis-ci.org/shreaker/Secure_Software_Demo)

# Software-Sicherheit

Als Software die es sicher zu programmieren gilt, soll eine simple Onlinebanking Software namens "BugCoin" umgesetzt werden.

## Funktionen
### Benutzer
- Nutzerrollen: Benutzer, Premiumbenutzer, Administrator <html>&#10004;</html>
- Kontostand einsehen <html>&#10004;</html>
- Umsätze einsehen <html>&#10004;</html>
- Überweisungen tätigen <html>&#10004;</html>
- Registrierung durch Nutzer selbst <html>&#10004;</html>
- Geschenkkarte zum Konto aufladen <html>&#10004;</html>
- ~~Account deaktivieren~~

### Admin
- Account aktivieren/deaktivieren <html>&#10004;</html>
- Account upgrade/downgrade für Premiumbenutzer <html>&#10004;</html>
- Alle Accounts anzeigen <html>&#10004;</html>
- Geschenkkarten Codes erzeugen

## Sicherheitsfeatures
- HTTPS, kein HTTP
- Benutzername + Password zur Authentifizierung <html>&#10004;</html>
- Two Factor Authentication <html>&#10004;</html>
- Captcha
- Signieren von Überweisungen (TAN, Privatekey, ...)
- Logout nach Inaktivität <html>&#10004;</html>
- Freischaltung neuer Nutzer durch Admin, nach PostIdent Verfahren
- Logging von Benutzer/Admin aktivitäten bezüglich SQL-Statements
- Sperren nach mehrmaliger fehlerhafter Passwort Eingabe
- ~~Emailverifizierung~~

## Eingesetzte Technologien
- Java -> SpringBoot (Spring)
- Maven
- H2 database
- Design patterns: MVC + Table Data Gateway

## Mockup
Eine Mockup Implementierung in HTML und CSS der Weboberfläche befindet sich im Ornder **html/**.

![Mockup of Webapplication](docs/readme/mockup_webapp.png)

## Continuous integration   
https://travis-ci.org/shreaker/Secure_Software_Demo [![Build Status](https://travis-ci.org/shreaker/Secure_Software_Demo.svg?branch=master)](https://travis-ci.org/shreaker/Secure_Software_Demo)

## Deployment
Unter https://salty-anchorage-26470.herokuapp.com steht eine mehr oder weniger aktuelle Version der Webapplikation zur verfügung. Achtung: Bis dato wird die Applikation im development mode betrieben, das bedeutet die Datenbank wird spätestens nach 24h zurückgesetzt.
