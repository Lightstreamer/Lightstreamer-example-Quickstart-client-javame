/*
 *
 * Copyright (c) Lightstreamer Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package javametest;

import java.util.NoSuchElementException;
import java.util.Vector;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.lightstreamer.javameclient.midp.ConnectionInfo;
import com.lightstreamer.javameclient.midp.ConnectionPolicy;
import com.lightstreamer.javameclient.midp.ExtendedTableInfo;
import com.lightstreamer.javameclient.midp.LSClient;
import com.lightstreamer.javameclient.midp.MessageInfo;
import com.lightstreamer.javameclient.midp.SimpleTableInfo;
import com.lightstreamer.javameclient.midp.SubscribedTableKey;
import com.lightstreamer.javameclient.midp.logger.ErrorPrompt;
import com.lightstreamer.javameclient.midp.logger.Logger;


/**
 * Class Tester.
 */
public class Tester extends MIDlet implements CommandListener {

    static boolean useSingleConnection = false;
    static boolean useReusableItemUpdates = true;
    static boolean useSocket = true;
    
    static boolean subscribeStockSimple = false;
    static boolean subscribeStockExt = true;
    static boolean subscribePortSimple = false;
    static boolean subscribePortExt = false;
    
    private long hbTime = 2000;

    static {
        Logger.setDefaultActive(true);
    }

    private TestConnectionListener myConnListener =
        new TestConnectionListener();
    private LSClient myClient;

    private Vector subscribedTables = new Vector();

    private TestCanvas myCanvas = new TestCanvas();
    private Display display;
    private Command exit;
    private Command disconnect;
    private Command deleteATable;
    private Command polling;
    private Command streaming;
    private Command addTable;
    private Command switchHB;
    private Command reduceFreq;
    private Command riseFreq;
    private Command sendSomeMessages;
    private Command sendSomeMessagesAndClose;
    private Command switchView; 
   
    private Logger logger = Logger.getLogger("Local logger");
    private ConnectionInfo myCI;
    private ConnectionPolicy myCP;

    private ErrorPrompt prompt;
    private ErrorPrompt sendMessageResponse;
    
    private TestSendMessageListener tsml;
    
    
    /**
     * Constructor Tester.
     */
    public Tester() {
        super();

        logger.log("TEST STARTS");

        display = Display.getDisplay(this);
        display.setCurrent(myCanvas);
        
        prompt = new ErrorPrompt(this,50);
        sendMessageResponse = new ErrorPrompt(this,200);
        
        

        //configure buttons
        
        disconnect = new Command("Disconnect", Command.STOP, 2);
        myCanvas.addCommand(disconnect);
        
        deleteATable = new Command("Remove a table", Command.OK, 2);
        myCanvas.addCommand(deleteATable);
        
        polling = new Command("Polling", Command.OK, 2);
        myCanvas.addCommand(polling);
        
        streaming = new Command("Streaming", Command.OK, 2);
        myCanvas.addCommand(streaming);
        
        addTable = new Command("Add a table", Command.OK, 2);
        myCanvas.addCommand(addTable);
        
        switchHB = new Command("enable/disable reverse HB", Command.OK, 2);
        myCanvas.addCommand(switchHB);
        
        reduceFreq = new Command("Set a frequency of 0.1", Command.OK, 2);
        myCanvas.addCommand(reduceFreq);
        
        riseFreq = new Command("Set an unlimited frequency", Command.OK, 2);
        myCanvas.addCommand(riseFreq);
        
        sendSomeMessages = new Command("Send some messages", Command.OK, 2);
        myCanvas.addCommand(sendSomeMessages);
        
        sendSomeMessagesAndClose = new Command("Send mexs and close", Command.OK, 2);
        myCanvas.addCommand(sendSomeMessagesAndClose);
        
        exit = new Command("Quit", Command.STOP, 1);
        myCanvas.addCommand(exit);

        switchView = new Command("Switch", Command.OK, 2);
        myCanvas.addCommand(switchView);
        
        
        

        
        
        
        myCanvas.setCommandListener(this);

        //Configure connection infos
        //change these configurations to point to your local server
        //
        myCI = new ConnectionInfo("localhost");
        myCI.setAdapter("FULLPORTFOLIODEMO");
        myCI.setControlInHttps(false);

        myCI.setControlPort(8989);
        myCI.setPort(8989);

        //myCI.setPassword("NO");
        //myCI.setUser("NO");

        myCI.setWorkInHttps(false);
        
        

        //Configure connection policy
        myCP = new ConnectionPolicy();
        //myCP.setRetryTimeout(500);
        //myCP.setBufferedStreamingHandled(false);
        //myCP.setIdleTimeout(30000);
        //myCP.setPollingInterval(500);
        //myCP.setKeepAliveInterval(2000);
        //myCP.setTimeoutForStalled(2000);
        //myCP.setTimeoutForReconnect(15000);
        
        
        
        
    }
    
    protected LSClient prepareLSClient() {
        LSClient aClient = new LSClient();
        aClient.useReusableItemUpdates(useReusableItemUpdates);
        aClient.useSingleConnection(useSingleConnection);
        aClient.useSocketConnection(useSocket);
        aClient.setReverseHeartbeatMillis(hbTime);
        return aClient;
    }

    /**
     * Method startApp.
     * @throws MIDletStateChangeException
     */
    protected void startApp() throws MIDletStateChangeException {
        this.myClient = this.prepareLSClient();
        
        
        
        tsml = new TestSendMessageListener(sendMessageResponse,myClient);
        
        SubscribedTableKey tableKey;

        if (subscribeStockSimple) {
            //create and subscribe a table
            SimpleTableInfo mySTable =
                new SimpleTableInfo("item1 item2", "stock_name last_price time",
                                    "MERGE");
            mySTable.setDataAdapter("QUOTE_ADAPTER");
            mySTable.setSnaspshotRequired(true);
            tableKey = myClient.subscribeTable(mySTable, new TestSimpleListener());
            subscribedTables.addElement(tableKey);
         
            //create and subscribe the table with a different listener
            tableKey = myClient.subscribeTable(mySTable, new TestHandyListener(3,"StockNames"), false);
            subscribedTables.addElement(tableKey);
        }
        
        if (subscribeStockExt) {
            //create and subscribe another table            
            ExtendedTableInfo myETable = new ExtendedTableInfo(
                new String[]{"item1", "item2"},
                new String[]{"stock_name", "last_price", "time"}, "MERGE");
            myETable.setDataAdapter("QUOTE_ADAPTER");
            myETable.setSnaspshotRequired(true);
            tableKey = myClient.subscribeTable(myETable, new TestExtendedListener());
            subscribedTables.addElement(tableKey);
    
            //create and subscribe the new table with a different listener
            tableKey = myClient.subscribeTable(myETable, new TestHandyListener(new String[]{"stock_name", "last_price", "time"},"StockFields"), false);
            subscribedTables.addElement(tableKey);
        }
        
        if (subscribePortSimple) {
          //create and subscribe a table in command mode
          SimpleTableInfo mySCTable =
              new SimpleTableInfo("portfolio1", "key command qty",
                                  "COMMAND");
          mySCTable.setDataAdapter("PORTFOLIO_ADAPTER");
          mySCTable.setSnaspshotRequired(true);
          tableKey = myClient.subscribeTable(mySCTable, new TestHandyListener(3,"PortfolioNames"), true);
          subscribedTables.addElement(tableKey);
        }
        
        if (subscribePortExt) {
          //create and subscribe another table  in command mode
          ExtendedTableInfo myECTable = new ExtendedTableInfo(
              new String[]{"portfolio1"},
              new String[]{"key", "command", "qty"}, "COMMAND");
          myECTable.setDataAdapter("PORTFOLIO_ADAPTER");
          myECTable.setSnaspshotRequired(true);
          tableKey = myClient.subscribeTable(myECTable, new TestHandyListener(new String[]{"key", "command", "qty"},"PortfolioFiedls"), true);
          subscribedTables.addElement(tableKey);
        }
        
        //new MessageInfoTest(prompt);
        //new MessageSequenceTest(prompt);
        
       
        myClient.openConnection(myCI, myConnListener, myCP);
        //myClient.openPollingConnection(myCI,myConnListener, myCp);

        //If we have called no methods on myCP (ie default policies) 
        //we could use the overloaded open methods:
        //myClient.openConnection(myCI, myConnListener);
        //myClient.openPollingConnection(myCI,myConnListener);
        
        
        
    }
    
    

    /**
     * Method pauseApp.
     */
    protected void pauseApp() {
    }

    /**
     * Method destroyApp.
     *
     * @param  arg0  ...
     * @throws MIDletStateChangeException
     */
    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
        //ends LSClient threads.
        LSClient.closeApp();
    }

    /**
     * Method commandAction.
     *
     * @param  com  ...
     * @param  arg1  ...
     */
    public void commandAction(Command com, Displayable arg1) {
        if (com == disconnect) {
            logger.log("DISCONNECT");
            myClient.closeConnection();

        } else if (com == polling) {
            logger.log("POLLING CONNECTION");
            myClient.openPollingConnection(myCI, myConnListener, myCP);

        } else if (com == streaming) {
            logger.log("STREAMING CONNECTION");
            myClient.openConnection(myCI, myConnListener, myCP);

        } else if (com == switchHB) {
            hbTime = (hbTime > 0) ? 0 : 2000;
            logger.log("NEW REVERSE HEARTBEAT TIME " + hbTime);
            myClient.setReverseHeartbeatMillis(hbTime);
            
        } else if (com == addTable) {
            if (useSingleConnection) {
                //in single connection mode we need to disconnect before change subscriptions
                myClient.closeConnection();
            }

            logger.log("ADD A TABLE");
            //create and configure new Table
            ExtendedTableInfo myETable = new ExtendedTableInfo(
                new String[]{"item1", "item11", "item3", "item2"},
                new String[]{"stock_name", "last_price", "time"}, "MERGE");
            myETable.setDataAdapter("QUOTE_ADAPTER");
            myETable.setSnaspshotRequired(true);
            myETable.setItemsRange(2, 3);
            myETable.setRequestedBufferSize(10);
            myETable.setRequestedMaxFrequency(1);
            //myETable.setSelector("NO");
            //myETable.setUnfilteredMaxFrequency();
            //myETable.setUnlimitedBufferSize();
            //myETable.setUnlimitedMaxFrequency();

            //Subscribe the new table
            SubscribedTableKey toAdd =
                myClient.subscribeTable(myETable, new TestHandyListener(new String[]{"stock_name", "last_price", "time"},"AddedTable"), false);
            //Add the table as last element of the Vector
            subscribedTables.addElement(toAdd);

            if (useSingleConnection) {
                //singleConnectionMode: need to reconnect
                myClient.openConnection(myCI, myConnListener);
            }

        } else if (com == reduceFreq) {
            logger.log("REDUCE FREQUENCY LIMIT");
            if (useSingleConnection) {
                //singleConnectionMode: need to disconnect before change subscriptions
                myClient.closeConnection();
            }

            //get all tables from the Vector...
            for (int i = 0; i < subscribedTables.size(); i++) {
                SubscribedTableKey key = (SubscribedTableKey) subscribedTables.elementAt(i);
                myClient.setMaxFrequency(key, 0.1);
            }

            if (useSingleConnection) {
                //in single connection mode we need to reconnect now
                myClient.openConnection(myCI, myConnListener);
            }
        } else if (com == riseFreq) {
            logger.log("INCREASE FREQUENCY LIMIT");
            if (useSingleConnection) {
                //singleConnectionMode: need to disconnect before change subscriptions
                myClient.closeConnection();
            }

            //get all tables from the Vector...
            for (int i = 0; i < subscribedTables.size(); i++) {
                SubscribedTableKey key = (SubscribedTableKey) subscribedTables.elementAt(i);
                myClient.setFrequencyUnlimited(key);
            }

            if (useSingleConnection) {
                //in single connection mode we need to reconnect now
                myClient.openConnection(myCI, myConnListener);
            }

        } else if (com == deleteATable) {
            logger.log("DELETE A TABLE");
            if (useSingleConnection) {
                //singleConnectionMode: need to disconnect before change subscriptions
                myClient.closeConnection();
            }

            try {
                //get the first table from the Vector...
                SubscribedTableKey toRem =
                    (SubscribedTableKey) subscribedTables.firstElement();
                //...and unsubscribe it...
                myClient.unsubscribeTable(toRem);
                //...then remove from the Vector too.
                subscribedTables.removeElement(toRem);
            } catch (NoSuchElementException nse) {
                logger.log("NO MORE TABLES, CAN'T DELETE");
            }

            if (useSingleConnection) {
                //in single connection mode we need to reconnect now
                myClient.openConnection(myCI, myConnListener);
            }
        } else if (com == switchView) {
            prompt.show();
            
        } else if (com == exit) {
            logger.log("BYE BYE");
            notifyDestroyed();
        }
    }

}


/*--- Formatted in Lightstreamer Java Convention Style on 2007-02-12 ---*/
