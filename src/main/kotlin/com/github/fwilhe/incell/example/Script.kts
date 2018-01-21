// Run with https://github.com/holgerbrandl/kscript for dependency resolution

import com.github.fwilhe.incell.sine
import com.github.fwilhe.incell.spreadsheet

//DEPS com.github.fwilhe.incell:incell:0-unreleased

spreadsheet {
    column("sin(x)", ::sine)
}
        .print()
