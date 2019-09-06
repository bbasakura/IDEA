package Trait_Demo


trait PersonForField {
  val  age:Int=50
}

//继承 trait 获取的field直接被添加到子类中 ===直接拿过来用的
class StudentForField (val name: String) extends PersonForField {
  def sayHello = println("Hi, I'm " + this.name + ", my  age  is "+ age)
}

object StudentForField{
  def main(args: Array[String]) {
    val s=new StudentForField("tom")
    s.sayHello
  }
}

