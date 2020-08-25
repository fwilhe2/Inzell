# Inzell

This project is meant to explore the kotlin programming language, learn about functional programming and implement a tiny spreadsheet-software.

## Name

The name is intended to be a pun on a popular spreadsheet software.
Also, [Inzell](https://en.wikipedia.org/wiki/Inzell) is a city in Bavaria.

## Concepts

The basic idea is that every column in a spreadsheet is usually some function.
It may be a constant function, if only one value is needed.
Input for the function is the column-number.
This allows to make a function that does anything with that value, counting for example.

The function must return a Double value, hence it's signature must look like

```
fun f(n: Int): Double
```

Some of such functions are provided by `StandardLibrary.kt`.

Ech `Column` has a title that is used as a header, and a function to compute its value.
An example is

```
Column("Number of CPUs", ::powerOfTwo)
```

The design goal is to hide all implementation-code from the user and allow to create easily programs to compute some data.
The default behavior is to write the CSV-data to stdout, which allows to put the tool in a unix pipe, for example to use tools such as `csv2md`.

## Building

To install the library to your local Maven repo, type `./gradlew publishToMavenLocal` in the project root.

## License

This software is written by Florian Wilhelm and available under the MIT license (see `LICENSE` for details)
