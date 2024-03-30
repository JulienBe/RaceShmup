package dig.race

object Builder {
    const val maxAcceleration = 3f
    const val acceleration = 1.5f
    const val turnSpeed = 4.5f
    const val maxTurnSpeed = 9f
    fun createCar(): Car {
        return Car()
    }

}
