package com.github.fwilhe.incell

import javax.script.ScriptEngineManager

fun run() {
    val engine = ScriptEngineManager().getEngineByExtension("kts")!!
    val s = """import com.github.fwilhe.incell.*
        spreadsheet {
    column("sin(x)", ::sine)
}
        .print()"""
    engine.eval(s)
}

fun main(args: Array<String>) {
    run()
}