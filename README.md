# TPV-Program

Hau IKER NISTAL-ek egindako TPV (Terminal Puntua) programaren asignaturarako proiektu pertsonala da.
## Azalpena


TPV-Program JavaFX erabiliz garatutako aplikazio bat da, produktuak kudeatzeko eta eskaerak egiteko. Aplikazio honek produktuak erakusten ditu, eskaerak egiten ditu eta PDF formatuan txostenak sortzen ditu.

##  Demo-Video

## Klase Diagrama


## Fitxategi Garrantzitsuak

- **Main.java**: Aplikazioaren sarrera puntua.
- **ProductsController.java**: Produktuen kudeaketa eta eskaeren prozesamendua.
- **KategoriesController.java**: Kategorien kudeaketa.
- **ProductSelectSave.java**: Fitxategien kudeaketa (gorde eta kargatu).
- **DBConnection.java**: Datu-basearekin konexioa.
- **FXML fitxategiak**: GUI diseinua (Login, Signup, Kategories, Product).

## Datu-basearekin Konexioa

Datu-basearekin konexioa egiteko, `DBConnection.java` fitxategia erabiltzen da. PostgreSQL datu-basearekin konexioa ezartzen du honako URL, erabiltzaile eta pasahitzarekin:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
private static final String USER = "postgres";
private static final String PASSWORD = "Admin123";
```

Konexioa ezartzeko metodoa:
```java
public static Connection connect() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}
```

PostgreSQL datu-basearekin konexioa ezartzeko informazio gehiago lortzeko, ikus PostgreSQL JDBC Driver.

## FXML Fitxategiak

- **Login.fxml**: Erabiltzaileak aplikaziora sartzeko login pantaila.
  
  ![Login](/src/main/resources/images/logscr.jpg)
  
- **Signup.fxml**: Erabiltzaile berriak erregistratzeko pantaila.
    
  ![SignUp](/src/main/resources/images/signscr.jpg)
  
- **Kategories.fxml**: Kategorien hautaketa pantaila. Kategoriak erakusten ditu eta erabiltzaileak kategoria bat hautatzeko aukera ematen du.
  
  ![Kategories](/src/main/resources/images/kategories.jpg)

- **Product.fxml**: Produktuen hautaketa pantaila. Produktuak erakusten ditu eta erabiltzaileak produktuak hautatzeko eta kantitatea sartzeko aukera ematen du.

  ![Products](/src/main/resources/images/products.jpg)

## Garapena


### Model Klaseak

- **Kategories.java**: Kategoria objektuak definitzen ditu, `id` eta `name` atributuekin.
- **Login.java**: Login objektuak definitzen ditu, `username` eta `password` atributuekin.
- **Product.java**: Produktu objektuak definitzen ditu, `id_prod`, `name`, `precio`, eta `idKategoria` atributuekin.
- **Signup.java**: Signup objektuak definitzen ditu, `username` eta `password` atributuekin.

## Garatzailea

- **Iker Nistal** - Garatzailea




