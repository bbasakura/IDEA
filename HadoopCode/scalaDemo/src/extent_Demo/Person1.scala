package extent_Demo

class Person1 {
  private val name = "leo"
  val age=50
  def getName = this.name
}
class Student1 extends Person1 {

  //子类自己的 属性
  val fc:String= "aaa"
  //覆盖父类的属性
  override val age: Int = 30

  //子类自己的方法
  def getSome = this.fc

  //覆盖父类的非抽象方法时，必须用overwriter，并且在调用的时候，还必须用super

  override def getName: String = "you name is a :"+super.getName





}
