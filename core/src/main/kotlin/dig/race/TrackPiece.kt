package dig.race

import com.badlogic.gdx.utils.Array

class TrackPiece(
    val index: Int,
    val x: Int,
    val y: Int
)

class Track {

    val pieces = Array<TrackPiece>()

    init {
        for (x in 0 until trackWidth) {
            for (y in 0 until trackHeight) {
                val piece = TrackPiece(
                    index = x * 100 + y,
                    x * pieceSize, y * pieceSize
                )
                pieces.add(piece)
            }
        }
    }

    fun get(x: Int, y: Int): TrackPiece {
        return pieces[x * 100 + y]
    }

    fun act() {

    }

    companion object {
        const val trackWidth = 100
        const val trackHeight = 100
        const val pieceSize = 5
        const val pieceSizeF = pieceSize.toFloat()
    }

}
