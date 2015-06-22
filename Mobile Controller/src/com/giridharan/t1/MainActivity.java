package com.giridharan.t1;
import android.support.v7.app.ActionBarActivity;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends ActionBarActivity {
EditText c,r,s;
Button e;
LinearLayout l;
String cc,ss,rr;
public static final String MyPREFERENCES = "MyPrefs" ;
public static final String Call = "callKey"; 
public static final String Silence = "silenceKey"; 
public static final String Ring = "ringKey"; 
SharedPreferences sharedpreferences;
ImageView i;
private AudioManager myAudioManager;
private static MainActivity inst;

public static MainActivity instance() {
    return inst;
}

@Override
public void onStart() {
    super.onStart();
    inst = this;
}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		l=(LinearLayout)findViewById(R.id.linearLayout1);
		c=(EditText)findViewById(R.id.editText1);
		s=(EditText)findViewById(R.id.editText2);
		r=(EditText)findViewById(R.id.editText3);
		i=(ImageView)findViewById(R.id.imageView1);
		e=(Button)findViewById(R.id.button2);
	      myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		e.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
            	   cc  = c.getText().toString();
         	       ss  = s.getText().toString();
         	      rr = r.getText().toString();
         	      Editor editor = sharedpreferences.edit();
         	      editor.putString(Call, cc);
         	      editor.putString(Silence, ss);
         	      editor.putString(Ring, rr);
         	      
         	      editor.commit(); 
	
               Toast.makeText(getApplicationContext(),"When you Send corresponding text as a message from any number your mobile respond ",Toast.LENGTH_LONG).show();
            }

						
	 });
	
	sharedpreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

    if (sharedpreferences.contains(Call))
    {
       c.setText(sharedpreferences.getString(Call, ""));

    }
    if (sharedpreferences.contains(Silence))
    {
       s.setText(sharedpreferences.getString(Silence, ""));

    }
    if (sharedpreferences.contains(Ring))
    {
       r.setText(sharedpreferences.getString(Ring, ""));

    }
}
	public void check(String mm,String ph)
	{
		if(mm.equals(cc)){ makeCall(ph);}
		else if(mm.equals(ss)){silent();}
		else if(mm.equals(rr)){ring();}
		else{}
		
	}
	
	public void makeCall(String phone) {
	      Intent phoneIntent = new Intent(Intent.ACTION_CALL);
	      phoneIntent.setData(Uri.parse("tel:"+phone));

	      try {
	         startActivity(phoneIntent);
	         finish();
	         Log.i("Finished making a call...", "");
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(MainActivity.this, 
	         "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
	      }
	}
  
  public void ring(){
	      myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	   }
	   public void silent(){
	      myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	   }

}