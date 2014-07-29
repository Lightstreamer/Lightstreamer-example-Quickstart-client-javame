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

import com.lightstreamer.javameclient.midp.LSClient;
import com.lightstreamer.javameclient.midp.MessageInfo;
import com.lightstreamer.javameclient.midp.SendMessageListener;
import com.lightstreamer.javameclient.midp.logger.ErrorPrompt;
import com.lightstreamer.javameclient.midp.logger.Logger;
import com.lightstreamer.javameclient.midp.logger.NumberedLogger;

public class TestSendMessageListener implements SendMessageListener {

    static int instId = 1;
    private ErrorPrompt testLog;
    private LSClient client;
    
    public TestSendMessageListener(ErrorPrompt prompt,LSClient client) {
        this.testLog = prompt;
        this.client = client;
    }
    
    public void onAbort(MessageInfo originalMessage, int prog) {
       testLog.log(prog + " ->ABORTED: " + "/" + originalMessage);
    }

    public void onError(int code, String error, MessageInfo originalMessage,
            int prog) {
        testLog.log(prog + " ->ERROR: " + code + " - " + error + " / " + originalMessage);
        client.sendMessage(new MessageInfo("CHAT|V2Message" + prog,"chat",1000),this);
    }

    public void onProcessed(MessageInfo originalMessage, int prog) {
       testLog.log(prog + " ->PROCESSED: " + originalMessage);
    }

}
