package com.muyi.imagetest;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.esri.android.map.MapView;
import com.esri.android.map.RasterLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.map.event.OnStatusChangedListener.STATUS;
import com.esri.android.runtime.ArcGISRuntime;
import com.esri.core.raster.FileRasterSource;


public class ImageTestActivity extends Activity {
	
	MapView mMapView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArcGISRuntime.setClientId("uK0DxqYT0om1UXa9");
        setContentView(R.layout.main);

		mMapView = (MapView)findViewById(R.id.map);
		FileRasterSource fileRasterSource;
		try {
			//fileRasterSource = new FileRasterSource("///mnt/sdcard/test/test.img");
			fileRasterSource = new FileRasterSource("///mnt/sdcard/test/img/test.tif");
			RasterLayer rasterLayer = new RasterLayer(fileRasterSource);
			mMapView.addLayer(rasterLayer);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mMapView.setOnStatusChangedListener(new OnStatusChangedListener() {
			
			public void onStatusChanged(Object source, STATUS status) {
				if (source == mMapView && status == STATUS.INITIALIZED) {
					Toast.makeText(getApplicationContext(), mMapView.getSpatialReference().toString(), Toast.LENGTH_LONG).show();
				}
				
			}
		});
		

    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
 }
	@Override
	protected void onPause() {
		super.onPause();
		mMapView.pause();
 }
	@Override
	protected void onResume() {
		super.onResume();
		mMapView.unpause();
	}

}