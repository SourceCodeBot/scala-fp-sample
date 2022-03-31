package com.sample.adapters

import cats.Monad
import cats.effect.IO
import com.sample.adapters.ports.Language.Calculators
import com.sample.adapters.ports.Program
import com.sample.adapters.ports.core.Domain.LitersTotal
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.paint.Color.{LightGreen, Red, color}
import scalafx.scene.layout.{BorderPane, GridPane}

import scala.collection.mutable

object Presentation:

  def safeDouble(value: String): Option[Double] = (
      if value.contains(',') then value.replace(',', '.') else value
    ).toDoubleOption

  import Interpretation.given
  import cats.effect.unsafe.implicits.global

  object UiElements:
    val error = new Label("") {
      textFill = Red
    }
    val liters = new TextField() {
      onKeyPressed = _ => clearError()
    }
    val tour = new TextField() {
      onKeyPressed = _ => clearError()
    }
    val litersPerKm = new TextField() {
      disable = true
    }
    val litersForTour = new TextField() {
      disable = true
    }
  object UiActions:
    val reset = new Button("Reset") {
      onAction = _ =>
        clearUiElementValues()
        clearError()
    }
    val submit = new Button("Calc"){
      onAction = _ => calculation(UiElements.tour.text.value, UiElements.liters.text.value)
    }

  def clearUiElementValues(): Unit =
    UiElements.liters.clear()
    UiElements.tour.clear()
    UiElements.litersPerKm.clear()
    UiElements.litersForTour.clear()

  def clearError(): Unit =
    UiElements.error.text = ""

  def calculation(tourText: String, litersText: String): Unit =
    clearError()
    val tourValueOpt = safeDouble(tourText)
    val litersValueOpt = safeDouble(litersText)
    for
      tourValue <- tourValueOpt
      litersValue <- litersValueOpt
    do
       val litersPerKm = Program.litersPerKm(litersValue, tourValue).unsafeRunSync()
       UiElements.litersPerKm.text = formatValue(litersPerKm)
       UiElements.litersForTour.text = formatValue(Program.litersForDistance(tourValue, litersPerKm).unsafeRunSync())

    evaluateError(tourValueOpt, litersValueOpt)

  def evaluateError(tourOpt: Option[Double], litersOpt: Option[Double]): Unit =
    val newErrorMsg = new mutable.StringBuilder()
    var newLine = ""
    if tourOpt.isEmpty then
      newErrorMsg.addAll("km for tour is a invalid number!")
      newLine = "\n"
    if litersOpt.isEmpty then newErrorMsg.addAll(s"${newLine}liters per 100km is a invalid number!")
    UiElements.error.text = newErrorMsg.toString()
  
  def formatValue(value: Double): String = f"$value%2.3f"

  def Calculator(): Scene = new Scene {
    val borderPane = new BorderPane
    borderPane.center = calculationFrame
    content = borderPane
  }

  private def calculationFrame = new GridPane() {
    hgap = 10
    vgap = 10
    padding = Insets(20, 50, 10, 10)

    add(new Label("Liters per 100 km"), 0, 0)
    add(UiElements.liters, 1, 0)

    add(new Label("km for tour"), 0, 1)
    add(UiElements.tour, 1, 1)

    add(new Label("Liters per km"), 0, 2)
    add(UiElements.litersPerKm, 1, 2)

    add(new Label("Liters per tour"), 0, 3)
    add(UiElements.litersForTour, 1, 3)

    add(UiActions.reset, 0, 4)
    add(UiActions.submit, 1, 4)

    add(UiElements.error, 0, 5, 2, 1)
  }