package dig.race

import com.badlogic.gdx.math.Vector3

class Pos {

    private val pos: Vector3 = Vector3()
    private val previousPos: Vector3 = Vector3()
    val xI: Int get() = pos.x.toInt()
    val yI: Int get() = pos.y.toInt()
    val zI: Int get() = pos.z.toInt()
    val x: Float get() = pos.x
    val prevX: Float get() = previousPos.x
    val y: Float get() = pos.y
    val prevY: Float get() = previousPos.y
    val z: Float get() = pos.z
    val prevZ: Float get() = previousPos.z

    fun set(x: Float, y: Float, z: Float) {
        previousPos.set(pos)
        pos.set(x, y, z)
    }

    fun update(x: Float, y: Float, z: Float = 0f) {
        previousPos.set(pos)
        pos.add(x, y, z)
    }

    fun reset() {
        pos.set(0f, 0f, 0f)
        previousPos.set(0f, 0f, 0f)
    }
}
