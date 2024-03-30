package dig.race

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Array
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
    private val cars = Array<Car>()
    private val drawer = Drawer()
    private val input = Input()
    private val physics = Physics()
    private var player = Builder.createCar()
    private val track = Track()

    init {
        cars.add(player)
        Gdx.input.inputProcessor = input
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.0f, green = 0.0f, blue = 0.0f)
        input.act()
        track.act()
        cars.forEach { car ->
            car.act(input.act(), delta)
        }
        physics.moveCars(delta, cars)
        with(player.pos) {
            CurrentCamFocus.x = x
            CurrentCamFocus.y = y
            CurrentCamFocus.z = z + 5f
        }
        drawer.begin()
        drawer.draw(track)
        drawer.draw(cars)
        drawer.end()
    }

    override fun dispose() {
    }
}
