package apply_Demo


//伴生现象中的apply方法，类似于构造方法
class ay(Foo:String) {}

object ay{

  def apply(Foo: String): ay = {new ay(Foo)}

}

object test{

  def main(args: Array[String]): Unit = {

    val apply = ay("hello")

    println(apply)


  }


}