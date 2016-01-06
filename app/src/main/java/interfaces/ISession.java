package interfaces;

import org.json.JSONObject;

/**
 * Created by julianmolina on 27/08/15.
 */
public interface ISession {

    public void onPreExecute();
    public String doInBackground();
    public void onPostExecute(String result);

}