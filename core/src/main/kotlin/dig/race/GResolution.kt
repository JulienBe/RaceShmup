package dig.race

import com.badlogic.gdx.Gdx

object GResolution {
    val area = GDim(320f * 0.5f, 200f * 0.5f)
    val screen = GDim(0f, 0f)

    init {
        screen.update(Gdx.graphics.width, Gdx.graphics.height)
    }
}
