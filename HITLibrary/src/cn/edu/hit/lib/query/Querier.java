package cn.edu.hit.lib.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class Querier extends AsyncTask<Void, Long, String>{
	public String params;
	public OnQueryFinishedListener listener;
	
	public Querier(String params){
		this.params = params;
	}
	
	public void setOnQueryFinishedListener(OnQueryFinishedListener listener) {
		this.listener = listener;
	}

	@Override
	protected String doInBackground(Void... params) {
		URL url;
		String result = "";
		try {
			url = new URL(URLContainer.BASEURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setDoOutput(true);
			con.setConnectTimeout(10000);
			con.setRequestMethod("POST");
			OutputStream out = con.getOutputStream();
			String request = this.params;
			out.write(request.getBytes());
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				result += line;
			}
			return result;
		} catch (MalformedURLException e) {
			Log.e("QuerierResult", e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("QuerierResult", e.toString());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		listener.onFinished(result);
		super.onPostExecute(result);
	}
	
	public interface OnQueryFinishedListener{
		public void onFinished(String result);
	}
}
