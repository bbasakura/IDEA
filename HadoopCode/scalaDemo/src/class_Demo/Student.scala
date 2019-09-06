package class_Demo

class Student(var name: String, var age: Int) {
  /**
    * 每个类都有主构造器，主构造器的参数直接放置类名后面，与类交织在一起
    */

  println("这是主构造器")

  private var gender = "male"

  //辅助构造器
  def this(name:String,age:Int,gender:String){
    //每个辅助构造器执行必须以主构造器或者其他辅助构造器的调用开始
    this(name,age)
    println("执行辅助构造器")
    this.gender=gender
  }
}
object Student{

  def main(args: Array[String]): Unit = {

    val student1 = new Student("张三",20)

    val student2 = new Student("李四",15,"female")

    println(student1.name)

    println(student2.name)
  }
}
