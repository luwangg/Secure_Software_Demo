[![Build Status](https://travis-ci.org/shreaker/Secure_Software_Demo.svg?branch=master)](https://travis-ci.org/shreaker/Secure_Software_Demo)

# Software-Sicherheit

Als Software die es sicher zu programmieren gilt, soll eine simple Onlinebanking Software namens "BugCoin" umgesetzt werden.

## Funktionen
### Benutzer
- Nutzerrollen: Benutzer, Premiumbenutzer, Administrator
- Kontostand einsehen
- Umsätze einsehen
- Überweisungen tätigen
- Registrierung durch Nutzer selbst
- Account deaktivieren
- Geschenkkarte zum Konto aufladen

### Admin
- Account aktivieren/deaktivieren
- Account upgrade/downgrade für Premiumbenutzer
- Alle Accounts anzeigen

## Sicherheitsfeatures
- HTTPS, kein HTTP
- Benutzername + Password zur Authentifizierung
- Two Factor Authentication
- Captcha
- Signieren von Überweisungen (TAN, Privatekey, ...)
- Emailverifizierung
- Logout nach Inaktivität
- Freischaltung neuer Nutzer durch Admin, nach PostIdent Verfahren
- Logging von Benutzer/Admin aktivitäten bezüglich SQL-Statements

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