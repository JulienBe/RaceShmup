package dig.race.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import dig.race.comp.CPos

class SDraw2D : IteratingSystem(Family.all(CPos::class.java).get()) {

    private val cPos: ComponentMapper<CPos> = ComponentMapper.getFor(CPos::class.java)

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val pos = cPos[entity].pos
        println("Drawing entity at ${pos.x}, ${pos.y}")
    }

}
