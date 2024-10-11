import korlibs.korge.box2d.*
import korlibs.korge.render.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import org.jbox2d.common.*
import org.jbox2d.dynamics.*
import org.jbox2d.dynamics.joints.*
class Wheel(
    cont: Container,
    x: Number,
    y: Number,
    body: Body.Companion
):Container(){
    var wheelBody = Body(BodyDef(BodyType.DYNAMIC), nearestBox2dWorld)
    var wheel:Image? = null
    init {
        wheel = image(
            dodgeWheel!!


        )

        wheel!!.position(x,y)

        wheel!!.addTo(cont)
        wheel!!.registerBodyWithFixture(type = BodyType.DYNAMIC, density = 1, friction = 0.01, restitution = 0.4)
        wheelBody.view = wheel

    }

    override fun renderInternal(ctx: RenderContext) {
    }
}



enum class CarType {
    Dodge
}

class Car(
    ctype: CarType,
    cont: SceneContainer,
    x: Number,
    y: Number
): Container() {
    var carBody = Body(BodyDef(BodyType.DYNAMIC), nearestBox2dWorld)
    public var car: Image = Image(
        when (ctype){
            CarType.Dodge -> dodgeBit!!
            else -> throw Error("Invalid Piece !?")
        }
    )

    init {
        car.position(x,y)
        car.scale = 1.0
        car.addTo(cont)
        car.registerBodyWithFixture(type = BodyType.DYNAMIC, density = 2, friction = 0.01)
        carBody.view = car

        //var wh = Wheel(cont, 700, 50)

        //this.addTo(cont)
    }

    override fun renderInternal(ctx: RenderContext) {
    }

}
fun attachWheelJoint(world: World, carBody: View, wheel: View) {
    val carBodyFixture = carBody.registerBodyWithFixture(type = BodyType.DYNAMIC)
    val wheelFixture = wheel.registerBodyWithFixture(type = BodyType.DYNAMIC)

    // Create a WheelJoint definition
    val wheelJointDef = WheelJointDef()
    wheelJointDef.initialize(carBodyFixture!!.body!!, wheelFixture!!.body!!, wheelFixture.body!!.worldCenter, Vec2(0f, 1f))

    wheelJointDef.frequencyHz = 4.0f // Suspension frequency
    wheelJointDef.dampingRatio = 0.7f // Damping ratio
    wheelJointDef.enableMotor = false // Disable motor initially (set to true if you want motorized wheels)

    // Create the WheelJoint in the Box2D world
    world.createJoint(wheelJointDef)
}


