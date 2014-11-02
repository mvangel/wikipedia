# Rucola

Very simple information retrieval library for explanatory purposes.

## Usage

```
ruby -Ilib example/rucola.rb [path [limit [dump|load]]]
```

## Parameters

- `path` path to TTL file
- `limit` read lines limit
- `dump|load` dumps or loads `path.corpus` (in case of load limit is ignored)

## Samples

```
ruby -Ilib example/rucola.rb data/long_abstracts_sk.ttl 20
ruby -Ilib example/rucola.rb data/long_abstracts_sk.ttl 20 dump
ruby -Ilib example/rucola.rb data/long_abstracts_sk.ttl 20 load
```

