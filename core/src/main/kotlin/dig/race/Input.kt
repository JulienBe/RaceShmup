package dig.race

import com.badlogic.gdx.Input
import com.badlogic.gdx.utils.Array
import ktx.app.KtxInputAdapter

class Input : KtxInputAdapter {

    private val keysToActions = mapOf(
        listOf(Input.Keys.UP, Input.Keys.W, Input.Keys.Z) to { Action.UP },
        listOf(Input.Keys.DOWN, Input.Keys.S) to { Action.DOWN },
        listOf(Input.Keys.LEFT, Input.Keys.A, Input.Keys.Q) to { Action.LEFT },
        listOf(Input.Keys.RIGHT, Input.Keys.D) to { Action.RIGHT }
    )
    private val keyPressed = Array<Int>()

    override fun keyDown(keycode: Int): Boolean {
        keyPressed.add(keycode)
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        keyPressed.removeValue(keycode, true)
        return true
    }

    fun act(): Set<Action> {
        val actions = keyPressed.mapNotNull { key ->
            keysToActions.entries.find { it.key.contains(key) }?.value?.invoke()
        }.toSet()
        return actions
    }
}

enum class Action {
    UP, DOWN, LEFT, RIGHT
}
