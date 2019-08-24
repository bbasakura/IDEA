package class_Demo

//伴生类
class Dog {
  val id = 1
  private var name = "itcast"
  def printName(): Unit ={
    //在Dog类中可以访问伴生对象Dog的私有属性
    println(Dog.CONSTANT + name )
  }
}
//伴生对象
object Dog {
  //伴生对象中的私有属性
  private val CONSTANT = "汪汪汪 : "
  def main(args: Array[String]) {
    val p = new Dog
    //访问私有的字段name
    p.name = "123"
    p.printName()
  }
}
//执行结果 汪汪汪 : 123
