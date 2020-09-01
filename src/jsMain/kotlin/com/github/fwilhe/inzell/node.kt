package com.github.fwilhe.inzell

@ExperimentalJsExport
@JsExport
fun spreadsheet(columns: List<Column>) = Sheet(columns, "abc")

@ExperimentalJsExport
@JsExport
fun column(title: String, function: (Int) -> Any) = Column(title, function)

@JsExport
fun printer(s: Any) {
    MarkdownPrinter(s as Sheet).printToStandardOut()
}
fun foo() {
    MarkdownPrinter(Sheet(listOf(Column("a", ::random)), "abc")).printToStandardOut()
}

@JsExport
fun withArgs(a: Int) = a - 3