package com.example.liuwangshu.moonaidl;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;
public class AIDLActivity extends AppCompatActivity {
    private final static String TAG="AIDLActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent mIntent=new Intent(AIDLActivity.this,AIDLService.class);
        bindService(mIntent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

   private ServiceConnection mServiceConnection=new ServiceConnection() {
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           IGameManager iGameManager=IGameManager.Stub.asInterface(service);
           Game game=new Game("月影传说","最好玩的武侠单机游戏");
           try {
               iGameManager.addGame(game);
               List<Game> mList=iGameManager.getGameList();
               for(int i=0;i<mList.size();i++){
                   Game mGame=mList.get(i);
                   Log.i(TAG,mGame.gameName+"---"+mGame.gameDescribe);
               }
           } catch (RemoteException e) {
               e.printStackTrace();
           }
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {

       }
   };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);

    }




}
