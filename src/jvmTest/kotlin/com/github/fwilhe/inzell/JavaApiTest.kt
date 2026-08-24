package com.github.fwilhe.inzell

import kotlin.test.Test
import kotlin.test.assertEquals

class JavaApiTest {
    @Test
    fun buildSnapshotsColumns() {
        val builder = SpreadsheetBuilder()
            .addColumn(Column("first") { 1 })
        val sheet = builder.build()

        builder.addColumn(Column("second") { 2 })

        assertEquals(listOf("first"), sheet.columns.map { it.title })
    }
}