package dig.race

import com.badlogic.gdx.math.Vector3

data class Dir(
    var vec: Vector3 = Vector3(),
    var acceleration: Float = 0f,
    var turningAngle: Float = 0f,
    var angle: Float = 0f,
    var speed: Float = 0f
)
