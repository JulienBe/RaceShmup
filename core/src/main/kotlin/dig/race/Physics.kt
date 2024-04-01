package dig.race

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import kotlin.math.min

class Physics {

    private val carInterpolation = Interpolation.circleOut
    private val workingVector = Vector2()

    fun moveCars(delta: Float, cars: Array<Car>) {
        cars.forEach { car ->
            with(car) {
                currentTurningAngle += turning * delta
                currentTurningAngle = currentTurningAngle.coerceIn(-Car.maxTurningAngle, Car.maxTurningAngle)

                currentSpeed = carInterpolation.apply(0f, Car.maxSpeed, (accelerationTime / Car.timeToMaxSpeed).coerceIn(0f, 1f))
                println("$accelerationTime : $currentSpeed")
                accelerationTime -= delta

                // bypass previous
                workingVector.set(Vector2.X)
                workingVector.rotateDeg(currentTurningAngle)
                workingVector.setLength(currentSpeed)


                pos.update(workingVector.x * delta, workingVector.y * delta, 0f)
            }
        }
    }
}
