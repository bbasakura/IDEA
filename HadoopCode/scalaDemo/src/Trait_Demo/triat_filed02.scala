package Trait_Demo

trait SayHelloTrait {

  //不赋值就是抽象的filed
  val msg:String
  def sayHello(name: String) = println(msg + ", " + name)
}

class PersonForAbstractField(val name: String) extends SayHelloTrait {
  //必须覆盖抽象 field=====必须赋值，不然还是抽象的

  val msg = "Hello"
  def makeFriends(other: PersonForAbstractField) = {
    this.sayHello(other.name)
    println("I'm " + this.name + ", I want to make friends with you!!")
  }
}
object PersonForAbstractField{
  def main(args: Array[String]) {
    val p1=new PersonForAbstractField("Tom")
    val p2=new PersonForAbstractField("Rose")
    p1.makeFriends(p2)
  }
}

