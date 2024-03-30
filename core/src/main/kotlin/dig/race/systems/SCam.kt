package dig.race.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import dig.race.CurrentCamFocus
import dig.race.comp.CCamFocus
import dig.race.comp.CPos

class SCam : IteratingSystem(Family.all(CCamFocus::class.java, CPos::class.java).get()) {

    private val cPos: ComponentMapper<CPos> = ComponentMapper.getFor(CPos::class.java)

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val pos = cPos[entity]

        CurrentCamFocus.x = pos.pos.x
        CurrentCamFocus.y = pos.pos.y
        CurrentCamFocus.z = pos.pos.z + 5f
    }
}
