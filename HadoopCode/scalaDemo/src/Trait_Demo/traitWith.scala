package Trait_Demo

trait LoggedTrait {
  // 该方法为实现的具体方法
  def log(msg: String) = {}
}
trait MyLogger extends LoggedTrait{
  // 覆盖 log() 方法
  override def log(msg: String) = println("log: " + msg)
}

class PersonForMixTraitMethod(val name: String) extends LoggedTrait {
  def sayHello = {
    println("Hi, I'm " + this.name)
    log("sayHello method is invoked!")
  }
}
object PersonForMixTraitMethod{
  def main(args: Array[String]) {
    val tom= new PersonForMixTraitMethod("Tom").sayHello //结果为：Hi, I'm Tom
    // 使用 with 关键字，指定混入MyLogger trait
    val rose = new PersonForMixTraitMethod("Rose") with MyLogger
    rose.sayHello
    // 结果为：     Hi, I'm Rose
    // 结果为：     log: sayHello method is invoked!
  }
}

