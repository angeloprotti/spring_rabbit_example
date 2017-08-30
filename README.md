# RabbitMQ - Exchange of messages between two Spring Boot Applications

This project was been created for use to guide and study [AMQP](https://en.wikipedia.org/wiki/Advanced_Message_Queuing_Protocol), to be more specific [RabbitMQ](https://en.wikipedia.org/wiki/RabbitMQ).

Basically, the **rabbit-one** sends a simple message to the queue of **rabbit-two** through **GET - /hello-rabbit**. Automatically, the **rabbit-two** reads this message and write an output on your console, and vice-versa (the same occurs to the other way, with the same path).

**PS:** This project was been created with the intention of studying. If you know the other way to do this more easily, tell me on: **jose.slneto@gmail.com**

#### Necessary
* RabbitMQ installed (see RabbitMQOneConfig.class and RabbitMQTwoConfig.class to more details);
