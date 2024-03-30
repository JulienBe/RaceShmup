package dig.race.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys.*
import dig.race.comp.CControllable
import dig.race.comp.CVelocity

class SControl : IteratingSystem(Family.all(CVelocity::class.java, CControllable::class.java).get()) {

    private val cVel: ComponentMapper<CVelocity> = ComponentMapper.getFor(CVelocity::class.java)
    private val keysToActions = mapOf(
        listOf(
            UP, W, Z
        ) to { vel: CVelocity, delta: Float ->
            vel.vel.y += 1f * delta
        },
        listOf(
            DOWN, S
        ) to { vel: CVelocity, delta: Float ->
            vel.vel.y += -1f * delta
        },
        listOf(
            LEFT, A, Q
        ) to { vel: CVelocity, delta: Float ->
            vel.vel.x += -1f * delta
        },
        listOf(
            RIGHT, D
        ) to { vel: CVelocity, delta: Float ->
            vel.vel.x += 1f * delta
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
