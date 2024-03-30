package dig.race

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Car(
    val pos: Pos = Pos(),
    val dir: Dir = Dir()
) : Drawable {

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

    override fun draw(batch: SpriteBatch, texture: Texture) {
        batch.color = Color.CORAL
        batch.draw(texture, pos.x.round(), pos.y.round(), 1f, 1f)
    }

    fun act(actions: Set<Action>, delta: Float) {
        actions.forEach { action ->
            keysToActions[action]?.invoke(dir, delta)
        }
    }
}
