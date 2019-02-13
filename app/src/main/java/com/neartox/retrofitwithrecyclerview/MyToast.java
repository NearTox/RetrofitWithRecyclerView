package com.neartox.retrofitwithrecyclerview;

import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

public class MyToast {
  private static Toast mToastCurrent;

  public static void EndCurrentToast() {
    if(mToastCurrent != null) {
      mToastCurrent.cancel();
    }
  }

  public static void ShowToast(String str, FragmentActivity pThis) {
    EndCurrentToast();
    mToastCurrent = Toast.makeText(pThis, str, Toast.LENGTH_SHORT);
    mToastCurrent.show();
  }
}
