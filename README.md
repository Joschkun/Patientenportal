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

- Entities (Case) angelegt, fehlende Verkn�pfungen sind auskommentiert
- CaseCRUDTest angelegt (Kompletter Unit-Test des CaseDAOs avisiert)
- Testing-Suite eingerichtet (Test k�nnen jetzt alle zusammen laufen)
- Ordner-Struktur ge�ndert (vor allem bei Tests) / kleine Tests zum "Probieren" kommen jetzT in die "Spielwiese"

08.06. (Stefan)
- OfficeDAO angefangen
- DoctorOfficeCRUDTest angefangen

14.06. (Stefan/Jan)
- User/Doctor/Relative/Patient/Address/Contact DAOs bearbeitet
- Tests weiterentwickelt
- ManyToMany bei Patient-Relative gestestet (funktioniert super)

15.06. (Stefan)
- Try/Catch/Finally - Bl�cke in die Tests eingebaut (noch nicht fertig)
- Struktur der DAOs leicht ge�ndert

To-Do
- ActorDAOs, RegistrationDAO und OfficeDAO vervollst�ndigen (siehe DAO Klassendiagramm und UserDAO) -- Jan

- Unit-Tests aktualisieren (neue Update-Mechanik)

- Herausfinden, warum die doctor_office JoinTable angelegt wird

- Login-Logik implementieren (bzw mal dieses System recherchieren, dass der Kollege uns bei der zwischenpr�sentation genannt hat
- Criteria-Abfragen testen ("Suche" nach username, bzw. Abfragen wie "zeige mir alle F�lle, bei denen ich Rechte habe")

Anmerkungen
- Case-Status bei Anzeige der F�lle filtern (Schon in den Rechten oder sp�ter?) --> in der Service-Darstellung ge�ndert (noch nicht sicher)
- Idee: SaveOrUpdate-Funktion (ausprobieren, testen)
- versch. Methodennamen f�r die getUserx-Methoden werden nicht mehr gebraucht, da bidirektionaler Zugriff funktioniert
	- gleiches gilt f�r die getDoctorsByx, etc. Methoden, das geht durch Eager/Init - Zugriff (macht eigentlich mehr Sinn)

- EAGER f�r OnetoOne, Lazy f�r gro�e Abfragen !!!
	- FEHLER IST BEHOBEN, NACH STUNDENLANGER SUCHE, FUCK YEAH
	- Die L�sung ist in der CaseDAO zu bewundern
	
- Flush-Error bei OneToMany und ManyToOne ist behoben
	- eine bestimmte Reihenfolge muss eingehalten werden, damit alles funktioniert
	- wie das aussehen kann/muss, k�nnt ihr euch im DoctorOfficeCRUDTest ansehen
	
- Die if (xxx =! null) - Logik in der Persistenzschicht kann weg, wenn wir immer das Prinzip nutzen, was den Flush-Error vermeidet
	- die if-Logik kann dann in die Service-Schicht, wo sie ja eigentlich "hingeh�rt"
	
- Das gleiche Prinzip kann man vllt auch bei dem Schreiben von Adreessen �ber das Office angewendet werden (funktioniert noch nicht)
	- Dadurch sparen wir uns die getrennten Address- und ContactDAOs
	- stattdessen nehmen wir das bestehende Objekt aus der Abfrage und �ndern nur den gew�nschten Wert
	- z.B. office.getContact().setMail("neue Mail");
	
	
Logik f�r die Service-Schicht
- Verkn�pfungen l�schen bei delete