package dig.race

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import dig.race.comp.CCamFocus
import dig.race.comp.CControllable
import dig.race.comp.CPos
import dig.race.comp.CDirection

object CarBuilder {
    const val maxAcceleration = 3f
    const val acceleration = 1.5f
    const val turnSpeed = 4.5f
    const val maxTurnSpeed = 9f

    fun addCar(engine: PooledEngine) {
        val entity: Entity = engine.createEntity()
        val pos = engine.createComponent(CPos::class.java)
        val vel = engine.createComponent(CDirection::class.java)
        val control = engine.createComponent(CControllable::class.java)
        val camFocus = engine.createComponent(CCamFocus::class.java)
        entity.add(pos)
        entity.add(vel)
        entity.add(control)
        entity.add(camFocus)
        engine.addEntity(entity)
    }
}
