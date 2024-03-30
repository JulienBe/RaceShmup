package dig.race.comp

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Pool.Poolable

data class CVelocity(var vel: Vector3 = Vector3()) : Component, Poolable {
    override fun reset() {
        vel.set(0f, 0f, 0f)
    }
}
