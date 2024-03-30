package dig.race

import com.badlogic.gdx.utils.Array

object Collider {
    fun checkCollisions(cars: Array<Car>, track: Track) {
        cars.forEach { car ->
            val x = car.pos.x.toInt() / TrackPiece.size
            val y = car.pos.y.toInt() / TrackPiece.size
            val piece = track.getAbsolutePos(x, y)
            track.collision(car, piece)
        }
    }

}
