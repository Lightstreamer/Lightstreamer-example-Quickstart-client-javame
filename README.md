# Lightstreamer - Quickstart Example - Java ME Client #
<!-- START DESCRIPTION lightstreamer-example-quickstart-client-javame -->

The *Quickstart Example* provides the source code to build very simple and basic client applications, used to test the capability of the Client APIs to connect and receive data from Lightstreamer Server. The examples can be used to familiarize with the Client APIs and as a reference on how to use them, and can be used as a starting point for client application implementations.

This project contains the source files of a sample application that shows how the [Lightstreamer Java ME Client API](https://lightstreamer.com/api/ls-javame-client/latest/index.html) can be used to connect to Lightstreamer Server.

<!-- END DESCRIPTION lightstreamer-example-quickstart-client-javame -->

## Details

These examples, called "Tester" and "DistinctTester", are not made for running on real devices, because the output is written on the console. Moreover, nothing is displayed on the display.

By the current configuration, specified in `/src/javametest/Tester.java` and in `/src/javametest/DistinctTester.java`, the demos try to connect to a local installation of Lightstreamer Server. If needed, the host name, the port number, the Adapter Set name, and the Data Adapter name could be changed in the source code.

The demos are suitable for running with the [QUOTE_ADAPTER](https://github.com/Lightstreamer/Lightstreamer-example-Stocklist-adapter-java), [PORTFOLIO_ADAPTER](https://github.com/Lightstreamer/Lightstreamer-example-Portfolio-adapter-java), and [CHAT_ROOM](https://github.com/Lightstreamer/Lightstreamer-example-Chat-adapter-java) Data Adapters.

Check out the sources code for further explanations. 

## Build

To build and install a version of this demo, pointing to your local Lightstreamer Server instance, follow the steps below.

* The `Tester` class, needs both the *PORTFOLIO_ADAPTER* (see [Lightstreamer - Portfolio Demo - Java Adapter](https://github.com/Lightstreamer/Lightstreamer-example-Portfolio-adapter-java)), and the *QUOTE_ADAPTER* (see [Lightstreamer - Stock-List Demo - Java Adapter](https://github.com/Lightstreamer/Lightstreamer-example-StockList-adapter-java)). Therefore, as a prerequisite, the full version of the [Lightstreamer - Portfolio Demo - Java Adapter](https://github.com/Lightstreamer/Lightstreamer-example-Portfolio-adapter-java) has to be deployed on your local Lightstreamer Server instance. Please follow the instructions in [Install the Portfolio Demo](https://github.com/Lightstreamer/Lightstreamer-example-Portfolio-adapter-java#install-the-portfolio-demo) to install it.
* The `DistinctTester` class needs the *CHAT_ROOM* Adapter, therefore, as a prerequisite, the [Lightstreamer - Basic Chat Demo - Java Adapter](https://github.com/Lightstreamer/Lightstreamer-example-Chat-adapter-java) has to be deployed on your local Lightstreamer Server instance.
* Download the latest `ls-j2me-client.jar` file from the [Lightstreamer Java ME Client SDK resources](https://lightstreamer.com/res/ls-javame-client/latest/usage.html) into the `lib` directory of the project.
* Build 

## See Also

### Lightstreamer Adapters Needed by This Demo Client 
<!-- START RELATED_ENTRIES -->

* [Lightstreamer - Stock-List Demo - Java Adapter](https://github.com/Lightstreamer/Lightstreamer-example-Stocklist-adapter-java)
* [Lightstreamer - Portfolio Demo - Java Adapter](https://github.com/Lightstreamer/Lightstreamer-example-Portfolio-adapter-java)
* [Lightstreamer - Basic Chat Demo - Java Adapter](https://github.com/Lightstreamer/Lightstreamer-example-Chat-adapter-java)
* [Lightstreamer - Reusable Metadata Adapters - Java Adapter](https://github.com/Lightstreamer/Lightstreamer-example-ReusableMetadata-adapter-java)

<!-- END RELATED_ENTRIES -->

### Related Projects 

* [Lightstreamer - Basic Stock-List and Round-Trip Demo - Java ME Client](https://github.com/Lightstreamer/Lightstreamer-example-StockList-client-midlet)

## Lightstreamer Compatibility Notes

- Compatible with Lightstreamer Java ME Client API v. 3.2.1 or newer.
- Ensure that Java ME Client API is supported by Lightstreamer Server license configuration.
