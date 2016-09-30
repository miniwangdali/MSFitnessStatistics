package pentagram.msfitnessstatic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class BandDataSenderService extends Service {
    Socket socket = null;
    Service service = null;
    public BandDataSenderService() {
        service = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("service", "service Started!");
        Toast.makeText(this, "service starting!", Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);
                        socket = new Socket();
                        socket.connect(new InetSocketAddress("172.16.15.21", 12306), 5000);
                        OutputStream outputStream = socket.getOutputStream();
                        //Log.d("send", "run: sending data");
                        String output = DataValue.getAllData();
                        outputStream.write(output.getBytes("utf-8"));
                        outputStream.flush();
                        //socket.shutdownOutput();
                        outputStream.close();
                        socket.close();
                    } catch (IOException e) {
                        Log.e("socket error!", e.toString());
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

}
