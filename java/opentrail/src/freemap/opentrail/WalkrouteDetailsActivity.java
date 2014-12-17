package freemap.opentrail;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class WalkrouteDetailsActivity extends Activity implements View.OnClickListener {
	
	Button ok, cancel;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.walkroutedetails);
		ok = (Button)findViewById(R.id.btnWrOk);
		//cancel = (Button)findViewById(R.id.btnWrCancel);
		ok.setOnClickListener(this);
		//cancel.setOnClickListener(this);
	}

	public void onClick(View v)
	{
		Intent intent = new Intent();
		if(v==ok)
		{
			Bundle extras = new Bundle();
			extras.putString("freemap.opentrail.wrfilename", 
				((EditText)findViewById(R.id.etWrFilename)).getText().toString());
			extras.putString("freemap.opentrail.wrtitle", 
					((EditText)findViewById(R.id.etWrTitle)).getText().toString());
			extras.putString("freemap.opentrail.wrdescription", 
					((EditText)findViewById(R.id.etWrDescription)).getText().toString());
			intent.putExtras(extras);
			setResult(RESULT_OK, intent);
		}
		/*
		else if (v==cancel)
		{
			setResult(RESULT_CANCELED, intent);
		}
		*/
		
		finish();
	}
}
