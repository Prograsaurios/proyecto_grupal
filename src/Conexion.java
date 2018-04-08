//Para método getInfo
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Para HTTPS
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Conexion {
	public Conexion() {
		crearTrustManager();
	}
	
	private void crearTrustManager() {
		// Create a trust manager that does not validate certificate chains like the default
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
						//No need to implement.
					}
					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
						//No need to implement.
					}
				}
		};

		//Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void getInfo(){
		try {
			String url = "https://www.sernac.cl/wp-content/themes/gobCL-sitios-1.0/sip/apiSIP.php";
			URL objURL = new URL(url);
			HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
			
			/*
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept"," * /*");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			con.setRequestProperty("Accept-Language", "es-ES,es;q=0.9");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("Content-Length", "106");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			con.setRequestProperty("Cookie", "__utmz=214185237.1522155426.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); PHPSESSID=b6c8cthb0dlme0u7r78uo0vm01; __utma=214185237.1299871094.1521852101.1523131678.1523228574.4; __utmc=214185237; __utmt=1; __utmb=214185237.2.10.1523228574");
			con.setRequestProperty("DNT", "1");
			con.setRequestProperty("Host", "www.sernac.cl");
			con.setRequestProperty("Origin", "https://www.sernac.cl");
			con.setRequestProperty("Referer", "https://www.sernac.cl/sistema-de-informacion-de-precios/");
			con.setRequestProperty("X-Requested-With","XMLHttpRequest");
			con.setRequestProperty("aData","reporte_region=1&reporte_comuna=&select_orden=1&nombre_medicamento_TMP=salofalk");
			*/

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
