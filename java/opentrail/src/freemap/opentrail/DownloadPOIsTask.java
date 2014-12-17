package freemap.opentrail;

import java.util.Map;
import java.util.Set;

import freemap.data.Point;
import freemap.datasource.FreemapDataset;
import freemap.datasource.TiledData;
import android.content.Context;
import freemap.datasource.CachedTileDeliverer;
import android.util.Log;
import freemap.andromaps.DataCallbackTask;
import org.mapsforge.core.model.LatLong;


public class DownloadPOIsTask extends DataCallbackTask<Void,Void>  {

	
	
	
	CachedTileDeliverer td;
	boolean forceWebDownload;
	LatLong location;
	
	public DownloadPOIsTask(Context ctx, CachedTileDeliverer td, DataReceiver receiver, boolean showDialog,
								boolean forceWebDownload, LatLong location)
	{
		super(ctx,receiver);
		setShowProgressDialog(showDialog);
		setShowDialogOnFinish(showDialog);
		setDialogDetails("Downloading...","Downloading POIs...");
		this.td=td;
		this.forceWebDownload=forceWebDownload;
		this.location=location;
	}
	

	public String doInBackground(Void... unused)
	{
		
		
		try
		{
		 
		    td.setForceReload(forceWebDownload);
		    
			Point p = new Point(location.longitude,location.latitude);
			Log.d("OpenTrail","Updating data with point: " + p);
			Log.d("OpenTrail","getSurroundingTiles()retuend:" +td.updateSurroundingTiles(p));
			//setData((FreemapDataset)td.getAllData());
			Log.d("OpenTrail","done");
			

			
			/* old TileDeliverer getAllData() code */
			FreemapDataset allData = new FreemapDataset();
	        allData.setProjection(td.getProjection());
	        Set<Map.Entry<String, TiledData>> entries = td.getAllTiles();
	        for(Map.Entry<String, TiledData> e: entries)
	        {
	            allData.merge(e.getValue());
	        }
	        setData(allData);
	        return "Successfully downloaded";
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
	public void receive(Object data)
	{
		if(receiver!=null)
			((DataReceiver)receiver).receivePOIs((FreemapDataset)data);
	}
}
