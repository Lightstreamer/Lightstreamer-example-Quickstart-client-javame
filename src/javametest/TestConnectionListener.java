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

import com.lightstreamer.javameclient.midp.ConnectionListener;
import com.lightstreamer.javameclient.midp.logger.Logger;
import com.lightstreamer.javameclient.midp.logger.NumberedLogger;

/**
 * Class TestConnectionListener.
 * Receive status changes (implementing ConnectionListener) and
 * use the Logger class to print them on console
 */
public class TestConnectionListener implements ConnectionListener {

    /** Field $objectName$. */
    static int instId = 1; 

    /** Field $objectName$. */
    NumberedLogger logger = Logger.getNumberedLogger("ConnectionListener",instId++);

    /**
     * Method onStatusChange.
     *
     * @param  newStatus  ...
     */
    public void onStatusChange(String newStatus) {
        logger.log("STATUS: " + newStatus);
    }

    /**
     * Method onError.
     *
     * @param  error  ...
     */
    public void onError(String error) {
        logger.log("ERROR " + error);
    }

    /**
     * Method onServerError.
     *
     * @param  code  ...
     * @param  error  ...
     */
    public void onServerError(int code, String error) {
        this.onError("Server -> " + code + " - " + error);

    }

    /**
     * Method onClientError.
     *
     * @param  error  ...
     */
    public void onClientError(String error) {
        this.onError("Client -> " + error);

    }

    /**
     * Method onBufferFull.
     */
    public void onBufferFull() {
        logger.log("Buffer full");
    }

    /**
     * Method onConnectionEnd.
     * @param  cause  ...
     */
	public void onConnectionEnd(int cause) {
		this.onError("On Connection End: " + cause);
	}

	

}


/*--- Formatted in Lightstreamer Java Convention Style on 2007-02-12 ---*/
