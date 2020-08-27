import com.github.fwilhe.inzell.Column
import com.github.fwilhe.inzell.CsvPrinter
import com.github.fwilhe.inzell.Sheet
import com.github.fwilhe.inzell.cosine

@ExperimentalJsExport
@JsExport
fun spreadsheet(columns: List<Column>): Sheet {
    return Sheet(columns)
}

@ExperimentalJsExport
@JsExport
fun column(title: String, fn: (Int) -> Double): Column {
    return Column(title, fn)
}

@ExperimentalJsExport
@JsExport
fun print(sheet: Sheet) {
    println(sheet.columns.first().eval(3))
}