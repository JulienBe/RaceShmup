package dig.race.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dig.race.comp.CPos
import dig.race.digrace.Companion.HEIGHT
import dig.race.digrace.Companion.WIDTH
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import java.util.Comparator
import kotlin.math.sign

class SDraw2D : SortedIteratingSystem(
    Family.all(CPos::class.java).get(),
    ZComparator()
) {

    private val batch = SpriteBatch()
    private val cam: Camera = OrthographicCamera(WIDTH, HEIGHT)
    private val cPos: ComponentMapper<CPos> = ComponentMapper.getFor(CPos::class.java)
    private val image = Texture("square.png".toInternalFile(), true).apply { setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest) }

    init {
        cam.translate(WIDTH / 2f, HEIGHT / 2f, 0f)
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val pos = cPos[entity].pos
        batch.draw(image, pos.x, pos.y, 1f, 1f)
    }

    override fun update(deltaTime: Float) {
        cam.update()
        batch.begin()
        batch.projectionMatrix = cam.combined
        super.update(deltaTime)
        batch.end()
    }

    override fun removedFromEngine(engine: Engine?) {
        batch.disposeSafely()
        image.disposeSafely()
        super.removedFromEngine(engine)
    }
}

class ZComparator : Comparator<Entity> {
    private val mPos = ComponentMapper.getFor(CPos::class.java)
    override fun compare(o1: Entity?, o2: Entity?): Int {
        return sign(mPos[o1].pos.z - mPos[o2].pos.z).toInt()
    }
}
