# Rucola

Very simple information retrieval library for explanatory purposes.

## Usage

```
ruby -Ilib example/rucola.rb [path [limit [dump|load]]]
```

## Parameters

- `path` Path to TTL file.
- `limit` Read lines limit.
- `dump|load` Dumps or loads `path.corpus` (in case of load limit is ignored).

## Samples

```
ruby -Ilib example/rucola.rb data/long_abstracts_sk.ttl 20
ruby -Ilib example/rucola.rb data/long_abstracts_sk.ttl 20 dump
ruby -Ilib example/rucola.rb data/long_abstracts_sk.ttl 20 load
```

## Output

```
Parsing input data ... done (0.033s)
Adding documents to corpus ... done (0.502s)
Corpus:
  model = Rucola::Models::NaiveVectorSpace
  extractor = Rucola::Extractors::Splitter Rucola::Extractors::Deletor
  weighter = Rucola::Weighters::TfIdf
  metric = Rucola::Metrics::NaiveCosineSimilarity
  size = 19
Finding similar documents ... done (0.059s)
Documents similar to http://sk.dbpedia.org/resource/Taekkyon:
  1.000000000000 http://sk.dbpedia.org/resource/Taekkyon Taekkyon (slovenská výslovnosť \"thäkkjon, kor. 택견]) je starý kórejský bojový…
  0.122581520698 http://sk.dbpedia.org/resource/Turingov_test Turingov test sformuloval Alan Turing. Pokúša sa dať odpoveď na otázku ako…
  0.113102005726 http://sk.dbpedia.org/resource/Astronómia Astronómia, čo etymologicky znamená „zákon hviezd,“ (z gréčtiny: αστρονομία =…
  0.108618681326 http://sk.dbpedia.org/resource/Matematika Matematika (z gr. μαθηματικός (mathematikós)= „milujúci poznanie“ > μάθημα (máthe
  0.097085239771 http://sk.dbpedia.org/resource/Filozofia Filozofia (z gr. láska k múdrosti: filein = ľúbiť a sofia = múdrosť) je štúdia…
  0.095146311487 http://sk.dbpedia.org/resource/Francúzsko Francúzsko (po francúzsky La France, výslovnosť IPA [fʀɑ̃s]), dlhý tvar…
  0.094464764572 http://sk.dbpedia.org/resource/Fyzika Fyzika (zo starogr. φυσικός (fysikos) = \"prirodzený\" a φύσις (fysis) = \"prírod
  0.083612269175 http://sk.dbpedia.org/resource/RTFM RTFM je skratka v internetovom slangu pre frázu Read The Fucking Manual (po…
  0.083077316343 http://sk.dbpedia.org/resource/Literatúra Literatúra (z latinčiny litteratura = písmo / jazykoveda) : a) (tiež…
  0.077130157291 http://sk.dbpedia.org/resource/Tantrizmus Tantrizmus alebo tantra (-sanskritský kmeň; sanskritský nominatív: tantram) je…
  0.074306610693 http://sk.dbpedia.org/resource/Weblog Weblog (z anglického web log – doslova: webový denník) alebo skrátene a…
  0.071876908378 http://sk.dbpedia.org/resource/Tibet_(historické_územie) Tibet je historické a geografické územie v Ázii približne zodpovedajúce…
  0.069124505516 http://sk.dbpedia.org/resource/Biológia Biológia (grec. bios, βιος – život; logos, λογος – slovo) je náuka o živote.…
  0.067488887630 http://sk.dbpedia.org/resource/Internet Internet, hovorovo net alebo sieť je verejne dostupný celosvetový systém…
  0.063952947939 http://sk.dbpedia.org/resource/Egypt Egypt (arab. مصر‎ Misr) dlhý názov Egyptská arabská republika, je severoafrická…
  0.060429855663 http://sk.dbpedia.org/resource/Václav_Havel Václav Havel (* 5. október 1936, Praha, ČSR – † 18. december 2011, Hrádeček)…
  0.051276432870 http://sk.dbpedia.org/resource/Alan_Mathison_Turing Alan Mathison Turing (* 23. jún 1912, Londýn, Spojené kráľovstvo – † 7. jún 1954,
  0.039163022499 http://sk.dbpedia.org/resource/Zoznam_štátov Toto je zoznam štátov.
```

