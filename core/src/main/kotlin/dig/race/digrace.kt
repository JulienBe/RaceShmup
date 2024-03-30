package dig.race

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dig.race.systems.SDraw2D
import dig.race.systems.SMvt
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely

class digrace : KtxGame<KtxScreen>() {
    override fun create() {
        addScreen(MainScreenTurnedOn())
        setScreen<MainScreenTurnedOn>()
    }
}

class MainScreenTurnedOn : KtxScreen {
    private val batch = SpriteBatch()
    private val engine = PooledEngine()

    init {
        engine.addSystem(SMvt())
        engine.addSystem(SDraw2D())
        FamilyBuilder.addCar(engine)
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        engine.update(delta)
    }

    override fun dispose() {
        batch.disposeSafely()
    }
}
