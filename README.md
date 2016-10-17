# Software-Sicherheit

Als Software die es sicher zu programmieren gilt, soll eine simple Onlinebanking Software namens "BugCoin" umgesetzt werden.

[![Build Status](https://travis-ci.org/shreaker/Secure_Software_Demo.svg?branch=master)](https://travis-ci.org/shreaker/Secure_Software_Demo)

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
- Java / JavaEE
- Maven
- MySQL/MariaDB

## Mockup
Eine Mockup Implementierung in HTML und CSS der Weboberfläche befindet sich im Ornder **html/**.

![Mockup of Webapplication](docs/readme/mockup_webapp.png)
