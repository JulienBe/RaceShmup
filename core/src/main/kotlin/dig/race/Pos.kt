package dig.race

import com.badlogic.gdx.math.Vector3

class Pos {

    private val pos: Vector3 = Vector3()
    val xI: Int get() = pos.x.toInt()
    val yI: Int get() = pos.y.toInt()
    val zI: Int get() = pos.z.toInt()
    val x: Float get() = pos.x
    val y: Float get() = pos.y
    val z: Float get() = pos.z

    fun reset() {
        pos.set(0f, 0f, 0f)
    }
}
