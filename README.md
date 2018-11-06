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
 

moduler: 

server 
    parser no.kristiania.pgr200.core.http-requests og sender response 
    db  
        lese/skrive fra db 
core 
    no.kristiania.pgr200.core.http
    mer?  
client  
    tar mot brukerinput - samme som ak2?
    sender requests til server 
    printer svar (hjelp dersom input er ugyldig)

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
- [PGR200 Hovedinnlevering](#pgr200-hovedinnlevering)
    - [Oppgave](#oppgave)
        - [Arkitektur](#arkitektur)
        - [Programflyt](#programflyt)
        - [Forslag til datamodell](#forslag-til-datamodell)
    - [Sjekkliste for innleveringen](#sjekkliste-for-innleveringen)
        - [Forberedelse](#forberedelse)
        - [Innlevering](#innlevering)
    - [Retningslinjer for vurdering](#retningslinjer-for-vurdering)
        - [Minimum krav for bestått](#minimum-krav-for-best%C3%A5tt)
        - [Minimum krav for C](#minimum-krav-for-c)
        - [Minimum krav for B](#minimum-krav-for-b)
        - [Krav for A](#krav-for-a)


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

__LAG To til, som viser kode__ 


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




# PGR200 Hovedinnlevering

Innleveringsfrist: 12. november kl 09:00. **Viktig:** WiseFlow *stenger* når fristen er ute - lever i tide.

Tag koden med `innlevering` i GitHub og last opp en ZIP-fil til WiseFlow. Dersom du ikke fikk godkjent innlevering #1 eller #2 i første runde, last opp zip-fil av disse i tillegg.

## Oppgave

Du har funnet en konferanse du er interessert i å gå på, men du har ikke råd til billetten. Men frykt ikke: etter at du tok kontakt med de som organiserer konferansen fikk du høre at du kunne få gratisbillett dersom du hjelper til å lage noe programvare for konferansen.

Oppgaven din: lag en server for appen som inneholder konferanseprogrammet i en no.kristiania.pgr200.server.database. Funksjonaliteten må som et minimum tillate at man legger inn og lister ut foredrag på konferansen. Du bruke datamodellen angitt under eller forenkle eller endre den slik du selv ønsker.

Programmet skal følge god programmeringsskikk: Det skal ha enhetstester, det skal ha en god README-dokumentasjon, det skal hente inn konfigurasjon fra en .properties-fil. Fila skal ligge i current working directory, hete `innlevering.properties` og inneholde properties `dataSource.url`, `dataSource.username` og `dataSource.password`. Når vi evaluerer oppgaven ønsker vi å bruke egne verdier for disse. Prosjektet bør også bygge automatisk på [Travis CI](https://travis-ci.com).

Pass på at det er godt med tester, at koden kompilerer og kjører ok med "mvn test" og at du beskriver hvordan man tester løsningen manuelt.

Eksempel kjøring (inkluder dette i README.md-fila deres):

```bash
> mvn test
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Building conference-server 0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ conference-server ---
[INFO] Compiling 25 source files to e:\Profiles\jbrodwal\workspaces\demo\conference-server\target\classes
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ conference-server ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 9 source files to e:\Profiles\jbrodwal\workspaces\demo\conference-server\target\test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ conference-server ---

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
....
> mvn install
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Building conference-server 0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
...
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ conference-server ---
[INFO] Building jar: e:\Profiles\jbrodwal\workspaces\demo\conference-server\target/conference-server-0.1-SNAPSHOT.jar
[INFO]
[INFO] --- maven-shade-plugin:3.1.1:shade (default) @ conference-server ---
[INFO] Including org.postgresql:postgresql:jar:42.2.2 in the shaded jar.
[INFO] Replacing original artifact with shaded artifact.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 12.565 s
[INFO] Finished at: 2018-07-08T17:18:12+02:00
[INFO] Final Memory: 21M/211M
[INFO] ------------------------------------------------------------------------
> psql --username postgres --no.kristiania.pgr200.server.command="create no.kristiania.pgr200.server.database ... with owner .."'
> Oppdater innlevering.properties med dataSource.url, dataSource.username, dataSource.password
> java -jar target/no.kristiania.pgr200.server.database-innlevering.jar resetdb
> java -jar target/no.kristiania.pgr200.server.database-innlevering.jar insert "Mitt foredrag"
> java -jar target/no.kristiania.pgr200.server.database-innlevering.jar list
```

Som en del av semesterarbeidet skal dere levere en video på 3-8 minutter. Dersom dere har laget dette i forbindelse med innlevering #1 eller innlevering #2 kan dere bare legge ved denne video. I motsatt fall skal dere ta opp en video for mappeinnleveringen på 3-8 minutter der dere parprogrammerer. Velg gjerne en bit med kode som dere refactorerer. Screencast-o-matic anbefales som verktøy for video-opptaket, men andre verktøy kan benyttes. En lenke til videoen skal leveres og ikke videoen selv. Husk å åpne for tilgang til videoen ("unlisted" i Youtube) og legge inn lenke fra README.

Dere skal også gi tilbakemelding på en annen gruppes besvarelse. Tilbakemeldingen skal skrives i en egen fil (tilsvarende format som en README-fil) og inkluderes både i deres prosjekt og den andre gruppens prosjekt. Tilbakemeldingen dere har mottatt skal ligge i en fil som heter `MOTTATT-TILBAKEMELDING.md` og tilbakemeldingen dere har gitt skal hete `GITT-TILBAKEMELDING.md`.

I tilbakemeldingen er det lurt å stille spørsmålene: 1. Hva lærte jeg av denne koden? 2. Hva forsto jeg ikke av denne koden? 3. Hva tror jeg forfatterne av koden kunne ha nyttig av å lære?

### Arkitektur

![Architecture Overview](doc/conference-server.png)

### Programflyt

![Programflyt](doc/conference-server-flow.png)

### Forslag til datamodell

![Datamodell](doc/conference-data-no.kristiania.pgr200.core.model.png)

## Sjekkliste for innleveringen

- [ ] Kodekvalitet
  - [x] Koden er klonet fra GitHub classrom
  - [X] Produserer `mvn package` en executable jar? (tips: Bruk `maven-shade-plugin`)
  - [X] Bruker koden Java 8 og UTF-8
  - [ ] Bygger prosjektet på [https://travis-ci.com](https://travis-ci.com)?
  - [ ] Har du god test-dekning? (tips: Sett opp jacoco-maven-plugin til å kreve at minst 65% av linjene har testdekning)
  - [X] Er koden delt inn i flere Maven `<modules>`?
  - [ ] Bruker kommunikasjon mellom klient og server HTTP korrekt?
  - [X] Kobler serveren seg opp mot PostgreSQL ved hjelp av konfigurasjon i fila `innlevering.properties` i *current working directory* med `dataSource.url`, `dataSource.username`, `dataSource.password`?
- [ ] Funksjonalitet
  - [X] add: Legg til et foredrag i databasen med title, description og topic (valgfritt)
  - [X] list: List opp alle foredrag i basen med et valgfritt topic
  - [ ] show: Vis detaljer for et foredrag
  - [ ] update: Endre title, description eller topic for et foredrag
  - [X] Valgfri tillegg: Kommandoer for å sette opp hvor mange dager og timer konferansen skal vare og hvor mange parallelle spor den skal inneholde.
- [ ] Dokumentasjon i form av README.md
  - [ ] Navn og Feide-ID på dere de som var på teamet
  - [ ] Inkluderer dokumentasjonen hvordan man tester ut funksjonaliteten programmet manuelt? (Inkludert eventuell ekstra funksjonalitet dere har tatt med)
  - [ ] Inkluderer dokumentasjonen en evaluering av hvordan man jobbet sammen?
  - [ ] Inkluderer dokumentasjonen en screencast av en parprogrammeringsesjon?
  - [ ] Inkluderer dokumentasjonen en evaluering *fra* en annen gruppe og en evaluering *til* en annen gruppe?
  - [ ] Inkluderer dokumentasjonen en UML diagram med datamodellen?
  - [ ] Inkluderer dokumentasjonen en link til screencast av programmeringsesjon?
  - [ ] Inkluderer dokumentasjonen en egenevaluering med hvilken karakter gruppen mener de fortjener?

### Forberedelse

- [X] Finn endelig grupperpartner innen 1. november
- [X] Finn en gruppe for gjensidig evaluering innen 1. november

### Innlevering

- [ ] Gi veilederne `hakonschutt` og `mudasar187` tilgang til repository
- [ ] Tag koden med `innlevering` i GitHub
- [ ] Ta en zip-eksport fra GitHub
- [ ] Last opp zip-fil i WiseFlow
- [ ] Dersom innlevering #1 eller innlevering #2 ikke ble godkjent *i WiseFlow*, last opp zip-fil med hver av disse innleveringene

## Retningslinjer for vurdering

### Minimum krav for bestått

- Kompilerende kode som er sjekket inn i GitHub
- Tester som gjør noe ikke totalt ufornuftig (eksempel på ufornuftlig `assertTrue(true)` eller `assertEquals(4, 2+2)`)
- Kjørbart program som legger inn data i databasen

### Minimum krav for C

- Skriv og les programmet fra databasen i Java i henhold til deres egen dokumentasjon
- Les og skriv data over socket
- Kode lagret på GitHub, kompilerer og utfører en oppgave uten å krasje

### Minimum krav for B

De fleste av følgende må være oppfyllt:

- Et rimelig nivå med enhetstester som kjører på Travis CI
- Kode uten større skrivefeil, feil innrykk, slukte exceptions eller advarsler fra Eclipse
- Readme som beskriver 4-10 steg for å demonstrere programmet
- God kode: Enkel, konsis, uttrykksfull, velformattert kode uten vestlige feil eller mangler
- Ingen alvorlige feil, SQL injection hull, krasj ved uventet input

### Krav for A

Løsningen må oppfylle alle krav til B og ha 2-3 områder som hever den ytterligere:

- Velskrevet (men ikke nødvendigvis omfattende) dokumentasjon
- At videoen får fram kvalitetene i designet
- Uttrykksfulle enhetstester som er effektive på å fange feil og som kjører på Travis CI
- En velbegrunnet datamodell med 4-8 klasser
- En lettfattelig og utvidbar no.kristiania.pgr200.core.http-server
- Spennende generisk kode som egentlig er unødvendig kompleks for å løse problemet
- Enkel kode som løser problemet presist og konsist (i konflikt med forrige)

Grupper på 3 må ha flere av disse enn grupper på 2 for å få en A.
