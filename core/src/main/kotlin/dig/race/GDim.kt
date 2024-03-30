package dig.race

data class GDim(var w: Float, var h: Float) {

    var hw: Float = w / 2
    var hh: Float = h / 2
    var ratio: Float = w / h
    val wI: Int get() = w.toInt()
    val hI: Int get() = h.toInt()

    fun update(width: Int, height: Int) {
        w = width.toFloat()
        h = height.toFloat()
        hw = w / 2
        hh = h / 2
        ratio = w / h
    }
}
