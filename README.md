# Java_project_team
Java nyelven írt grafikus felülettel rendelkező játék. Csapatmunkaként csináltuk egy egyetemi tárgyra. Minden dokumentáció megtalálható magyar nyelven egy összesített pdf-ben.

# A játék alaphelyzete:
Egy labirintusban vannak elhelyezve hallgatók és oktatók. A hallgatók akkor nyernek, ha
megtalálják a szobákban a logarlécet, az oktatók célja ezt megakadályozni. Minden karakter 
képes szobából szobába közlekedni. Minden szobának van egy befogadóképessége, aminél 
több karakter nem tartózkodhat egyszerre az adott szobában, ez szobánként változhat.
Ha az oktatók egy szobába kerülnek egy hallgatóval, akkor a hallgató kibukik az egyetemről, 
ezzel a hallgatónak vége a játéknak. Ha minden hallgató kiesik, vége a játéknak, a játékosok 
vesztettek. Ez alól vannak kivételek, ha a hallgatónál vannak bizonyos tárgyak, ekkor túlélheti 
a találkozást az oktatókkal.

Tárgyak:
- Logarléc: A hallgatók győzelmi feltétele ennek megtalálása.
- TVSZ denevérbőr: Ha a hallgató rendelkezik ezzel a tárggyal, három alkalommal 
megvédi a hallgatót az oktatókkal való találkozástól.
- Szent söröspohár: Ha a hallgató felveszi, adott ideig megvédi a hallgatót az oktatókkal 
való találkozás hatásától.
- Nedves táblatörlő: A vele egy szobában lévő oktatókat egy adott ideig megbénítja, 
addig nem tudják elhagyni a szobát.
- Dobozolt káposztás camembert: Egy szobában elhelyezve az adott szobából mérges 
gázos szobát csinál, akik egy ilyen szobába lépnek FFP2 maszk (lásd alább) nélkül,
azok egy ideig eszméletüket vesztik és minden náluk lévő tárgyat eldobnak.
- FFP2 maszk: Akinek van ilyen tárgya, védettséget kap egy időre a mérgezett 
szobákban, így azok hatása nem érvényes a birtokosra.
- Tranzisztor: Két tranzisztort felvéve össze lehet őket kapcsolni. Ekkor ha egyet 
leteszünk egy szobába és aktiváljuk, akkor egy másik szobából a másik tranzisztort 
aktiválva és letéve az elsőnek letett tranzisztorhoz teleportálhatunk.

A szobákban különféle tárgyak lehetnek (ilyen a Logarléc is), amiket a karakterek magukhoz 
vehetnek. Ez alól kivétel a Logarléc, amit csak a hallgatók vehetnek fel. Egy karakternél
(hallgató, oktató) legfeljebb 5 tárgy lehet. Tárgyakat csak a hallgatók képesek használni, 
továbbá, ha egy oktató tárgyat talál, mindenképpen felveszi azt, ha nincs helye, akkor a 
legrégebben nála lévő tárgyat eldobja, majd az újat rakja a helyére.
Szobák működése:
A szobákat ajtók kötik össze. Vannak normális ajtók, melyek mindkét irányban 
használhatóak, vannak olyan ajtók, melyek csak az egyik irányban nyílnak, a másik irányban 
nem lehet őket használni. Továbbá vannak ajtók, amik időről-időre eltűnnek és előjönnek, és 
csak akkor használhatóak, ha éppen láthatóak.
A szobák véletlenszerűen összeolvadhatnak vagy szétválhatnak. Ha két szoba egyesül, akkor 
az új szobának az eredeti szobáknak minden szomszédja a szomszédja lesz. Minden, az 
eredeti szobában jelenlévő tárgy az új szobában is jelen lesz. Ha két szoba ketté osztódik, a 
szobák az eredeti szoba szomszédjain megosztoznak, egy szomszéd vagy az egyik, vagy a 
másik létrejövő szoba szomszédja lesz. Az eredeti szobában lévő tárgyak véletlenszerűen 
vagy az egyik, vagy a másik új szobába kerülnek
