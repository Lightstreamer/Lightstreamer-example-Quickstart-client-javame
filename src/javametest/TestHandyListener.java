/*
 *
 * Copyright 2014 Weswit s.r.l.
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

import com.lightstreamer.javameclient.midp.UpdateInfo;
import com.lightstreamer.javameclient.midp.HandyTableListener;
import com.lightstreamer.javameclient.midp.logger.Logger;
import com.lightstreamer.javameclient.midp.logger.NumberedLogger;

/**
 * Class TestHandyListener.
 * Receive table updates (implementing HandyTableListener) and
 * use the Logger class to print them on console
 */
public class TestHandyListener implements HandyTableListener {
 
    /** Field $objectName$. */
    static int instId = 1;

    /** Field $objectName$. */
    private NumberedLogger logger;
    
    private int numFields = 0;
    private String[] schema = {};
    private boolean byName = false;
    
    public TestHandyListener(String[] schema, String name) {
      this.byName = true;
      this.schema = schema;
      this.numFields = schema.length;
      this.logger = Logger.getNumberedLogger(name,instId++);
    }
    
    public TestHandyListener(int numFields, String name) {
      this.numFields = numFields;
      this.logger = Logger.getNumberedLogger(name,instId++);
    }
    
    private boolean checkConsinstency(String itemName) {
      if (this.byName && itemName == null) {
        logger.log("ERROR, EXPECTING ITEMNAME");
        return false;
      } else if (!this.byName && itemName != null) {
        logger.log("ERROR, NOT EXPECTING ITEMNAME");
        return false;
      }
      return true;
    }
    
    /**
     * Method onUpdate.
     *
     * @param  itemPos  ...
     * @param  itemName  ...
     * @param  update  ...
     */
    public void onUpdate(int itemPos, String itemName, UpdateInfo update) {
      if (!this.checkConsinstency(itemName)) {
        return;
      }
      
      StringBuffer strToLog = new StringBuffer("Item ");
      
      strToLog.append(itemPos);
      if (this.byName){
        strToLog.append("/");
        strToLog.append(itemName);
      }
      
      for (int i=0; i<this.numFields; i++) {
        strToLog.append(i+1);
        strToLog.append("/");
        if (this.byName) {
          if (!update.getNewValue(this.schema[i]).equals(update.getNewValue(i+1))) {
            logger.log("ERROR, FIELD AND NUM FIELD DO NOT MATCH");
            return;
          }
              
          strToLog.append(this.schema[i]);
          strToLog.append("/");
          strToLog.append(update.getNewValue(this.schema[i]));
        } else {
          strToLog.append(update.getNewValue(i+1));
        }
          
        strToLog.append(" | ");
      }
      
      logger.log(strToLog.toString());
    }

    /**
     * Method onSnapshotEnd.
     *
     * @param  itemPos  ...
     * @param  itemName  ...
     */
    public void onSnapshotEnd(int itemPos, String itemName) {
      if (!this.checkConsinstency(itemName)) {
        return;
      }  
      if (this.byName) {
        logger.log("EOS item " + itemPos + "/" + itemName);
      } else {
        logger.log("EOS item " + itemPos);
      }
    }

    /**
     * Method onRawUpdatesLost.
     *
     * @param  itemPos  ...
     * @param  itemName  ...
     * @param  lostUpdates  ...
     */
    public void onRawUpdatesLost(int itemPos, String itemName, int lostUpdates) {
      if (!this.checkConsinstency(itemName)) {
        return;
      } 
      if (itemName != null) {
        logger.log("LOST " + lostUpdates + " updates for item " + itemPos + "/" + itemName);
      } else {
        logger.log("LOST " + lostUpdates + " updates for item " + itemPos);
      }
    }

    /**
     * Method onUnsubscr.
     *
     * @param  itemPos  ...
     * @param  itemName  ...
     */
    public void onUnsubscr(int itemPos, String itemName) {
      if (!this.checkConsinstency(itemName)) {
        return;
      } 
      if (itemName != null) {
        logger.log("UNSUBSCRIBED item " + itemPos + "/" + itemName);
      } else {
        logger.log("UNSUBSCRIBED item " + itemPos);
      }
    }

    /**
     * Method onUnsubscrAll.
     */
    public void onUnsubscrAll() {
      logger.log("UNSUBSCRIBED!");
    }

    /**
     * Method onControlError.
     */
    public void onControlError(int code, String msg) {
      logger.log("TABLE ERROR: " + code + " - " + msg);
	}

}


/*--- Formatted in Lightstreamer Java Convention Style on 2007-02-12 ---*/
