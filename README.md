# Lightstreamer - Quickstart Example - Java ME Client #
<!-- START DESCRIPTION lightstreamer-example-quickstart-client-javame -->

The *Quickstart Example* provides the source code to build very simple and basic client applications, used to test the capability of the Client APIs to connect and receive data from Lightstreamer Server. The examples can be used to familiarize with the Client APIs and as a reference on how to use them, and can be used as a starting point for client application implementations.

This project contains the source files of a sample application that shows how the [Lightstreamer Java ME Client API](http://www.lightstreamer.com/docs/client_javame_api/index.html) can be used to connect to Lightstreamer Server.

<!-- END DESCRIPTION lightstreamer-example-quickstart-client-javame -->

## Details

These examples, called "Tester" and "DistinctTester", are not made for running on real devices, because the output is written on the console. Moreover, nothing is displayed on the display.

By the current configuration, specified in `/src/javametest/Tester.java` and in `/src/javametest/DistinctTester.java`, the demos try to connect to a local installation of Lightstreamer Server. If needed, the host name, the port number, the Adapter Set name, and the Data Adapter name could be changed in the source code.

The demos are suitable for running with the [QUOTE_ADAPTER](https://github.com/Weswit/Lightstreamer-example-Stocklist-adapter-java), [PORTFOLIO_ADAPTER](https://github.com/Weswit/Lightstreamer-example-Portfolio-adapter-java), and [CHAT_ROOM](https://github.com/Weswit/Lightstreamer-example-Chat-adapter-java) Data Adapters.

Check out the sources code for further explanations. 

## Build

To build and install a version of this demo, pointing to your local Lightstreamer Server instance, follow the steps below.

* The `Tester` class, needs both the *PORTFOLIO_ADAPTER* (see [Lightstreamer - Portfolio Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-Portfolio-adapter-java)), and the *QUOTE_ADAPTER* (see [Lightstreamer - Stock-List Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-StockList-adapter-java)). Therefore, as a prerequisite, the full version of the [Lightstreamer - Portfolio Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-Portfolio-adapter-java) has to be deployed on your local Lightstreamer Server instance. Please follow the instructions in [Install the Portfolio Demo](https://github.com/Weswit/Lightstreamer-example-Portfolio-adapter-java#install-the-portfolio-demo) to install it.
* The `DistinctTester` class needs the *CHAT_ROOM* Adapter, therefore, as a prerequisite, the [Lightstreamer - Basic Chat Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-Chat-adapter-java) has to be deployed on your local Lightstreamer Server instance.
* Get the *Lighstreamer SDK for Java ME Clients*: copy the `ls-j2me-client` file from the `/DOCS-SDKs/sdk_client_java_me/lib` folder of the [latest Lightstreamer distribution](http://www.lightstreamer.com/download) into the `lib` directory of the project.
* Build 

## See Also

### Lightstreamer Adapters Needed by This Demo Client 
<!-- START RELATED_ENTRIES -->

* [Lightstreamer - Stock-List Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-Stocklist-adapter-java)
* [Lightstreamer - Portfolio Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-Portfolio-adapter-java)
* [Lightstreamer - Basic Chat Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-Chat-adapter-java)
* [Lightstreamer - Reusable Metadata Adapters - Java Adapter](https://github.com/Weswit/Lightstreamer-example-ReusableMetadata-adapter-java)

<!-- END RELATED_ENTRIES -->

### Related Projects 

* [Lightstreamer - Basic Stock-List and Round-Trip Demo - Java ME Client](https://github.com/Weswit/Lightstreamer-example-StockList-client-midlet)

## Lightstreamer Compatibility Notes

- Compatible with Lightstreamer Java ME Client API v. 3.2.1 or newer.
- For Lightstreamer Allegro (+ Java ME Client API), Presto, Vivace.
