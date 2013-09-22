package freemap.hikar;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.view.MenuItem;
import android.content.Intent;
import android.content.SharedPreferences;


public class Hikar extends Activity 
{
    LocationProcessor locationProcessor;
    ViewFragment viewFragment;
    HUD hud;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hud=new HUD(this);
        setContentView(R.layout.activity_main);
        addContentView(hud, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        viewFragment = (ViewFragment)getFragmentManager().findFragmentById(R.id.view_fragment);  
        try { JSONWayFactory.test(); } catch(org.json.JSONException e) { android.util.Log.e("hikar",e.toString()); }
    }

   
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item)
    {
        boolean retcode=false;
        
        switch(item.getItemId())
        {
            case R.id.menu_calibrate:
                viewFragment.toggleCalibrate();
                item.setTitle(item.getTitle().equals("Calibrate") ? "Stop calibrating": "Calibrate");
                retcode=true;
                break;
                
            case R.id.menu_settings:
                Intent i = new Intent(this,Preferences.class);
                startActivity(i);
        }
        return retcode;
    }
    
    public void onPause()
    {
        super.onPause();
    }
    
    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        float cameraHeight = Float.parseFloat(prefs.getString("prefCameraHeight","1.4"));
        viewFragment.setCameraHeight(cameraHeight);
    }
    
    public HUD getHUD()
    {
        return hud;
    }
}
