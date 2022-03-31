package com.sample

import com.sample.adapters.Presentation

import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color._

object Main extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Consumption Calculator"
      width = 300
      height = 300
      scene = Presentation.Calculator()
    }
  }
}
