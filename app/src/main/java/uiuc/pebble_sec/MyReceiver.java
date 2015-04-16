/* author: lrchang2
   date: 4/6/15
 */
package uiuc.pebble_sec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.*;
import android.util.Log;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();

    private static final String TAG = "SMSBroadcastReceiver";


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
    String action = intent.getAction();
    Log.i(TAG,"Broadcast received: " + intent.getAction());
        //gets SMS message
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            //gets bundle object from correct place
            final Bundle bundle = intent.getExtras();

            try {
                if (bundle != null) {
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < pdusObj.length; i++) {
                        SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String text = messages.getDisplayMessageBody();
                        String sender = messages.getDisplayOriginatingAddress();

                        Log.i("Receiver", "sender: " + sender + "message: " + text);


                        //message alert to check if it works after try
                        Toast.makeText(context, "sender" + sender + "Received Text: " + text, Toast.LENGTH_LONG).show();
                    }
                }


            } catch (Exception e) {
                Log.e("Receiver", "Exception Receiver" + e);
            }
        }
    }
}
