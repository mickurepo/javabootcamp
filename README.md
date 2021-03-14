# Autorstwo i przeznaczenie:
Program został wykonany na potrzeby rekrutacji do firmy Core Services Sp. z o.o. w 2021r.
Autor: Michał Kurzyk

# Wymagane narzędzia:
Wydawca nie gwarantuje poprawnego działania programu z narzędziami w wersji starszymi niż wypisano poniżej:
- Maven 3.6.3
- Java JDK 15.0.2

# Założenia projektu:
- Silnik projektu został stworzony w sposób potencjalnie umożliwiający łatwy rozwój, lub wykorzystanie go w innym projekcie, ale jest to tylko jego dodatkowy atut, a nie wymóg od klienta.
- Program powstał bez głównego nacisku na front-end, wersję front-endu traktuje się jako ułatwienie dla użytkownika na start, a nie jako finalną wersje.
- Projekt nie jest produktem komercyjnym, więc jego ewentualne luki i niedopracowania pozostają nierozwiązane.
- Wejsciowe pliki XML i CSV muszą być poprawne (& &amp;)
- Kategorie piosenek nie są dynamiczne i ich baza (lista) znajduje się w kodzie programu- utwory o kategorii nierozpoznanej nie zostaną wyświetlone. (problem uwarunkowany poprzez wymóg klienta- enum)
- Tekst widziany na ekranie jest wpisany na sztywno do programu (hardcode). Wersją językową program jest j. angielski i nie zakładano innej.

# Dodatkowe informacje:
- W folderze "Additional" dołączonym do projektu znajdują się pliki pomocnicze data.xml i data.csv
- Żeby wyzerować głosy dla danej piosenki, kliknij PPM w polu "Votes"
- Raport wg. kategorii działa w następujący sposób: sortuje wszystkie utwory po danej kategorii, po czym dodatkowo po ilości głosów. Wypisane są wszystkie odpowiednio posortowane utwory, nie wyszczególnia się żadnej kategorii.
- Cały program został napisany w ściśle obiektowy sposób
- Obsługa plików XML i CSV została wykonana na dwa sposoby: za pomocą bibliotek (encje), i w sposób klasyczny (demonstracja możliwości rowiązania problemu na dwa sposoby). Wspomniane biblioteki to JAXB i OpenCSV
- Kod z front-endu został odseparowany od back-endu
- Kod funkcjonalny został opisane. Do każdej funkcji znajduje się opis w formie komentarzy nad nim