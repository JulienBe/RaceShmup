package dig.race.comp

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool.Poolable
import dig.race.lwjgl3.Pos

data class CPos(var pos: Pos) : Component, Poolable {
    override fun reset() {
        pos.reset()
    }
}
