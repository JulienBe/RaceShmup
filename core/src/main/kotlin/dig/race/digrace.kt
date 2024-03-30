package dig.race

import com.badlogic.ashley.core.PooledEngine
import dig.race.systems.SCam
import dig.race.systems.SControl
import dig.race.systems.SDraw2D
import dig.race.systems.SMvt
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen

class digrace : KtxGame<KtxScreen>() {
    override fun create() {
        addScreen(MainScreenTurnedOn())
        setScreen<MainScreenTurnedOn>()
    }
}

class MainScreenTurnedOn : KtxScreen {
    private val engine = PooledEngine()

    init {
        engine.addSystem(SControl())
        engine.addSystem(SMvt())
        engine.addSystem(SDraw2D())
        engine.addSystem(SCam())
        CarBuilder.addCar(engine)
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.0f, green = 0.0f, blue = 0.0f)
        engine.update(delta)
    }

    override fun dispose() {
        engine.removeAllEntities()
        engine.clearPools()
    }
}
