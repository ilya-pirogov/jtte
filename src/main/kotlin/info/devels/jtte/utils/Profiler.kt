package info.devels.jtte.utils


inline fun profile(text: String, cb: () -> Unit) {
    val milestone = System.nanoTime()
    cb()
    val elapsed = System.nanoTime() - milestone
    println("%s. Elapsed: %.3f ms".format(text, elapsed.toDouble() / 1000000.0))
}