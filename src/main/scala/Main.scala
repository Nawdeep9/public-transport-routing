object Main extends App {

  val input = 8
  val input1 = Input("a", "b", 1)
  val input2 = Input("a", "c", 2)
  val inputs = Seq(input1, input2)

  val railNetwork =
    inputs.foldLeft(Map.empty[String, Station])((res, i) => {
      val s1 = res.getOrElse(i.start, Station(i.start))
      val s2 = res.getOrElse(i.end, Station(i.end))
      val d = s1.destinationWithTime.getOrElse(Map(i.end -> (s2, i.time))) + (i.end -> (s2, i.time))
      res + (i.start -> Station(i.start,Some(d)))
    })

  def findRoute(start: String, end: String, path: String): String = {
    railNetwork.get(start) match {
      case Some(s) =>
        s.destinationWithTime match {
          case Some(destinations) =>
            if(destinations.contains(end)) s"$path -> $end"
            else destinations.map(d => findRoute(d._1, end, path))
          case None => "No Route found"
        }
      case None => "No route found"
    }
  }
}
