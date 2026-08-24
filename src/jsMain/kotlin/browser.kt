import com.github.fwilhe.inzell.HtmlPrinter
import org.w3c.dom.Document
import org.w3c.dom.HTMLElement

fun HtmlPrinter.browserPrint(document: Document, rowCount: Int = numberOfRows) {
	val body = document.body ?: return
	body.innerHTML = ""

	val container = document.createElement("div")
	val decreaseButton = document.createElement("button") as HTMLElement
	decreaseButton.textContent = "-"
	decreaseButton.setAttribute("aria-label", "Show fewer rows")
	val increaseButton = document.createElement("button") as HTMLElement
	increaseButton.textContent = "+"
	increaseButton.setAttribute("aria-label", "Show more rows")
	container.appendChild(decreaseButton)
	container.appendChild(increaseButton)
	body.appendChild(container)

	var currentRowCount = rowCount.coerceAtLeast(0)
	fun render() {
		body.querySelector("table")?.let { body.removeChild(it) }
		val table = document.createElement("table")
		val caption = document.createElement("caption")
		caption.textContent = sheet.caption
		table.appendChild(caption)

		val header = document.createElement("tr")
		sheet.columns.forEach { column ->
			val cell = document.createElement("th")
			cell.textContent = column.title
			header.appendChild(cell)
		}
		table.appendChild(header)

		repeat(currentRowCount) { row ->
			val tableRow = document.createElement("tr")
			sheet.columns.forEach { column ->
				val cell = document.createElement("td")
				cell.textContent = column.eval(row).toString()
				tableRow.appendChild(cell)
			}
			table.appendChild(tableRow)
		}
		body.appendChild(table)
	}

	decreaseButton.addEventListener("click", {
		currentRowCount = (currentRowCount - 1).coerceAtLeast(0)
		render()
	})
	increaseButton.addEventListener("click", {
		currentRowCount += 1
		render()
	})
	render()
}