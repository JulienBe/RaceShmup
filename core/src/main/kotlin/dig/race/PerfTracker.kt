package dig.race

import com.badlogic.gdx.Gdx
import java.util.PriorityQueue

class PerfTracker {
    private var totalTime = 0f
    private var currentSecond = 0
    private val histogram = MutableList(25) { 0 } // Assuming fps will not exceed 120

    private val performanceQueue: PriorityQueue<MethodPerformance> = PriorityQueue(Comparator.comparingDouble { it.time })


    fun update(delta: Float) {
        totalTime += delta
        if (totalTime > currentSecond + 1) {
            val fps = Gdx.graphics.framesPerSecond
            if (fps in 0..120) {
                histogram[fps / 5]++
            }
            currentSecond++
            println("\nFPS: $fps")
            printHistogram()
            printSlowestMethods()
        }
        performanceQueue.clear()
    }

    fun <T> measure(methodName: String, block: () -> T): T {
        val startTime = System.nanoTime()
        val result = block()

        trackMethodPerformance(methodName, startTime, System.nanoTime())

        return result
    }

    fun trackMethodPerformance(methodName: String, startTime: Long, endTime: Long) {
        val elapsedTime = (endTime - startTime) / 1_000_000.0 // convert to milliseconds
        performanceQueue.add(MethodPerformance(methodName, elapsedTime))

        // Keep only the two slowest methods in the queue
        while (performanceQueue.size > 2) {
            performanceQueue.poll()
        }
    }

    fun printHistogram() {
        histogram.forEachIndexed { index, count ->
            val bar = "*".repeat(count)
            println("FPS ${index * 5} to ${index * 5 + 4}: $bar")
        }
    }

    fun printSlowestMethods() {
        println("Two slowest methods:")
        for (methodPerformance in performanceQueue) {
            println("Method: ${methodPerformance.name}, Time: ${methodPerformance.time} ms")
        }
    }
}

private data class MethodPerformance(val name: String, val time: Double)
