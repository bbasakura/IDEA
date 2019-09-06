package abstract_Demo

abstract class Person9(val name:String) {
  //必须指出返回类型，不然默认返回为Unit
  def sayHello:String
  def sayBye:String
}
class Student9(name:String) extends Person9(name){

  override def sayHello: String ="hahah"+name

  //可以不加overwriter
   def sayBye: String = "xixixi"+ name

}
object Student9 {
  def main(args: Array[String]) {
    val s = new Student9("tom")
    println(s.sayHello)
    println(s.sayBye)
  }
}