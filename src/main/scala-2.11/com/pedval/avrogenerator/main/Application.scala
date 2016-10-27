package com.pedval.avrogenerator.main

import java.util.Properties

import com.pedval.avrogenerator.sink.KafkaSink
import org.apache.commons.codec.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

import com.pedval.avrogenerator.avro.AvroGenerator

/**
  * Created by PJimen01 on 27/10/2016.
  */
object Application {

  def main(args: Array[String]) {

    if(args.length != 3) {
      println("----------------------")
      println("- ERROR              -")
      println("- Usage:             -")
      println("----------------------")

      System.exit(-1)
    }

    val conf = new SparkConf().setMaster("local[2]").setAppName(args(0))

    val ssc = new StreamingContext(conf, Seconds(args(1).toInt))

    val inputTopics = args(3).split(",").toSet

    val kafkaParams = Map[String, String]("metadata.broker.list"   -> "localhost:9092")

    val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, inputTopics)

    val kafkaSink = ssc.sparkContext.broadcast(KafkaSink(createProperties()))

    messages.foreachRDD(rdd => {
      rdd.map(AvroGenerator.createAvro).foreach(avro => kafkaSink.value.send("test", avro))
    })

    ssc.start()
    ssc.awaitTermination()

  }


  def createProperties():  Properties = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("serializer.class", "kafka.serializer.StringEncoder")
    props.put("producer.type", "async")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    props
  }

}
