package com.github.fwilhe.inzell


class SpreadsheetBuilder() {
    val columns: MutableList<Column> = mutableListOf()
    var theCaption: String = "(No caption provided)"
    fun addColumn(column: Column): SpreadsheetBuilder {
        columns.add(column)
        return this
    }

    fun setCaption(caption: String): SpreadsheetBuilder {
        theCaption = caption
        return this
    }

    fun build(): Sheet = Sheet(columns, theCaption)
}


fun javaSpreadsheetBuilder(columns: List<Column>, theCaption: String = "(No caption provided)"): Sheet {
    return Sheet(columns, theCaption)
}