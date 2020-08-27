//import com.github.fwilhe.inzell.HtmlPrinter
//import kotlinx.html.*
//import kotlinx.html.dom.append
//import kotlinx.html.js.onClickFunction
//import org.w3c.dom.Document
//
//fun HtmlPrinter.browserPrint(document: Document, rowCount: Int = 10) {
//    document.body!!.append.div {
//        button {
//            +"+"
//            onClickFunction = { this@browserPrint.browserPrint(document, rowCount + 1) }
//        }
//        button {
//            +"-"
//            onClickFunction = { this@browserPrint.browserPrint(document, rowCount - 1) }
//        }
//        table {
//            caption { +sheet.caption }
//            tr {
//                repeat(sheet.columns.size) { columnIndex ->
//                    th {
//                        +sheet.columns[columnIndex].title
//                    }
//                }
//            }
//            repeat(rowCount) { row ->
//                tr {
//                    sheet.columns.forEach {
//                        td {
//                            +it.eval(row).toString()
//                        }
//                    }
//                }
//            }
//        }
//    }
//}