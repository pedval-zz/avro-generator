# avro-generator
Generates AVRO records given a single line CSV line from Kafka

###Generating classes from AVRO schema
```
sbt avro:generate-specific
```
Note: for more details and uses of sbt-avrohugger, please visit: https://github.com/julianpeeters/sbt-avrohugger

###Generating the jar (with all dependencies included)
```
sbt clean assembly
```
Note: for more details and uses of the assembly plugin, please visit: https://github.com/sbt/sbt-assembly

###Running the app
```
$SPARK_HOME/bin/spark-submit --class com.pedval.avrogenerator.main.Application \
    ./target/scala-2.11/avro-generator-1.0.jar \
    <app_name> <seconds_to process_batch> <input_topics> <output_topic>
```

Example
```
spark-submit --class com.pedval.avrogenerator.main.Application target/scala-2.11/avro-generator-1.0.jar AvroGenerator 5 "input1, input2" outputAvroTopic
``` 