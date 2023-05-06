

object Bundles {

    val gui = Seq(
        Dependencies.scalaFx
    ) ++ Dependencies.fx

    val api = Seq(
        Dependencies.actors,
        Dependencies.http,
        Dependencies.stream
    )
}