package dig.race

import com.badlogic.gdx.math.Vector2

class Car(
    val pos: Pos = Pos(),
    var currentSpeed: Float = 0f,
    val dir: Vector2 = Vector2(),
    var accelerationTime: Float = 0f,
    var currentTurningAngle: Float = 0f,
    var turning: Float = 0f
) {

    private val keysToActions = mapOf(
        Action.UP to { delta: Float ->
            accelerationTime += delta * 2
            accelerationTime = accelerationTime.coerceIn(0f, timeToMaxSpeed)
         },
        Action.DOWN to { delta: Float ->
            accelerationTime -= delta * 2
            accelerationTime = accelerationTime.coerceIn(0f, timeToMaxSpeed)
       },
        Action.LEFT to { delta: Float -> turning += turnSpeed * delta },
        Action.RIGHT to { delta: Float -> turning -= turnSpeed * delta }
    )

    fun act(actions: Set<Action>, delta: Float) {
        actions.forEach { action ->
            keysToActions[action]?.invoke(delta)
        }
    }

    companion object {
        const val timeToMaxSpeed: Float = 2f
        const val maxSpeed: Float = 240f
        const val turnSpeed = 4.5f
        const val maxTurningAngle = 9f
    }

}
