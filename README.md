# Patientenportal

Nicht im Master-Branch arbeiten!

- Wenn ihr mit Programmieren startet, checked euren Branch und merged dann von "Development"
- Nach dem Commit und Push in eurem eigenen Branch checked ihr Development und merged von dort dann euren eigenen Branch (da sollte m�glichst alles funktionieren dann)

- Hier unten k�nnen wir nochmal kurz notieren, das wir zuletzt gemacht haben und was noch fehlt


Fortschrittsstand:

06.06. (Stefan)
- Entities (User, Doctor, Patient, Address, Contact) angelegt, fehlende Verkn�pfungen sind auskommentiert

07.06. (Jan/Stefan)
- UserCRUDTest angelegt (Kompletter Unit-Test des UserDAOs avisiert)
- RegistrationDAO angelegt f�r Actor-Create-Methoden
- Entities (Relative, Office, Insurance) angelegt, fehlende Verkn�pfungen sind auskommentiert


To-Do
- UserDAO vervollst�ndigen (versch. Methodennamen f�r die getUserx-Methoden // "userID" �berall anpassen)
- Case-Entity und Vitaldata-Entity an die Struktur der funktionierenden Entities anpassen
- DAOs f�r Address und Contact anlegen







Anmerkungen
- Case-Status bei Anzeige der F�lle filtern (Schon in den Rechten oder sp�ter?) --> in der Service-Darstellung ge�ndert (noch nicht sicher)
- Boolean-Ausgabe testen (true/false oder 1/0)

- EAGER f�r OnetoOne, Lazy f�r gro�e Abfragen !!!