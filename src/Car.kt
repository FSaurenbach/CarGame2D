import korlibs.image.color.*
import korlibs.korge.box2d.*
import korlibs.korge.render.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.math.geom.*
import org.jbox2d.dynamics.*

class Wheel(
    cont: Container,
    x:Number,
    y:Number
):Container(){
    init {
        var wheel = image(
                dodgeWheel!!


        )

        wheel.position(x,y)

        wheel.addTo(cont)
        wheel.registerBodyWithFixture(type = BodyType.DYNAMIC, density = 1, friction = 0.01, restitution = 0.4)


    }

    override fun renderInternal(ctx: RenderContext) {
    }
}



enum class CarType {
    Dodge
}

class Car (
    var ctype: CarType,
    cont: SceneContainer,
    x:Number,
    y:Number
): Container() {
    private var car: Image = Image(
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

        var wh = Wheel(cont, 700, 50)

        //this.addTo(cont)
    }

    override fun renderInternal(ctx: RenderContext) {
    }

}
