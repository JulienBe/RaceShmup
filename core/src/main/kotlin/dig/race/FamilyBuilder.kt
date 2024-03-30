package dig.race

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import dig.race.comp.CControllable
import dig.race.comp.CPos
import dig.race.comp.CVelocity

object FamilyBuilder {
    fun addCar(engine: PooledEngine) {
        val entity: Entity = engine.createEntity()
        val pos = engine.createComponent(CPos::class.java)
        val vel = engine.createComponent(CVelocity::class.java)
        val control = engine.createComponent(CControllable::class.java)
        entity.add(pos)
        entity.add(vel)
        entity.add(control)
        engine.addEntity(entity)
    }
}
