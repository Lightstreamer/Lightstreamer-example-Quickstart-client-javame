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

import com.lightstreamer.javameclient.midp.ExtendedItemUpdate;
import com.lightstreamer.javameclient.midp.ExtendedTableListener;
import com.lightstreamer.javameclient.midp.logger.Logger;
import com.lightstreamer.javameclient.midp.logger.NumberedLogger;

/**
 * Class TestExtendedListener.
 * Receive table updates (implementing ExtendedTableListener) and
 * use the Logger class to print them on console
 */
public class TestExtendedListener implements ExtendedTableListener {

    /** Field $objectName$. */ 
    static int instId = 1;

    /** Field $objectName$. */
    NumberedLogger logger = Logger.getNumberedLogger("TestExtendedListener",instId++);

    /**
     * Method onUpdate.
     *
     * @param  item  ...
     * @param  update  ...
     */
    public void onUpdate(String item, ExtendedItemUpdate update) {
        logger.log("Item " + item + " -> " + update.getFieldNewValue("stock_name") + " | " + update.getFieldNewValue("last_price") + " | " + update.getFieldNewValue("time"));
    }

    /**
     * Method onEndOfSnapshot.
     *
     * @param  item  ...
     */
    public void onEndOfSnapshot(String item) {
        logger.log("EOS item " + item);
    }

    /**
     * Method onRawUpdatesLost.
     *
     * @param  item  ...
     * @param  lostUpdates  ...
     */
    public void onRawUpdatesLost(String item, int lostUpdates) {
        logger.log("LOST " + lostUpdates + " updates for item " + item);
    }

    /**
     * Method onUnsubscribe.
     */
    public void onUnsubscribe() {
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
