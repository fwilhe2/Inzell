# Inzell

![CI](https://github.com/fwilhe2/Inzell/workflows/CI/badge.svg)
![Latest Release](https://img.shields.io/github/v/tag/fwilhe2/Inzell?label=Latest%20Release)
![License](https://img.shields.io/github/license/fwilhe2/Inzell)

This project is meant to explore the kotlin programming language, learn about functional programming and implement a tiny spreadsheet-software.

## Name

The name is intended to be a pun on a popular spreadsheet software.
Also, [Inzell](https://en.wikipedia.org/wiki/Inzell) is a city in Bavaria.

## Concepts

The basic idea is that every column in a spreadsheet is usually some function.
It may be a constant function, if only one value is needed.
Input for the function is the row index.
This allows to make a function that does anything with that value, counting for example.

The function may return values of any type:

```kotlin
fun f(n: Int): Any
```

Some of such functions are provided by `StandardLibrary.kt`.

Ech `Column` has a title which is used as a header, and a function to compute its value.
An example is:

```kotlin
Column("Number of CPUs", ::powerOfTwo)
```

Finally, a `spreadsheet` is built like so:

```kotlin
spreadsheet {
    column("Expenses", expenses)
    column("Share of Expense", ::shareOfExpense)
    column("Cost with (fictional) tax", ::expenseWithTaxes)
}
```

### More Examples

See [Inzell-Examples](https://github.com/fwilhe2/Inzell-Examples/) for more examples, also ones not in Kotlin.

## Using the library

The maven artifacts are published to GitHub.
Use the following coordinates for a jvm application:

```
<dependency>
  <groupId>com.github.fwilhe.inzell</groupId>
  <artifactId>inzell-jvm</artifactId>
  <version>LATEST_VERSION_HERE</version>
</dependency>
```

Be sure to configure the GitHub repo in maven.

The library depends on the Kotlin standard library.

## Building

To install the library to your local Maven repo, type `./gradlew publishToMavenLocal` in the project root.

## License

This software is written by Florian Wilhelm and available under the MIT license (see `LICENSE` for details)
