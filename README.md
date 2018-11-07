# Eksamen 

## Todo 
-[ ] Refaktorer HTTP-biten 
-[ ] Bytt til POST med data i body 
-[ ] Gå over alle Fixme's og todo's 
-[ ] Fjerne alle dependencies fra eksamen-pom
-[ ] Ha en index.html herokuapp.com/schedule som viser schedule rendret
-[ ] Gå gjennom ALLE klasser bit for bit og skriv forklarende doc for _alle_ klasser og noen metoder   
-[ ] Skrive javadoc
-[ ] client lytter etter kommandoer, behøver ikke kjøre java-jar hver gang (har en "prompt")
-[ ] sjekke warnings fra intellij
-[ ] Skrive om de største testene som integrasjonstester 

## Notater fra tog :D 
DaoTest.createDao() er lik i alle tester. Burde det være default-metode?


## Notes 
Refaktorering: 
Commands ligger i core -> client/server implementerer bare execute.
For at det skal fungere, må også client gi et map med eks. "title", "Tords toalettguide"
Dvs. at parsing i client fungerer litt som i første øving, men at argumenter parsers på forhånd.          

-[ ] wrapper-klasser rundt JSON som sier hva slags operasjon som ble gjort mm. 

## Todo tuesday
1. Argumentparser/inputparser-tester + sever og client test 
2. rydd i clientinserttalkcommand
3. TordTest, Olav JavaDoc + rydd  
4. "localhost" er hardkodet, ligge i proeprties-fil?
 


- [Eksamen](#eksamen)
    - [Todo](#todo)
    - [Notater fra tog :D](#notater-fra-tog-d)
    - [Notes](#notes)
    - [Todo tuesday](#todo-tuesday)
- [Dokumentasjon](#dokumentasjon)
    - [Evaluering av samarbeid](#evaluering-av-samarbeid)
    - [Video](#video)
    - [Diagrammer](#diagrammer)
    - [Egenevaluering](#egenevaluering)


# Dokumentasjon 
Olav Sundfør - sunola17 <br>
Tord Jon - jontor17 <br>

    - [X] Navn og Feide-ID på dere de som var på teamet
    - [ ] Inkluderer dokumentasjonen hvordan man tester ut funksjonaliteten programmet manuelt? (Inkludert eventuell ekstra funksjonalitet dere har tatt med)
    - [X] Inkluderer dokumentasjonen en evaluering av hvordan man jobbet sammen?
    - [X] Inkluderer dokumentasjonen en screencast av en parprogrammeringsesjon?
    - [ ] Inkluderer dokumentasjonen en evaluering *fra* en annen gruppe og en evaluering *til* en annen gruppe?
    - [ ] Inkluderer dokumentasjonen en UML diagram med datamodellen?
    - [X] Inkluderer dokumentasjonen en link til screencast av programmeringsesjon?
    - [ ] Inkluderer dokumentasjonen en egenevaluering med hvilken karakter gruppen mener de fortjener?


## Evaluering av samarbeid 
Vi har stort sett jobbet sammen, ofte på en maskin. Når vi ikke har gjort det, har vi jobbet over nett, med Git og samtaleverktøy som voicechats i Slack og Discord. Vi har også delt skjerm gjennom disse tjenestene og på den måten fått gjort en slags form for parprogrammering når vi har sett behov for det.

Hva samarbeidet angår, finner vi ofte gode løsninger. Det er ikke alltid vi er enige, men vi opplever at samtalen er konstruktiv, heller enn å være en konflikt. Vi har jevnt over funnet en god løsning som begge kan si seg fornøyde med, til tross for at ikke alle elementer er slik som hver av oss opprinnelig hadde sett for seg.

I arbeidskrav 2 skrev vi at vi har et forbedringspotensiale på det å sette konkrete mål for hva som skal gjøres, fremfor å jobbe sporadisk og hoppe mellom arbeidsoppgaver. Her har vi blitt flinkere. 
En teknikk vi har brukt er å sette opp konkrete lister over hva som må gjøres. 

## [Video](https://www.youtube.com/watch?v=3axZ6puyq0s&feature=youtu.be)

## Diagrammer 
![Databasediagram](./diagrams/database/database.png)
![DAO-klassediagram](./diagrams/class/dao.png)
![Command-klassediagram](./diagrams/class/command.png)


- [ ] evaluering av prosjektet 
- [ ] Hvilken karakter vi mener vi fortjener

## Egenevaluering
Vårt prosjekt har brukt noe av logikken fra tidligere arbeidskrav. Det gikk lettere å slå sammen de to prosjektene denne gangen, siden vi hadde gjort det tidligere. Vi tok plugins som _maven-shade-plugin_ og _jacoco_ med oss fra tidligere arbeidskrav. Under oppsettet kom nytten av å ha gjort det samme tidligere tydelig frem. 

Til forskjell fra tidligere oppgaver, har vi i den endelige innleveringen delt prosjektet opp i flere maven-moduler. 
Dette har gitt oss en mye bedre fordeling av oppgaver ("Separation of concerns") i programmet enn vi ellers ville hatt. 

Kommandoer ("Commands") spiller en stor rolle i vår struktur. En kommando er en handling som programmet gjør, basert på et input. Et eksempel på en kommando er `InsertTalkCommand`. Den har som oppgave å legge inn et foredrag ("talk") i databasen. 
Kommandoer som denne fyller to roller:  
1. Bruker forteller clienten at dette skal skje
2. Server får beskjed av klienten og utfører den faktiske operasjonen
Denne egenskapen gjelder alle kommandoer i programmet vårt. Derfor har alle kommandoer et felles sett med  abstrakte "base"-klasser i `Core`-modulen. 
`Server`- og `Client`-modulene implementerer så konkrete versjoner av disse. (f.eks. `ServerInsertTalkCommand`og `ClientInserTalkCommand`)

Tester -> forklare hvorfor store 



