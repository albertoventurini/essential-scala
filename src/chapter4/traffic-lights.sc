object TrafficLightPolymorphism {

  sealed trait TrafficLight {
    def next: TrafficLight
  }

  final case object Red extends TrafficLight {
    def next: TrafficLight = Green
  }

  final case object Green extends TrafficLight {
    def next: TrafficLight = Yellow
  }

  final case object Yellow extends TrafficLight {
    def next: TrafficLight = Red
  }

}

object TrafficLightPatternMatching {

  sealed trait TrafficLight
  final case object Red extends TrafficLight
  final case object Green extends TrafficLight
  final case object Yellow extends TrafficLight

  def next(trafficLight: TrafficLight) = trafficLight match {
    case Green => Yellow
    case Yellow => Red
    case Red => Green
  }

}