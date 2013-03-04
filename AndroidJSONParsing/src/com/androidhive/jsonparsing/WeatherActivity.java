package com.androidhive.jsonparsing;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WeatherActivity extends Activity 
{
	
	//TAG_ARR means that the string is for an array
	//Information from the weather underground:
	
	private static String weather_url = "http://api.wunderground.com/api/077f406152c256e2/conditions/q/GA/Atlanta.json";
	private static final String TAG_RESPONSE = "response";
	private static final String TAG_ARR_CURRENT_OBSERVATION = "current_observation";
	private static final String TAG_TEMP_C = "temp_c";
	private static final String TAG_TEMP_F = "temp_f";
	
	private static final String TAG_DISPLAY_LOCATION = "display_location";
	private static final String TAG_CITY = "city";
	private static final String TAG_STATE = "state";
	private static final String TAG_LATITUDE = "latitude";
	private static final String TAG_LONGITUDE = "longitude";
	private static final String TAG_ELEVATION = "elevation";
	private static final String TAG_ARR_OBSERVATION_LOCATION = "observation_location";

	
	
	JSONObject weather = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_layout);
	
		// Creating JSON Parser instance
				JSONParser jParser = new JSONParser();				
		//weather_url
		JSONObject jsonWeather= jParser.getJSONFromUrl(weather_url);
		
		try{
			//Get the object which has the stuff for temperature inside
			weather = jsonWeather.getJSONObject(TAG_ARR_CURRENT_OBSERVATION);
			JSONObject location = weather.getJSONObject(TAG_DISPLAY_LOCATION);

			String temp_f = weather.getString(TAG_TEMP_F);
			String temp_c = weather.getString(TAG_TEMP_C);
			String city = location.getString(TAG_CITY);
			
			
			TextView locationTV = (TextView) findViewById(R.id.locationLabel);

			if(city != null)
			{
				locationTV.setText("City : " + city);
			}
			else
			{
				locationTV.setText("Location");
			}
			
			TextView temperatureTV = (TextView) findViewById(R.id.temperatureLabel);

			if(temp_f != null)
			{
				temperatureTV.setText("Temperature: " + temp_f + " F");
			}
			else
			{
				temperatureTV.setText("Temperature");
			}
			
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
}
