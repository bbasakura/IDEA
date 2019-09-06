package IsinstanceOf_Demo

class Person3 {
}

class student3 extends Person3 {
}

object student3 {

  def main(args: Array[String]): Unit = {

    val p: student3 = new student3

    val s: student3 = null


    //isInstanceOf[T]======中括号[]
    println(s.isInstanceOf[student3])
    if (p.isInstanceOf[student3]){

      val s = p.asInstanceOf[student3]

      println(s.isInstanceOf[student3])

    }

  }


}