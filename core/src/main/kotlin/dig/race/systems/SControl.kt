package dig.race.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys.*
import dig.race.CarBuilder
import dig.race.comp.CControllable
import dig.race.comp.CDirection

class SControl : IteratingSystem(Family.all(CDirection::class.java, CControllable::class.java).get()) {

    private val cVel: ComponentMapper<CDirection> = ComponentMapper.getFor(CDirection::class.java)
    private val keysToActions = mapOf(
        listOf(
            UP, W, Z
        ) to { vel: CDirection, delta: Float ->
            vel.acceleration += CarBuilder.acceleration * delta
            vel.acceleration.coerceIn(0f, CarBuilder.maxAcceleration)
        },
        listOf(
            DOWN, S
        ) to { vel: CDirection, delta: Float ->
            vel.acceleration += CarBuilder.acceleration * delta
            vel.acceleration.coerceIn(0f, CarBuilder.maxAcceleration)
        },
        listOf(
            LEFT, A, Q
        ) to { vel: CDirection, delta: Float ->
            vel.turningAngle += CarBuilder.turnSpeed * delta
            vel.turningAngle.coerceIn(-CarBuilder.maxTurnSpeed, CarBuilder.maxTurnSpeed)
        },
        listOf(
            RIGHT, D
        ) to { vel: CDirection, delta: Float ->
            vel.turningAngle -= CarBuilder.turnSpeed * delta
            vel.turningAngle.coerceIn(-CarBuilder.maxTurnSpeed, CarBuilder.maxTurnSpeed)
        }
    )

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val vel = cVel[entity]
        keysToActions.forEach { (keys, action) ->
            keys.forEach {
                if (Gdx.input.isKeyPressed(it)) {
                    action.invoke(vel, deltaTime)
                }
            }
        }
    }
}
