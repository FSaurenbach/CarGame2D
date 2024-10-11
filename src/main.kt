import korlibs.image.bitmap.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.korge.box2d.*
import korlibs.korge.view.align.*
import korlibs.math.geom.*
import org.jbox2d.dynamics.*

var dodgeBit: Bitmap? = null
var dodgeWheel: Bitmap? = null
suspend fun main() = Korge(windowSize = Size(960, 540), backgroundColor = Colors["#2b2b2b"]) {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo { GameScene() }
}

class GameScene : Scene() {
    override suspend fun SContainer.sceneMain() {
        val minDegrees = (-16).degrees
        val maxDegrees = (+16).degrees
        dodgeBit = resourcesVfs["off road racing/car-body.png"].readBitmap()
        dodgeWheel = resourcesVfs["off road racing/car-wheel.png"].readBitmap()

        /*val world = worldView(10.0, 20.0)
        world.createBody {
            circle(50f)
                //ellipse(Size(50, 50))
                .registerBodyWithFixture(
                    type = BodyType.DYNAMIC,
                    linearVelocityY = 6.0,
                    friction = 0.2,
                    restitution = 1
                )
                .xy(120 + 140, 246)
                .anchor(Anchor.CENTER)

        }*/

        val car = Car(CarType.Dodge, sceneContainer, 500, 50)

        // Create wheels
        val frontWheel = Wheel(sceneContainer, 500, 100, Body)
        val rearWheel = Wheel(sceneContainer, 600, 100, Body)
        attachWheelJoint(nearestBox2dWorld, car.car, frontWheel.wheel!!)
        attachWheelJoint(nearestBox2dWorld, car.car!!, rearWheel.wheel!!)
        val ground = solidRect(800, 50, Colors.BLUE)
        ground.y = sceneHeight.toDouble() - 60
        ground.centerXOnStage()
        ground.registerBodyWithFixture(type = BodyType.STATIC, friction = 0.2, restitution = 0.2)


    }
}
