package Trait_Demo


trait ValidTrait {
  //抽象方法
  def getName: String
  //具体方法，具体方法的返回值依赖于抽象方法

  def valid: Boolean = {"Tom".equals(this.getName)
  }
}
class PersonForValid(val name: String) extends ValidTrait {
  def getName: String = this.name
}

object PersonForValid{
  def main(args: Array[String]): Unit = {
    val person = new PersonForValid("Rose")
    println(person.valid)
  }
}

