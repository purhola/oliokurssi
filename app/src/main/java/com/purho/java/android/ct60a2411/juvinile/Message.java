package com.purho.java.android.ct60a2411.juvinile;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;

public class Message implements Serializable {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}

