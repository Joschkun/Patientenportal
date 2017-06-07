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
- Bidirektional-Test angelegt (funktioniert alles)
- RegistrationDAO angelegt f�r Actor-Create-Methoden
- Entities (Relative, Office, Insurance) angelegt, fehlende Verkn�pfungen sind auskommentiert
- UserDAO fertig (UpdateAddress, UpdateContact hinzugef�gt)
- UserCRUDTest fertig (funktioniert alles)

To-Do
- ActorDAOs anlegen und RegistrationDAO vervollst�ndigen (siehe DAO Klassendiagramm und UserDAO) -- Jan

- Case-Entity und Vitaldata-Entity an die Struktur der funktionierenden Entities anpassen --> Testen
- ManytoMany-Verkn�pfung ausprobieren







Anmerkungen
- Case-Status bei Anzeige der F�lle filtern (Schon in den Rechten oder sp�ter?) --> in der Service-Darstellung ge�ndert (noch nicht sicher)
- Boolean-Ausgabe testen (true/false oder 1/0)

- EAGER f�r OnetoOne, Lazy f�r gro�e Abfragen !!!

- Idee: SaveOrUpdate-Funktion (ausprobieren, testen)

- versch. Methodennamen f�r die getUserx-Methoden werden nicht mehr gebraucht, da bidirektionaler Zugriff funktioniert