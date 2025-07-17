# Rapport – innlevering 4

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

- Team Lead/scrum-master: Ansvarlig for at prosjektet går på fram, at vi følger kanban-tavlen, har god kommunikasjon og ansvar for at vi er mest mulig effektive.
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

- Kamera er mer zoomet ut det vi skulle ønsket, men det var vanskelig å endre så den er relativt ut-zoomet nå
- Vegg bør tegnes oppå spiller for å gi illusjon at man går bak vegg, nå blir spiller tegnet sist

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

Tester er skrevet nesten utelukkende på Modellen i vår MVC-arkitektur. Vi så ikke behov for å grundig teste controlleren, da den hovedsakelig gjør metodekall på `model` basert på input/tastetrykk. Vi har testet View gjennom prosjektet manuelt/visuelt ved å kjøre programmet og sett om ting er som forventet.

En bemerkning verdt å ta hensyn til er at vi noen steder har laget animasjoner basert på 'ticks' i de ulike `render()` metodene, som i vår versjon av libgdx blir kalt 60 ganger i sekundet. Dersom det skulle skjedd endringer i Libgdx som endrer på tick-raten, så vil animasjonene endres deretter. Men i akkurat dette prosjektet ser ikke vi på det som et problem som bør tas hensyn til.

### Oppsummering testing

- `model` pakken er testet grundig da det er der funksjonaliteten til spillet blir utført, og der man best kan verifisere at spillets oppførsel er som forventet.
- Vi diskuterte om vi skulle skrive tester til `controller` pakken, men valgte å ikke gjøre det siden den (nesten) utelukkende gjør kall på metoder i `model` basert på tastetrykk. Vi har i stedet gjort manuell testing ved å se om forventet funksjonalitet skjer ved tastetrykk. Vi har og skrevet tester direkte til `model`, så eneste som ikke er testet er begrensninger gjort i if-setningene basert på gamestates osv i controlleren.
- Til `Audiocontroller` har vi testet at de ulike boolean flags'ene endrer seg som forventet. F. eks at `mute` fungerer som forventet og at `battlemusic` flagget blir satt til `true` når man starter `battlescene`
- Testing til `utils` pakken er ganske straight forward. Testen hvor vi mocker at data lastet inn riktig fra CSV ressurs er nok triviell..
- `view` pakken er ikke skrevet tester til, men heller blitt testet manuelt ved å spille spillet. Som følge at dette har vi ført inn `<excludes>**/view/**</excludes>` i `pom.xml`

### Oppgave A5: Oppsummering av prosjektet/retrospektiv

Når alt kommer til alt, er vi fornøyde med hvordan vi har arbeidet med semesteroppgaven. Vi startet som en gjeng med fremmede til å bli et velfungerende lag med stor L. Vi har gjennom perioden bygget et lag bestående av folk med forskjellige kunnskaper og egenskaper som til slutt ikke bare har fått et ferdig produkt, men vi tar også med oss mye lærdom fra det å jobbe sammen i et team.

Til å begynne med, føler vi at vi har hatt en god rolleinndeling. Vi har vært en gjeng med noe ulik kompetanse, hvor tanken har vært å spille på hverandres styrker, og at alle bidrar til fellesskapet. I tillegg til at vi har hatt et åpent miljø hvor vi har turt å diskutere og stille spørsmål til det som måtte være, noe vi føler har gitt oss en fin læringskurve i prosjektet.

Noen erfaringer vi har gjort oss under arbeidet er blant annet at det å ha en plan og et mål å jobbe mot effektiviserer arbeid. Vi brukte en Kanban-tavle som viste oversikt over oppgaver som skulle gjøres, og oppgaver som var under arbeid. Dette gjorde at vi til enhver tid visste hva som skulle gjøres. Det at vi også etter hvert møte kom med punkter vi skulle jobbe mot frem til neste gang, bidro til at vi fullførte delmål og hadde konsistenitet i arbeidet. En annen erfaring som er verdt å nevne er at det å ha en scrum-master hjelper også veldig på god arbeidsflyt. Vi har hatt en god team-leder som har gitt klare og gode tilbakemeldinger på arbeid, samt tatt ansvar for å bedre produktiviteten.

Gruppedynamikken og kommunikasjonen i gruppa har vært god gjennom prosjektet. Vi har brukt discord hyppig til å planlegge og holde hverandre oppdatert. Trello har vi brukt for å holde struktur, men det meste av planleggingen og utviklingen har skjedd muntlig og gjennom discord. På gruppens discord har vi flere kanaler for blant annet utvikling, git, wiki (bl.a. deling av ressurser) og en generell kanal. Trello sin rolle for gruppen var mest å luke ut støy fra discordsamtalen og ha oversikt over hva som har blitt gjort og må gjøres.

Spillet er nå ferdigstilt og vi kan komfortabelt si at vi har laget et velfungerende spill. Vi synes kode-basen har holdt seg modulær, og vi har passet på at ingen klasser blir unødvendig store. Opprettelsen av 2 scener og interaksjonen dem mellom har vært noe krevende, spesielt med tanke på ViewPorten, men har løst seg veldig bra.
Det vi mener at vi har gjort bra er blant annet at vi startet tidlig på prosjektet. Vi var tidlig i gang med arbeidsfordeling og kom tidlig i gang med utviklingen av selve spillet. Vi synes også at vi har vært flinke til ha møter. Vi har hatt gode møter, hvor vi har fulgt målene våre og hatt sterkt oppmøte. I tillegg har vi vært bevisst på arbeidsoppgaver ved hjelp av kanban-tavlen/ Trello og Discord, slik har vi alltid hatt noe å gjøre på. Vi har også vært god på kommunikasjon som nevnt over.

Noe vi mener vi kunne gjort annerledes er å bruke en bedre deploy-strategi inspirert av praksis i arbeidslivet. Vi kunne hatt en main-branch som fungerte som "production"-miljø, og en develop-branch som utviklingsgren. Hver gang vi jobbet med en ny oppgave eller funksjon, kunne vi laget en ny branch (feature branch) knyttet til en "ticket" eller spesifikk oppgave. Disse kunne blitt merget inn i develop etter testing, og kun når develop var stabil og klar for en milepæl, f.eks. en oblig-frist (prototype, early version osv.), kunne vi ha merget develop inn i main.

***Dette har vi fikset siden sist:***

1. Fikset map-problem.
2. Testet nøye
3. Vi har også lagt til mer dokumentasjon av public metoder.
4. Ryddet i kodebasen, slik vi følger SOLID prinsippene.
