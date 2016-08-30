package com.example.android.material_design.pack;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sunny on 3/3/2016.
 */
public class L {


    public static void m(String message){
        Log.d("ekta","" + message);}

   public static void t(Context context,String message){

       Toast.makeText(context,message + " ",Toast.LENGTH_SHORT).show();
   }

}
