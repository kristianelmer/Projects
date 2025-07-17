# Rapport – innlevering 1

## INF112 Project - GhostFighter

Team: (Gruppe 8-2) Martin S. Pedersen, Lyder Samnøy, Brage Merkesdal, Siren Bjorøy, Kristian B. E. Elmer, Marion M. Haugland
Link til prosjektboard: <https://trello.com/b/OFEjO9cN/gruppe-8-2>

### Oppgave A1

**Roller:**

- Team Lead/scrum-master: Martin
- Git Master: Kristian
- QA: Siren
- Documentation lead: Marion
- Digital Asset Manager: Brage
- Game designer: Lyder

**Rollebeskrivelser:**

- Team Lead/scrum-master: Ansvarlig for at prosjektet går på fram, at vi følger kanban-tavlen, har god kommukajson og ansvar for at vi er mest mulig effektive.
- Git Master: Har ansvar for bruken av git, løser eventuelle problemer i git og blir tech-support.
- QA: Ansvarlig for testene og at de oppdager potensielle problemer, og bli kjent med PiTest for å få best mulig testing.
- Documentation lead: Ansvar for å oppdatere og vedlikeholde dokumenter, og ansvar for innleveringer.
- Digital Asset Manager: Ansvar for grafisk design, audio, bilder og tekstfiler.
- Game designer: Ansvar for avgjørelser i utvikling av spillet.

**Erfaring:**

- Martin: 4. sem DTEK, Allrounder, kan litt om alt og har tidligere jobbet med kodeprosjekter i FriByte
- Lyder: går DTEK, har erfaring med faget fra tidligere. Er god på idemyldring og villig til å ta avgjørelser.
- Brage: 4. sem DTEK, begrenset kodeerfaring men brenner for Tetris og alt som ligner
- Siren: 4. sem DTEK (byttet fra årsstudium) klar for å lære, åpen for det meste
- Kristian: 4. sem AIKI, LeetCode nerd, god i Python, ny til Java. Erfaring fra en del prosjekter tidligere, god på tids/rom effektivitet.
- Marion: 4. sem AIKI, ingen "programmerings-gud", men veldig lærevillig. Erfaring med å jobbe i team.

### Oppgave A2: Konsept

**Om spillet:**

- Spillfigur:
- Figur som kan styres med piltastene opp/ned, høyre/venstre.
- Todimensjonal verden:
- Plattform - horisontal flate spilleren kan stå eller gå på.
- Vegg - vertikal flate som spilleren kan gå gjennom.
- Verden er størrre en skjermen får plass til.
- Fiende:
- Spøkelser som beveger seg tilfeldig i verden, og som spiller må interagere med for å iverksette "battle".
- Battlescene:
- Egen scene hvor spillfigur er i duell med fiende.
- "Healthpoints":
- Spiller kan samle inn peonggjenstander som gir spillfigur mer krefter.
- Utfordring i spillet:
- Å bekjempe fiende, samle inn nok poenggjenstander.
- Mål:
- Vinne over fiender i kamper.

Spillkonseptet vårt er inspirert at Pokemon, hvor kampene mot fiender (spøkelser) er tilsvarende Pokemon kamper.

**Kjøring:**

- Kompileres med `mvn package`.
- Kjøres med `mvn exec:java`.
- Krever java 21 eller senere.

**Kjente feil:**

**Credits:**

### Oppgave A3

***Tilpasse en prosess for teamet***

**Prosjektmetodikk:**

Vi har valgt å gå for en prosjektmetodikk inspirert av både scrum og Kanban. Vi har valgt ut en rekke elementer fra begge metodene som vi mener egner seg best for gruppeprosjektet.

Elementene i fra Scrum som vi mener hjelper oss i utviklingen av prosjektet er blant annet faste arbeidsintervaller. Vi har blitt enige om å treffes 2 ganger i uken, hver mandag og fredag i forkant av seminaret. Vi tenker også å ha delmål i prosjektet, som sprinter. En sprint-økt varer i 1-2 uker hvor vi jobber mot et mål vi har satt oss.

I tillegg har vi utnevnt en team lead/scrum-master som har et ekstra ansvar for at det er god arbeidsflyt. Det vi legger i god arbeidsflyt er god planlegging, at vi har klare mål for hva som skal bli gjort i sprintøktene, når det skal bli gjort, hvem som skal gjøre hva og god kommunikasjon.

Når det kommer til planlegging og organisering har vi blitt inspirert av Kanban-metoden hvor vi har satt opp en oversiktlig tavle som minner om Kanban-tavlen. Vi har en To-do-list, work in progress-list, og Done-list. Vi bruker et visualiseringsverktøy som heter Trello slik at alle har full oversikt over prosjektet til enhver tid. Team lead/Scrum-master har også et ekstra ansvar for å holde denne oppdatert.

Når det gjelder arbeidsfordeling har alle i teamet fått tildelt en rolle som innebærer at man har et ekstra ansvar innenfor et spesifikt område. I tillegg tenker vi å fordele arbeidsoppgaver på tvers av rollene underveis i prosjektet slik at alle får kjennskaper til alle emnene i prosjektet. Vi starter med et planleggingsmøte, fordeler oppgavene i sprint-økten og passer på at arbeidsmengden samsvarer med tiden på sprintøkten.

Et nøkkelelement under jobbingen blir å dokumenter alt man gjør slik at vi forholder oss til arbeidsoppgaver og følger tidsplanen. Vi tenker å dokumentere arbeidet ved å publisere det på Git, lage branches og oppdatere Trello. På denne måten vil vi kunne opprettholde effektiv jobbing samt kunne løse problemer på en bedre måte da de oppstår.

God kommunikasjon var også noe vi som gruppe fra første stund ble enige om å vektlegge. Vi tenker at god kommunikasjon vil være alfa omega for at vi skal få til et best mulig gruppe-prosjekt. For å få til dette treffes vi fysisk hver uke, samt bruker kommunikasjonsplattformen Discord aktivt.

**Hvordan får vi et best mulig prosjekt?**

- Metoder
- Scrum
- Kanban
- Tilpasninger
- Fleksibilitet dersom et gruppemedlem ikke kan være tilstede på en sprintøkt
- Mulige problemer?
- Feilberegning av tid, at vi bruker lengre/kortere tid enn planlagt på en oppgave

**Viktige aspekter ved prosessen:**

***Oversikt over forventet produkt***
Det overordnede målet til produktet er å lage et spill som vekker interesse hos spillerne, og at de som spiller GhostFighters opplever glede og føler at spillet var verdt tiden.

**Minimum Viable Product (MVP):**

1. Spiller får presentert en forside meny, med flere knapper; «how to play», «start», «credits», og musikk i bakgrunnen som skaper stemning.
2. Når spiller trykker på «play» kommer spiller inn i map-verden.
3. Spilleren kan bevege spillfigur ved hjelp av piltastene på tastaturet.
4. Spiller interagerer med terrenget i map-verden.
5. Spiller kan hente poeng (healthpoints) ved å interagere med poenggjenstander
6. Spiller skal oppsøke fiende for å interagere med dem slik at man kommer i en battlescene.
7. Spilleren kan vinne eller tape liv i kampen mot fienden, i verste fall dø.
8. Mål for spillebrett er å vinne over flest mulige fiender for å oppnå en mengde poeng.
9. Når en battlescene er ferdig kommer man tilbake til map-verden og kan møte på flere fiender.
10. Når man fullfører spillet eller dør får man opp start-meny ved oppstart eller «game-over»

**Brukerhistorier og akseptansekriterier i prioritert rekkefølge:**

***Brukerhistorie 1: Terreng***

Som spiller trenger jeg å kunne skille vegger fra bakgrunns elementer slik at jeg kan se hvor jeg skal bevege spillfigur og oppdage poenggjenstander og fiender. Som programmerer ønsker jeg å utvikle en funksjonalitet som gjør at spillfigur kan interagerer med elementer i terrenget. Dette oppnås ved at spillfigur skiller seg fra terrenget sammenstøter på elementene i terrenget.

***Akseptansekriterier:***

1. Gitt at spillet er i gang, når spiller har trykket play, så kan man bevege seg i terrenget ved hjelp av piltastene.
2. Gitt at spillfigur er i map-verden/terreng kan den ikke gå gjennom vegger.
3. Gitt at spiller er i map-verdenen/terreng så kan den interagere med fiende og plukke poenggjenstander.

***Brukerhistorie 2: Fiender***
Som spiller trenger jeg å kunne identifisere fiender i terrenget og støte på dem, slik at jeg kan starte en kamp mot dem. Som programmerer ønsker jeg å utvikle en funksjonalitet som gjør at når spillfigur sammenstøter på fiender så kommer man til battlescenen. I tillegg ønsker jeg å lage flere fiender som har ulik funksjonalitet.

***Akseptansekriterier:***

1. Gitt at spillet er i gang og spiller er i map-verden/terreng så kan man identifisere fiender.
2. Fiender beveger seg rundt i terrenget, slik at spillfigur kan oppsøke dem.
3. Ved sammenstøt på fiende, får man opp en ny verden, battlescene hvor fiende og spillfigur skal slåss.
4. Spiller får poeng ved seier over fiende.
5. Spiller mister poeng ved tap mot fiende, kan også dø og det blir «game over»
6. Gitt at spillet er i gang og bruker er i map-verden/terreng så kan man identifisere forskjellige typer fiender.

***Brukerhistorie 3: Oppgraderinger***

Som spiller trenger jeg å kunne identifisere poenggjenstander slik at jeg kan oppnå oppgraderinger. Spiller kan få forskjellige fordeler av de forskjellige poenggjenstandene. Som programmerer ønsker jeg å lage en oversikt som viser «poengene» til spillfigur og hva som skjer når spillfigur tar opp poenggjenstand.

***Akseptansekriterier:***

1. Gitt at spiller sammenstøter med poenggjenstand, så skal spiller få en oversikt over antall poeng man får, og totalt antall poeng spiller har.
2. Spiller skal også få økning i poeng med mindre man har nådd en maksimal grense.

***Brukerhistorie 4: Spillet er slutt***

Som spiller trenger jeg å få en oversikt som viser at spillet er over og poengscore, slik at man vet at spiller er over og hva resultatet ble. I tillegg til at det vises mulighet for å starte spillet på nytt. Som programmerer ønsker jeg å utvikle en skjerm som viser «game over» og poengscore, og som har en knapp som starter spillet på nytt.

***Akseptansekriterier:***

1. Gitt at spillet er over, når man har tapt/er død, så skal man få opp en skjerm som viser «game over» og poengscoren sin.
2. Det skal også være en knapp på skjermen som starter spillet på nytt.

### Oppgave A4: Kode

Se kode i source-mappen

### Oppgave A5: Oppsummering av prosjektet

Så langt i prosessen har det gått bra! Alle er motiverte og blitt kjent med rollene sine og hva det innebærer.

Til nå har vi ikke hatt behov for spesifikke roller, som f. eks QA - men har heller brukt tid på å bli kjent med Git og LibGDX. Vi har også begynt å kode spillet, hvor vi har laget mange klasser med lite funksjonalitet for å få strukturen i gang, vi har spiller, fiende, map og kamera som føger spiller.

Kommunikasjonen i gruppa er god, Vi bruker discord hyppig til å planlegge og holde hverandre oppdatert. Vi har begynt å bruke Trello, men ønsker å følge den strengere. Siden oppstartsfasen er så "lite spesifikk" så har vi heller bare gjort nødvendig arbeid på "feeling" og ikke fulgt skikkelig struktur, nå som kodebasen har begynt å komme seg er det lettere vise til konkrete ting som må gjøres. Her håper vi å ta mer nytte av Trello.

Gruppedynamikken er god, vi er en stor gruppe på 6 personer som gjør at vi får mange gode innspill. Til tider kan det være vanskelig å få alle på gruppen til å møte opp på de avtalte tidspunktene, av den grunn bruker vi discord aktivt.

3 forbedringspunkter til neste sprint:

 1. Bruke Trello mer.
 2. Alle på gruppen skal ha pushet en metode innen fredag.
 3. Gi beskjed på Discord at man kommer når gruppen skal samles.
