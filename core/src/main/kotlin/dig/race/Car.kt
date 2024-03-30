package dig.race

class Car(
    val pos: Pos = Pos(),
    val dir: Dir = Dir(),
    val maxSpeed: Float = 1200f
) {

    private val keysToActions = mapOf(
        Action.UP to { vel: Dir, delta: Float ->
            vel.acceleration += Builder.acceleration * delta
            vel.acceleration.coerceIn(0f, Builder.maxAcceleration)
        },
        Action.DOWN to { vel: Dir, delta: Float ->
            vel.acceleration -= Builder.acceleration * delta
            vel.acceleration.coerceIn(0f, Builder.maxAcceleration)
        },
        Action.LEFT to { vel: Dir, delta: Float ->
            vel.turningAngle += Builder.turnSpeed * delta
            vel.turningAngle.coerceIn(-Builder.maxTurnSpeed, Builder.maxTurnSpeed)
        },
        Action.RIGHT to { vel: Dir, delta: Float ->
            vel.turningAngle -= Builder.turnSpeed * delta
            vel.turningAngle.coerceIn(-Builder.maxTurnSpeed, Builder.maxTurnSpeed)
        }
    )

    fun act(actions: Set<Action>, delta: Float) {
        actions.forEach { action ->
            keysToActions[action]?.invoke(dir, delta)
        }
    }

}
