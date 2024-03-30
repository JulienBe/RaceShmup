package dig.race

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dig.race.TrackPiece.Companion.size

class TrackPiece(
    val index: Int,
    val x: Int,
    val y: Int
) {

    fun draw(batch: SpriteBatch, image: Texture) {
        batch.draw(image, x.toFloat(), y.toFloat(), size.toFloat(), size.toFloat())
    }

    companion object {
        const val size = 2
        const val sizeF = size.toFloat()
        val noPiece = TrackPiece(-1, -1, -1)
    }
}

class Track {

    val pieces = Array(trackWidth) { Array<TrackPiece>(trackHeight) { TrackPiece.noPiece } }

    init {
        for (x in 0 until trackWidth) {
            for (y in 0 until trackHeight) {
                val piece = TrackPiece(
                    index = x * 100 + y,
                    x * size, y * size
                )
                pieces[x][y] = piece
            }
        }
    }

    fun get(x: Int, y: Int): TrackPiece {
        if (x < 0 || y < 0 || x >= trackWidth || y >= trackHeight) {
            return TrackPiece.noPiece
        }
        return pieces[x][y]
    }

    fun act() {
    }

    fun collision(car: Car, piece: TrackPiece) {
        // replace by the noPiece
        pieces[piece.x / size][piece.y / size] = TrackPiece.noPiece
    }

    fun getAbsolutePos(x: Int, y: Int): TrackPiece {
        return get(x, y)
    }

    companion object {
        const val trackWidth = 100
        const val trackHeight = 100
    }
}
