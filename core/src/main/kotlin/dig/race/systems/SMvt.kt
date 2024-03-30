package dig.race.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.math.Vector3
import dig.race.comp.CPos
import dig.race.comp.CDirection

class SMvt : IntervalSystem(0.016f) {

    private val cPos: ComponentMapper<CPos> = ComponentMapper.getFor(CPos::class.java)
    private val cDir: ComponentMapper<CDirection> = ComponentMapper.getFor(CDirection::class.java)
    private lateinit var entities: ImmutableArray<Entity>

    override fun addedToEngine(engine: Engine) {
        entities = engine.getEntitiesFor(Family.all(CPos::class.java, CDirection::class.java).get())
        super.addedToEngine(engine)
    }

    override fun updateInterval() {
        entities.forEach {
            val pos = cPos[it].pos
            val dir = cDir[it]

            dir.speed += dir.acceleration
            dir.angle += dir.turningAngle

            dir.vec.set(Vector3.X)
            dir.vec.rotate(Vector3.Z, dir.angle)
            dir.vec.setLength(dir.speed)

            dir.speed *= 0.99f
            dir.acceleration *= 0.99f
            dir.turningAngle *= 0.99f

            pos.update(dir.vec.x * interval, dir.vec.y * interval, dir.vec.z * interval)
        }
    }
}
