package com.pedval.avrogenerator.sink

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

/**
  * Created by PJimen01 on 27/10/2016.
  */
class KafkaSink[V](createProducer: () => KafkaProducer[String, V]) extends Serializable {

  lazy val producer = createProducer()

  def send(topic: String, value: V): Unit = producer.send(new ProducerRecord(topic, value))



}

object KafkaSink {
  def apply[V](config: Properties): KafkaSink[V] = {
    val f = () => {
      new KafkaProducer[String, V](config)
    }
    new KafkaSink(f)
  }



}


