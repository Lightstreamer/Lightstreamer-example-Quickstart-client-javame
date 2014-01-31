# Lightstreamer - Quickstart Example - Java ME Client #
<!-- START DESCRIPTION lightstreamer-example-quickstart-client-javame -->

This example, called "Tester", is not made for running on real devices, since its output is written on the console. Moreover nothing is displayed on the display.
You can use the provided source code as a starting point to familiarize with the [Lightstreamer Java ME Client API](http://www.lightstreamer.com/docs/client_javame_api/index.html).

<!-- END DESCRIPTION lightstreamer-example-quickstart-client-javame -->

Check out the sources for further explanations. 

# Build #

This example is comprised of the following folders:
* /src<br>
  Contains the sources to build the java application.
  
* /lib<br>
  Drop here the ls-j2me-client.jar from the Lighstreamer SDK for Java ME Clients, to be used for the build process and execution.

# Deploy #
  
By the current configuration, specified in "/src/javametest/tester.java", the demo tries to connect to a local installation of Lightstreamer Server. If needed, the host name, the port number, the Adapter Set name and the Data Adapter
name could be changed in the source code.<br>
The demo is suitable for running with the [QUOTE_ADAPTER](https://github.com/Weswit/Lightstreamer-example-Stocklist-adapter-java), [PORTFOLIO_ADAPTER](https://github.com/Weswit/Lightstreamer-example-Portfolio-adapter-java) and [CHAT_ROOM](https://github.com/Weswit/Lightstreamer-example-Chat-adapter-java) Data Adapters.

# See Also #

## Lightstreamer Adapters Needed by This Demo Client ##
<!-- START RELATED_ENTRIES -->

* [Lightstreamer - Stock-List Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-Stocklist-adapter-java)
* [Lightstreamer - Portfolio Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-Portfolio-adapter-java)
* [Lightstreamer - Basic Chat Demo - Java Adapter](https://github.com/Weswit/Lightstreamer-example-Chat-adapter-java)
* [Lightstreamer - Reusable Metadata Adapters - Java Adapter](https://github.com/Weswit/Lightstreamer-example-ReusableMetadata-adapter-java)

<!-- END RELATED_ENTRIES -->

## Related Projects ##

* [Lightstreamer - Basic Stock-List and Round-Trip Demo - Java ME Client](https://github.com/Weswit/Lightstreamer-example-StockList-client-midlet)

# Lightstreamer Compatibility Notes #

- Compatible with Lightstreamer Java ME Client API v. 3.2.1 or newer.
- For Lightstreamer Allegro (+ Java ME Client API), Presto, Vivace.

