package org.srcbot.samples.adapters

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.layout.GridPane
import scalafx.scene.text.Text
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.effect.DropShadow
import scalafx.geometry.Insets
import scalafx.scene.control.TextField
import org.srcbot.samples.adapters.ports.Program
import org.srcbot.samples.adapters.Interpretation.given

object Presentation extends JFXApp3:

    override def start(): Unit = 
      val litersField = new TextField()
      val distanceField = new TextField()

      val consumeText = new Text("You consume 0.0l per Km.")
      val trackText = new Text("Your Track took 0.0 liters.")
      litersField.onKeyReleased = _ => {
        litersField.text.value.toDoubleOption.foreach(Program.setLiters)
        consumeText.text = f"You consume ${Program.calculatePerKm}%2.3fl per Km."
      }
      distanceField.onKeyReleased = _ => {
        distanceField.text.value.toDoubleOption.foreach(Program.setDistance)
        trackText.text = f"Your Tack took ${Program.calculateLitersForDistance}%2.3f liters."
      }

      val grid = new GridPane {
          vgap = 10
          hgap = 10
          padding = Insets(10, 10, 10, 10) 
      }
      grid.add(new Text {
                  text = "Liters per 100Km"
      }, 0, 0)
      grid.add(litersField, 1, 0)
      
      grid.add(new Text {
                  text = "Distance (km)"
      }, 0, 1)
      grid.add(distanceField, 1, 1)
      
      grid.add(consumeText, 0, 2, 2, 1)
      grid.add(trackText, 0, 3, 2, 1)

      stage = new JFXApp3.PrimaryStage {
      title = "Calc FP"
      scene = new Scene {
        fill = Color.rgb(200, 200, 200)
        content = grid
      }
    }
end Presentation