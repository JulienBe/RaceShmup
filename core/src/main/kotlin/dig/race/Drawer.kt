package dig.race

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.utils.Array
import ktx.assets.toInternalFile

class Drawer {

    private val batch = SpriteBatch()
    private val cam: OrthographicCamera = OrthographicCamera(GResolution.area.w, GResolution.area.h)
    private val image = Texture("square.png".toInternalFile(), true).apply { setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest) }
    private val buffer = frameBuffer()

    fun draw(cars: Array<Car>) {
        batch.color = Color.CORAL
        cars.forEach {
            batch.draw(image, it.pos.x.round(), it.pos.y.round(), 1f, 1f)
        }
    }

    fun begin() {
        cam.position.set(CurrentCamFocus.x.round(), CurrentCamFocus.y.round(), cam.position.z)
        cam.update()
        batch.projectionMatrix = cam.combined
        buffer.begin()
        batch.begin()
        Gdx.graphics.gL20.glClearColor(0f, 0f, 0f, 1f)
        Gdx.graphics.gL20.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    fun end() {
        batch.end()
        buffer.end()
        val texture = buffer.colorBufferTexture
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        cam.position.set(GResolution.area.hw, GResolution.area.hh, 0f)
        cam.update()
        batch.projectionMatrix = cam.combined
        batch.setColor(Color.WHITE)
        batch.begin()
        batch.draw(texture, 0f, GResolution.area.h, GResolution.area.w, -GResolution.area.h)
        batch.end()
    }

    fun draw(track: Track) {
        batch.color = Color.GRAY
        track.pieces.forEach {
            it.forEach { it.draw(batch, image) }
        }
    }

    companion object {
        fun frameBuffer(): FrameBuffer {
            return FrameBuffer(Pixmap.Format.RGBA8888, GResolution.area.wI, GResolution.area.hI, false)
        }
    }
}

interface Drawable {
    fun draw(batch: SpriteBatch, texture: Texture)
}
