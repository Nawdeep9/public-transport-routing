case class Station(name: String, destinationWithTime: Option[Map[String, (Station, Int)]] = None)

case class Input(start:String, end:String, time: Int)