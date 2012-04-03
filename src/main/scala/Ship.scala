trait ShipStatus
case object Alive extends ShipStatus
case object Dead extends ShipStatus

case class Ship (var shipNumber: Int, var status: ShipStatus = Dead)