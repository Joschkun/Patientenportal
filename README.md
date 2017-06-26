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
- Criteria impolementiert und getestet (siehe Spielwiese.smalltests)
- CheckUsername zur RegistrationDAO hinzugef�gt und UserCRUDTest um checkUsername erweitert --> funktioniert alles

16.06.(Jan)
- RelativeDAO Update und Delete eingef�gt
- PatientDAO Update und Delete eingef�gt
- braucht man bei Contact ein create oder wird die dann in die Service schicht geschrieben da es ja �ber den User l�uft

19.06. (Jan/Stefan)

- Bidirektionaler Zugriff optimiert (muss noch auf alle bestehenden Klassen angewendet werden)
	- Join-Column auf der "owning-side" und MappedBy - Attribut auf der Gegenseite
- Criteria (Rechte, checkusername)
- Rechte fertig, RechteTest fertig, AccessTest angelegt und fertig
- ActorDAOs und RegistrationDAO fertig (bis auf Patient)
- InsuranceDAO und Test fast fertig

20.6 (Jan)
-MDOC, InstructionDoc und Doc als Supperklasse angelegt + zugeh�rige DAOs f�r MedicalDOc und InstructionDoc
-f�r Dokument kein DAO da reine Entity f�r Vererbung 

22.06. (Stefan)
- mapped bei bei den user-actor-verkn�pfungen eingef�hrt, au�erdem bei doctor-office
	- war auch n�tig, da der WS sonst fehler wirft
- Patient-Case eingef�gt
- MedicalDocDAO vervollst�ndigt (beim update ggf. noch Patient hinzuf�gen, try-Bl�cke fehlen noch)
- Mdoc zu case und patient eingef�gt
- MDoc-Test angelegt ( bis auf die Case-Verkn�pfung klappt alles)

23.06 (Stefan / nur auf Tablet eingef�gt, noch nicht getestet)
- case-doctor-Verkn�pfung angelegt (noch kein Test geschrieben)
- vitaldata-case-Verkn�pfung ge�ndert (Timestamp noch anschauen!), caseTest amge�asst


To-Do

- Dokumente fertig anlegen und mit Case Verkn�pfen
	- bei den Dokumenten fehlt noch das create!? (bei MDoc eingef�gt - Stefan)
	- Spalten aus der Superklasse werden (noch) nicht in die Datenbank �bernommen, da m�ssen wir noch was machen
	- Entities: Case-Verkn�pfung von Dokumenten in die Superklasse?
	- InstructiondocDAO MDOC Test achte dabei ob vererbte Sachen ge�ndert werden k�nnen und wie es angelegt wird --Jan

- InsuranceTest vervollst�ndigen -- Jan

- Bidirektionale OneToMany anpassen (soweit abgeschlossen, siehe Unteraufgabe)
	- L�uft darauf hinaus, dass wir alle (!) Case-Verkn�pfungen individuell anlegen und bei Case myppedBy angeben
	- noch nicht alles angelegt

- Login-Logik implementieren (bzw mal dieses System recherchieren, dass der Betreuer uns bei der zwischenpr�sentation genannt hat)
	- RBAM (role based access model)!

- Funktion: Einf�gen von Dokumenten in die DB pr�fen

- Tests funktionieren individuell, aber nicht in der Suite
	- liegt daran, dass z.B. mehrere Tests einen Case anlegen und damit arbeiten (beim 2. Test passt die ID dann nicht mehr)
	- entweder L�sung in der Unit-Logik finden, Tests zusammenfassen oder einfach ohne Suite arbeiten (w�re schade)

- DAOs zu Interfaces "ummodeln"

- WS-Logik!!!

Anmerkungen
- Case-Status bei Anzeige der F�lle filtern (Schon in den Rechten oder sp�ter?) --> in der Service-Darstellung ge�ndert (noch nicht sicher)
- Idee: SaveOrUpdate-Funktion (ausprobieren, testen)
- versch. Methodennamen f�r die getUserx-Methoden werden nicht mehr gebraucht, da bidirektionaler Zugriff funktioniert
	- gleiches gilt f�r die getDoctorsByx, etc. Methoden, das geht durch Eager/Init - Zugriff (macht eigentlich mehr Sinn)

- EAGER f�r OnetoOne, Lazy f�r gro�e Abfragen !!!
	- FEHLER IST BEHOBEN, NACH STUNDENLANGER SUCHE, FUCK YEAH
	- Die L�sung ist in der CaseDAO zu bewundern
	- im Prinzip k�nnen wir die ganzen EAGER-Verbindungen auch lazy machen und mit Hibernate-Initialize arbeiten (insofern sinnvoll)
	
- Flush-Error bei OneToMany und ManyToOne ist behoben
	- eine bestimmte Reihenfolge muss eingehalten werden, damit alles funktioniert
	- wie das aussehen kann/muss, k�nnt ihr euch im DoctorOfficeCRUDTest ansehen
	- Grundlegend: Referenzierte Entities erst anlegen und dann verkn�pfen (au�er cascadetype.all und und owning-Klassen)
	
- Die if (xxx =! null) - Logik in der Persistenzschicht kann weg, wenn wir immer das Prinzip nutzen, was den Flush-Error vermeidet
	- die if-Logik kann dann in die Service-Schicht, wo sie ja eigentlich "hingeh�rt"
	
- Das gleiche Prinzip kann man vllt auch bei dem Schreiben von Adreessen �ber das Office angewendet werden (funktioniert noch nicht)
	- Dadurch sparen wir uns die getrennten Address- und ContactDAOs
	- stattdessen nehmen wir das bestehende Objekt aus der Abfrage und �ndern nur den gew�nschten Wert
	- z.B. office.getContact().setMail("neue Mail");
	
- MAPPEDBY f�r Bidirektionale Beziehungen benutzen!!!!!! Siehe Patient-Insurance
	
Logik f�r die Service-Schicht
- Verkn�pfungen l�schen bei delete
- Keine eigene DAO-Abfrage f�r Case-Status, lieber "aussortieren" in der WS-Logik?
- Bei Create-Case auch dem erstellenden Doktor Lese-/Schreibrechte mitgeben