package cn.edu.hit.lib.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.AsyncTask;
import android.util.Log;

public class SessionIDGetter extends AsyncTask<Void, Long, String>{

	@Override
	protected String doInBackground(Void... params) {
		URL url;
		String result = "";
		try {
			url = new URL(URLContainer.SCRIPTURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setDoOutput(true);
			con.setConnectTimeout(10000);
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				result += line;
			}
			Log.e("cookie", con.getHeaderFields().toString());
			return result;
		} catch (MalformedURLException e) {
			Log.e("SessionIDGetter", e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("SessionIDGetter", e.toString());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		if(result == null) return;
		Pattern pattern = Pattern.compile("dwr\\.engine\\._origScriptSessionId = \"(\\w+)\"");
		Matcher matcher = pattern.matcher(result);
		if(matcher.find()){
			QueryURLFormatter.SCRIPT_SESSIONID = matcher.group(1);
		}
		super.onPostExecute(result);
	}

}
