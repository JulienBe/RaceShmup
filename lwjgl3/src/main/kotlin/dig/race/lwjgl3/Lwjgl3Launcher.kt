@file:JvmName("Lwjgl3Launcher")

package dig.race.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import dig.race.digrace

fun main() {
    // This handles macOS support and helps on Windows.
    if (StartupHelper.startNewJvmIfRequired())
      return
    Lwjgl3Application(digrace(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("digrace")
        setWindowedMode(320 * 3, 200 * 3)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
