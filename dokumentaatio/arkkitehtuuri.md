## Arkkitehtuurikuvaus
# Rakenne

### Pakkausrakenne
Ohjelmassa on kolme kerrosta, ui, dao ja domain. Ui pitää sisällään java.FX tehdyn käyttöliittymän, dao pitää huolen, että tiedot tallennetaan tietokantaan ja osataan tuoda sieltä ja domain pitää sisällään sovelluslogiikan.

### Käyttöliittymä
Idea on yksinkertainen. Ensin on kirjautumisikkuna mistä pääsee toiseen ikkunaan jossa voidaan luoda käyttäjä. Kun käyttäjä luodaan se tuo käyttäjän takaisin aloitusikkunaan josta voidaan kirjautua sisään. Kirjauduttuaan sisään päästään itse ohjelmaan, jossa voidaan lisätä, poistaa, muokata(toivottavasti), harjoituksia, niiden kestoa ja millä painoilla harjoituksia ollaan tehty.

### Sovelluslogiikka
Pääluokat ovat User ja Exercise. Service vastaa käyttöliittymän tarvitsemista metodeista kuten login ja ServiceCreateUser. Service toimii rajapinnat UserDataDao ja ExerciseDataDao toteuttavien luokkien UserDao ja ExerciseDao kautta.

### Tietokanta

Ohjelma tallentaa tiedot pysyvästi tietokantaan, tästä pitää huolen luokat UserDao, ExerciseDao ja Database. Database luokka luo tietokantataulut ja luo yhteyden tietokantaan.
