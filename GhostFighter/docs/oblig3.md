# Rapport – innlevering 3

## INF112 Project - GhostFighter

Team: (Gruppe 8-2) Martin S. Pedersen, Lyder Samnøy, Brage Merkesdal, Siren Bjorøy, Kristian B. E. Elmer, Marion M. Haugland

Trykk [her](https://trello.com/b/OFEjO9cN/gruppe-8-2) for å se Trello-tavlen til prosjektet, tilgang er foreløpig kun gitt til Endre (han bør kunne legge til flere).

### Oppgave A1

**Roller:**

- Team Lead/scrum-master: Martin
- Git Master: Kristian
- QA: Siren
- Documentation lead: Marion
- Digital Asset Manager: Brage
- Game designer: Lyder

**Rollebeskrivelser:**

- Team Lead/scrum-master: Ansvarlig for at prosjektet går på fram, at vi følger kanban-tavlen, har god kommunikason og ansvar for at vi er mest mulig effektive.
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

Spillfigur:

- Figur som kan styres i alle retninger på et åpent brett med diverse vegger og hindre

Todimensjonal verden:

- Plattform - horisontal flate spilleren kan stå eller gå på.
- Vegg - vertikal flate som spilleren ikke kan gå gjennom.
- Hinder - Objekt som står i stil med verdenen og spilleren ikke kan gå igjennom
- Verden er størrre en skjermen får plass til. Kamera følger spilleren.

Fiende:

- Spøkelser som beveger seg tilfeldig i verden, og som spiller må interagere med for å iverksette "battle".

Powerup:

- Spiller kan samle inn peonggjenstander som gir spillfigur "Hjerter".

Healthpoints:

- Har Healthpoints i omverdenen og i duell. I omverdenen starter man med 3 hjerter. I duell har man 20hp, som økes dynamisk når man vinner dueller

Battlescene:

- Egen scene hvor spillfigur er i duell med fiende.
- duell-healthpoints resettes for hver duell. Taper man i Battlescene så mister man 1 omverden-healthpoint

Utfordring i spillet:

- Å bekjempe fiender i duell, samle inn nok powerup til å overleve (RNG-based).

Mål:

- Vinne over fiender i kamper.

Spillkonseptet vårt er inspirert at Pokemon, hvor kampene mot fiender (spøkelser) er tilsvarende Pokemon kamper.

**Kjøring:**

- Kompileres med `mvn package`.
- Kjøres med `mvn exec:java`.
- Krever java 21 eller senere.

**Kjente feil:**

- Fart på spiller og fiende er høyere hva som er forventet i sluttproduktet, for utviklingens skyld
- Kamera er mer zoomet ut enn hva som er i forventet i sluttproduktet, for utviklingens skyld

**Credits:**

### Oppgave A3

***Tilpasse en prosess for teamet***

**Prosjektmetodikk:**

Vi har valgt å gå for en prosjektmetodikk inspirert av både scrum og Kanban. Vi har valgt ut en rekke elementer fra begge metodene som vi mener egner seg best for gruppeprosjektet.

Elementene i fra Scrum som vi mener hjelper oss i utviklingen av prosjektet er blant annet faste arbeidsintervaller. Vi har blitt enige om å treffes ukentlig, hver fredag i forkant av seminaret, og en ekstra dag i uken ved behov. Vi tenker også å ha delmål i prosjektet, som sprinter. En sprint-økt varer i 1-2 uker hvor vi jobber mot et mål vi har satt oss.

I tillegg har vi utnevnt en team lead/scrum-master som har et ekstra ansvar for at det er god arbeidsflyt. Det vi legger i god arbeidsflyt er god planlegging, at vi har klare mål for hva som skal bli gjort i sprintøktene, når det skal bli gjort, hvem som skal gjøre hva og god kommunikasjon.

Når det kommer til planlegging og organisering har vi blitt inspirert av Kanban-metoden hvor vi har satt opp en oversiktlig tavle som minner om Kanban-tavlen. Vi har en To-do-list, work in progress-list, og Done-list. Vi bruker et visualiseringsverktøy som heter Trello slik at alle har full oversikt over prosjektet til enhver tid. Team lead/Scrum-master har også et ekstra ansvar for å holde denne oppdatert.

Når det gjelder arbeidsfordeling har alle i teamet fått tildelt en rolle som innebærer at man har et ekstra ansvar innenfor et spesifikt område. I tillegg tenker vi å fordele arbeidsoppgaver på tvers av rollene underveis i prosjektet slik at alle får kjennskaper til alle emnene i prosjektet. Vi starter med et planleggingsmøte, fordeler oppgavene i sprint-økten og passer på at arbeidsmengden samsvarer med tiden på sprintøkten.

Et nøkkelelement under jobbingen blir å dokumentere alt man gjør slik at vi forholder oss til arbeidsoppgaver og følger tidsplanen. Vi tenker å dokumentere arbeidet ved å publisere det på Git, lage branches og oppdatere Trello. På denne måten vil vi kunne opprettholde effektiv jobbing samt kunne løse problemer på en bedre måte da de oppstår.

God kommunikasjon var også noe vi som gruppe fra første stund ble enige om å vektlegge. Vi tenker at god kommunikasjon vil være alfa omega for at vi skal få til et best mulig gruppe-prosjekt. For å få til dette treffes vi fysisk hver uke, samt bruker kommunikasjonsplattformen Discord aktivt.

**Hvordan får vi et best mulig prosjekt?**

- Metoder
- Scrum
- Kanban
- Tilpasninger
- Fleksibilitet dersom et gruppemedlem ikke kan være tilstede på en sprintøkt

Mulige problemer?

- Feilberegning av tid, at vi bruker lengre/kortere tid enn planlagt på en oppgave

**Viktige aspekter ved prosessen:**

***Oversikt over forventet produkt***
Det overordnede målet til produktet er å lage et spill som vekker interesse hos spillerne, og at de som spiller GhostFighter GO opplever glede og føler at spillet var verdt tiden.

**Minimum Viable Product (MVP):**

1. Spiller får presentert en forside meny, med en knapp; «play», og musikk i bakgrunnen som skaper stemning.
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

Som spiller trenger jeg å kunne skille vegger fra bakgrunnselementer slik at jeg kan se hvor jeg skal bevege spillfigur og oppdage poenggjenstander og fiender. Som programmerer ønsker jeg å utvikle en funksjonalitet som gjør at spillfigur kan interagerer med elementer i terrenget. Dette oppnås ved at spillfigur skiller seg fra terrenget sammenstøter på elementene i terrenget.

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

Som spiller trenger jeg å kunne identifisere en ny verden/scene som kommer opp med en fight-knapp og fiende, slik at spiller kan starte en kamp mot fiende. Som programmerer ønsker jeg å utvikle en funksjonalitet som gjør at når spiller er i battlescene kjemper spillfigur mot fiende. I tillegg ønsker jeg å lage en «health-points» index og mulighet for å pause en fight.

***Akseptansekriterier:***

Akseptansekriterier:
1.Gitt at spiller har interagert med fiende så skal man komme inn i ny verden, battlescene.
2.Gitt at man er inne i battlescene så kan man se en fight-knapp. Hvis man trykker på fight-knappen så skal «Health-points» index bli oppdatert etter hvem som får/mister poeng.
Etter at spillfigur er ferdig med en fight blir spiller sendt tilbake til map-verdenen.

***Brukerhistorie 4: Spillet er slutt***

Som spiller trenger jeg å få en oversikt som viser at spillet er over og poengscore, slik at man vet at spiller er over og hva resultatet ble. I tillegg til at det vises mulighet for å starte spillet på nytt. Som programmerer ønsker jeg å utvikle en skjerm som viser «game over» og poengscore, og som har en knapp som starter spillet på nytt.

***Akseptansekriterier:***

1. Gitt at spillet er over, når man har tapt/er død, så skal man få opp en skjerm som viser «game over» og poengscoren sin.
2. Det skal også være en knapp på skjermen som starter spillet på nytt.

### Oppgave A4: Kode

Se komplett kode i [src-mappen](/src/)

Det finnes noen bugs i koden. En av bugsene er at knappene på velkomstskjermen og hjelpesiden ikke blir endret i størrelse hvis man utvider eller endrer på skjermen. Selve knappene vil ha samme utseende, men knappefunksjonen vil være et annet sted på skjermen.
En annen bug er at når en battle avsluttes kommer man til overworld med en gang en spillfigur/fienden har dødd.

En siste bemerkning er at vi noen steder har laget animasjoner basert på 'ticks' i de ulike `render()` metodene, som i vår versjon av libgdx blir kalt 60 ganger i sekundet. Dersom det skulle skjedd endringer i Libgdx som endrer på tick-raten, så vil animasjonene endres deretter. Men i akkurat dette prosjektet ser ikke vi på det som et problem som bør tas hensyn til.

### Oppgave A5: Oppsummering av prosjektet

Vi er fornøyde med faget og prosjektet så langt! Gruppen samarbeider godt og det er givende og morsomt å lage spill sammen.

Alle er blitt kjent med rollene sine og ansvarsområder, selv om vi ikke følger dem slavisk enda. Så vet vi hverandres ansvarsområder og bygger sakte men sikkert våre egne spesialiseringer.

Spillet har begynt å forme seg og vi kan komfortabelt si at vi har laget et velfungerende spill (early access-versjon). Vi synes kodebasen har holdt seg modulær, og vi har passet på at ingen klasser blir unødvendig store. Opprettelsen av 2 scener og interaksjon dem mellom har vært noe krevende, spesielt med tanke på ViewPorten, men har løst seg veldig bra. Selv om vi begynner å nærme oss slutten av spillutviklingen og har et spillbart spill, ønsker vi fortsatt å bedre brukeropplevelsen. Vi har da for eksempel implementert en dans som indikerer seier i en battle.

Kommunikasjonen i gruppa er god, Vi bruker discord hyppig til å planlegge og holde hverandre oppdatert. Trello brukes for å holde struktur, men planleggingen og utviklingen skjer muntlig og via discord. På gruppens discord har vi flere kanaler for blant annet utvikling, git, wiki (bla deling av ressurser) og en generell kanal. Trello sin rolle for gruppen blir mest å luke ut støy fra discordsamtalene og ha oversikt over hva som har blitt gjort og må gjøres.

***Dette har vi fikset siden sist:***

1. Siden forrige gang har vi videreutviklet spillet slik at det er mer spillbart. Vi har bl.a. lagt til abstrakte power-ups, redigert map, og laget en BOSS-ghost, noe som har hatt påvirkning på spillet.
2. Vi har laget flere interfaces bl.a. IPlayer, IRender, og overworldEntity. Vi har også lagt til abstract classes som f.eks. EntityFactory og GhostFactory.
3. Vi har laget forside og hjelpeside med knapper.
4. Vi har også laget en tekniskbeskrivelse av programmet i form av et klassediagram.
5. Vi har også lagt til mer dokumentasjon av public metoder.

3 forbedringspunkter til neste sprint:

1. Løsrive modellens avhengighet av GameView // Oppdatering fredag 22:26 - Nogenlunde løst problemet! Tar gjerne imot feedback på løsning med Map krøll
2. Utbedre testing - har stått på stedet hvil ettersom maploading har hindret oss i å lage objekter i test-hierarkiet // løst ish ? Er mocking gjort riktig?
3. Rydde opp i kode hvor det er nødvendig, slik at vi følger SOLID-prinsippene.
