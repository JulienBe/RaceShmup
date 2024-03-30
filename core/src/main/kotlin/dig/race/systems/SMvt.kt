package dig.race.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.ashley.utils.ImmutableArray
import dig.race.comp.CPos
import dig.race.comp.CVelocity

class SMvt : IntervalSystem(0.016f) {

    private val cPos: ComponentMapper<CPos> = ComponentMapper.getFor(CPos::class.java)
    private val cVel: ComponentMapper<CVelocity> = ComponentMapper.getFor(CVelocity::class.java)
    private lateinit var entities: ImmutableArray<Entity>

    override fun addedToEngine(engine: Engine) {
        entities = engine.getEntitiesFor(Family.all(CPos::class.java, CVelocity::class.java).get())
        super.addedToEngine(engine)
    }

    override fun updateInterval() {
        entities.forEach {
            val pos = cPos[it].pos
            val vel = cVel[it].vel
            pos.update(vel.x * interval, vel.y * interval, vel.z * interval)
        }
    }
}
