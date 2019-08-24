package construct_Demo

class Person7(val name:String,val age:Int){
  var score :Double=0.0
  var address:String="beijing"

  //第一个辅助constr=======第一行必须
  def this(name:String,score:Double)={
    //每个辅助constructor的第一行都必须调用其他辅助constructor或者主constructor代码
    //主constructor代码
    this(name,30)
    this.score=score
  }
  //其他辅助constructor
  def this(name:String,address:String)={
  this(name,100.0)
    this.address=address
  }


}
