package com.github.fwilhe.inzell

fun spreadsheet() = Sheet(listOf(Column("a", ::random)), "abc")
fun printer(s: Any) {
    MarkdownPrinter(s as Sheet).printToStandardOut()
}
fun foo() {
    MarkdownPrinter(Sheet(listOf(Column("a", ::random)), "abc")).printToStandardOut()
}

