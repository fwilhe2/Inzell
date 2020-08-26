package com.github.fwilhe.inzell

class SpreadsheetBuilder() {
    private val columns: MutableList<Column> = mutableListOf()
    private var theCaption: String = "(No caption provided)"

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
