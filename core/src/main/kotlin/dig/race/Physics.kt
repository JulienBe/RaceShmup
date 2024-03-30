package dig.race

import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array

class Physics {
    fun moveCars(delta: Float, cars: Array<Car>) {
        cars.forEach { car ->
            with(car.dir) {
                speed += acceleration
                angle += turningAngle
                speed = speed.coerceIn(0f, car.maxSpeed * delta)

                // bypass previous
                vec.set(Vector3.X)
                vec.rotate(Vector3.Z, angle)
                vec.setLength(speed)

                speed *= 0.99f
                acceleration *= 0.99f
                turningAngle *= 0.97f
            }

            car.pos.update(car.dir.vec.x * delta, car.dir.vec.y * delta, car.dir.vec.z * delta)
        }
    }
}
