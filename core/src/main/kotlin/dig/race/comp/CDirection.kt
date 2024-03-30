package dig.race.comp

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Pool.Poolable

data class CDirection(
    var vec: Vector3 = Vector3(),
    var acceleration: Float = 0f,
    var turningAngle: Float = 0f,
    var angle: Float = 0f,
    var speed: Float = 0f
) : Component, Poolable {

    override fun reset() {
        vec.set(0f, 0f, 0f)
        acceleration = 0f
        turningAngle = 0f
        angle = 0f
        speed = 0f
    }
}
