/* author: lrchang2
   date: 4/6/15
 */
package uiuc.pebble_sec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.Telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver {

    final SmsManager sms = SmsManger.getDefault();

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
            final Bundle bundle = getintent().getExtra();

            try {
                if (bundle != null) {
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < pdusObj.length; i++) {
                        SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        String text = messages.getDisplayMessageBody().toString();

                        Log.i("Receiver", "message: " + text);
                    }

                    //message alert to check if it works after try
                    Toast.makeText(context, "Received Text: " + text, Toast.LENGTH_LONG).show();


                    String action = intent.getAction();
                    Log.i("Receiver", "Broadcast received: "+action);
                    if(action.equals("my.action.string")){
                        String state = intent.getExtras().getString("extra");

                    //send broadcast to RSA code
                    Intent i = new Intent("my.action.string");
                    intent.putExtra("text","bundle");
                    intent.setAction("com.android.activity.SEND_DATA");
                    context.sendBroadcast(i);

                    /*put in RSA
                    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver(){
                        @Override
                        public void OnReceive(Context context, Intent intent){
                            String text = intent.getStringExtra("text");
                            Log.d("receiver","Got message: "+message);*/

                    //Register Receiver
                        LocalBroadcastManager.getInstance(mContext.registerReceiver(mMessageReceiver, new IntentFilter("com.android.activity.SEND_DATA")));

                        }
                    }


                }


            }
            catch(Exception e){
                Log.e("Receiver","Exception Receiver" +e);


            }

    }
}
