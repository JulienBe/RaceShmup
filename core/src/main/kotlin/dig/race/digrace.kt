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
    private val perf = PerfTracker()

    init {
        cars.add(player)
        Gdx.input.inputProcessor = input
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.0f, green = 0.0f, blue = 0.0f)
        perf.measure("input.act") { input.act() }
        perf.measure("track.act") { track.act() }
        perf.measure("car.act") {
            cars.forEach { car ->
                car.act(input.act(), delta)
            }
        }
        perf.measure("physics.moveCars") { physics.moveCars(delta, cars) }
        perf.measure("Collider.checkCollisions") { Collider.checkCollisions(cars, track) }
        with(player.pos) {
            CurrentCamFocus.x = x
            CurrentCamFocus.y = y
            CurrentCamFocus.z = z + 5f
        }
        drawer.begin()
        perf.measure("drawer.drawTrack") { drawer.drawTrack(track) }
        perf.measure("drawer.drawCars") { drawer.drawCars(cars) }
        drawer.end()
        perf.update(delta)
    }

    override fun dispose() {
    }
}
