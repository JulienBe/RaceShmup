package dig.race.comp

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool.Poolable
import dig.race.Pos

data class CPos(var pos: Pos = Pos()) : Component, Poolable {
    override fun reset() {
        pos.reset()
    }
}
