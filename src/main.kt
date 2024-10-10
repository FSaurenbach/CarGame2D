import korlibs.image.bitmap.*
import korlibs.time.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.tween.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.korge.box2d.*
import korlibs.korge.view.align.*
import korlibs.math.geom.*
import korlibs.math.geom.Circle
import korlibs.math.interpolation.*
import org.jbox2d.collision.shapes.*
import org.jbox2d.common.*
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
        var ca = Car(CarType.Dodge, sceneContainer, 500, 50)
        //var w = Wheel(sceneContainer, 600, 50)

        val ground = solidRect(800, 50, Colors.BLUE)
        ground.y = sceneHeight.toDouble() - 60
        ground.centerXOnStage()
        ground.registerBodyWithFixture(type = BodyType.STATIC, friction = 0.2, restitution = 0.2)
        //fastEllipse(Size(100, 100))



        /*val image = Car(CarType.Dodge, sceneContainer)
            .registerBodyWithFixture(
                type = BodyType.DYNAMIC,
                linearVelocityY = 6.0,
                friction = 0.2,
                restitution =6
            )*/


        //image.scale = 0.1


    }
}


