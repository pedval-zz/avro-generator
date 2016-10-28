package com.pedval.avrogenerator.avro

import com.pedval.avrogenerator.generatedclasses.Player

/**
  * Created by PJimen01 on 27/10/2016.
  */
object AvroGenerator {

  def createPlayerAvro(line : (String,String)) : Player = {
    val fields = line._2.split(",")
    val player : Player=  Player(fields(0),
      fields(1).toInt,
      fields(2).toInt,
      fields(3).toInt,
      fields(4),
      fields(5),
      fields(6),
      fields(7).toInt,
      fields(8).toInt,
      fields(9).toInt,
      fields(10),
      fields(11),
      fields(12),
      fields(13),
      fields(14),
      fields(15),
      fields(16).toInt,
      fields(17).toInt,
      fields(18),
      fields(19),
      fields(20),
      fields(21),
      fields(22),
      fields(23))

    player
  }

}
