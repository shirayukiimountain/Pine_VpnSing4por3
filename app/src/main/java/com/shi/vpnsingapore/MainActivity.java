
package com.shi.vpnsingapore;

import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shi.vpnsingapore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(LinearLayout.VERTICAL);
        linearlayout.setGravity(Gravity.CENTER);
        
        //boolean isPremium = true;
        String message = determineUser(); 
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        
        params.gravity = Gravity.CENTER;
        
        TextView textview = new TextView(this);
        textview.setText(message);
        textview.setTextSize(15);
        textview.setGravity(Gravity.CENTER);
        textview.setLayoutParams(params);
        
        linearlayout.addView(textview);
        
        setContentView(linearlayout);
        
    }
    
    private String determineUser() {
    	if (isPremium()) {
            return "Kamu premium";
        } else if (isMemberValid()){
            return "Premium valid";
        } else {
            return "Kamu bukan premium";
        }
    }
    
    private boolean isPremium() {
        return false;
    }
    
    private boolean isMemberValid() {
        return false;
    }
}
