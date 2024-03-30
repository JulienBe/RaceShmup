package dig.race

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.utils.Array

object Builder {
    const val maxAcceleration = 3f
    const val acceleration = 1.5f
    const val turnSpeed = 4.5f
    const val maxTurnSpeed = 9f

    const val trackWidth = 100
    const val trackHeight = 100

    fun createCar(): Car {
        return Car()
    }

    fun addTrack(): Array<TrackPiece> {
        val pieces = Array<TrackPiece>()
        for (x in 0 until trackWidth) {
            for (y in 0 until trackHeight) {
                val piece = TrackPiece(
                    index = x * trackHeight + y,
                    x, y
                )
                pieces.add(piece)
            }
        }
        return pieces
    }
}
