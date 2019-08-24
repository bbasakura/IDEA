package apply_Demo

class Money(val value: Double, val conutry: String) {}

object Money {

  def apply(value: Double,conutry: String): Money = {
    new Money( value,  conutry)
  }

  def unapply(money: Money): Option[(Double, String)] = {
    if(money==null){
      //牛逼了
        None
    }else{

      Some(money.value,money.conutry)
    }

  }
}

//测试方法
object test01{

  def main(args: Array[String]): Unit = {
    val money = Money(1.02,"da")
    money match {
      case Money(num,"RMB") =>println("Rmb"+num)
      case _ =>println("Not Rmb")
  }
  }






}