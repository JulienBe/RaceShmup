package dig.race.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.math.Vector3
import dig.race.CurrentCamFocus
import dig.race.GResolution.area
import dig.race.comp.CCamFocus
import dig.race.comp.CPos
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import java.util.Comparator
import kotlin.math.roundToInt
import kotlin.math.sign
import kotlin.random.Random

class SDraw2D : SortedIteratingSystem(
    Family.all(CPos::class.java).get(),
    ZComparator()
) {

    private val batch = SpriteBatch()
    private val cam: OrthographicCamera = OrthographicCamera(area.w, area.h)
    private val cPos: ComponentMapper<CPos> = ComponentMapper.getFor(CPos::class.java)
    private val image = Texture("square.png".toInternalFile(), true).apply { setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest) }
    private val buffer = frameBuffer()

    init {
        cam.translate(area.hw, area.hh, 0f)
        cam.setToOrtho(false, area.w, area.h)
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val pos = cPos[entity].pos
        batch.setColor(Color.WHITE)
        batch.draw(image, pos.prevX.round(), pos.prevY.round(), 1f, 1f)
        batch.setColor(Color.CORAL)
        batch.draw(image, pos.x.round(), pos.y.round(), 1f, 1f)
    }

    override fun update(deltaTime: Float) {
        cam.position.set(CurrentCamFocus.x.round(), CurrentCamFocus.y.round(), cam.position.z)
        cam.update()
        batch.projectionMatrix = cam.combined
        buffer.begin()
        batch.begin()
        Gdx.graphics.gL20.glClearColor(0f, 0f, 0f, 1f)
        Gdx.graphics.gL20.glClear(GL20.GL_COLOR_BUFFER_BIT)

        super.update(deltaTime)

        batch.end()
        buffer.end()
        val texture = buffer.colorBufferTexture
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        cam.position.set(area.hw, area.hh, 0f)
        cam.update()
        batch.projectionMatrix = cam.combined
        batch.begin()
        batch.draw(texture, 0f, area.h, area.w, -area.h)
        batch.end()
    }

    override fun removedFromEngine(engine: Engine?) {
        batch.disposeSafely()
        image.disposeSafely()
        super.removedFromEngine(engine)
    }

    companion object {
        fun frameBuffer(): FrameBuffer {
            return FrameBuffer(Pixmap.Format.RGBA8888, area.wI, area.hI, false)
        }
    }
}

private fun Float.round(): Float {
    return this.roundToInt().toFloat()
}

class ZComparator : Comparator<Entity> {
    private val mPos = ComponentMapper.getFor(CPos::class.java)
    override fun compare(o1: Entity?, o2: Entity?): Int {
        return sign(mPos[o1].pos.z - mPos[o2].pos.z).toInt()
    }
}
