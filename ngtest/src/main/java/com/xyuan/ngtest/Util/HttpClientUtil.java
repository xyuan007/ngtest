package com.xyuan.ngtest.Util;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;


public class HttpClientUtil {
	private static DefaultHttpClient httpclient = null;
	
	public static DefaultHttpClient getClientByUrl(String url){
		if(url.contains("https"))
			return (DefaultHttpClient) HttpClientUtil.newHttpsClient();
		else
			return HttpClientUtil.getClient();
	}
	
	public static DefaultHttpClient getClient(){
		if(httpclient == null){		
			httpclient = new DefaultHttpClient();
			if(ProjectPropUtil.getHttpProxy().equals("true")){
				HttpHost proxy = new HttpHost(ProjectPropUtil.getProxyIP(), ProjectPropUtil.getProxyPort());  
				httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);  
			}
		}
		return httpclient;
	}
	
	public static void closeClient() throws IOException{
		if(httpclient != null){
			httpclient.close();
		}
	}
	
    static public HttpClient newHttpsClient() {  
        try {  
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
            trustStore.load(null, null);  
  
            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);  
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
  
            HttpParams params = new BasicHttpParams();  
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);  
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);  
            HttpConnectionParams.setConnectionTimeout(params, 10000);  
            HttpConnectionParams.setSoTimeout(params, 10000);  
  
            SchemeRegistry registry = new SchemeRegistry();  
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));  
            registry.register(new Scheme("https", sf, 443));  
  
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);  
  
            httpclient = new DefaultHttpClient(ccm, params); 
        } catch (Exception e) {  
        	httpclient = new DefaultHttpClient();  
        }
        
		if(ProjectPropUtil.getHttpProxy().equals("true")){
			HttpHost proxy = new HttpHost(ProjectPropUtil.getProxyIP(), ProjectPropUtil.getProxyPort());  
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);  
		}
		return httpclient;  
    }  
  
    private static class MySSLSocketFactory extends SSLSocketFactory {  
        SSLContext sslContext = SSLContext.getInstance("TLS");  
  
        public MySSLSocketFactory(KeyStore truststore)  
                throws NoSuchAlgorithmException, KeyManagementException,  
                KeyStoreException, UnrecoverableKeyException {  
            super(truststore);  
  
            TrustManager tm = new X509TrustManager() {  
                public void checkClientTrusted(X509Certificate[] chain, String authType)  
                        throws CertificateException {  
                }  
  
                public void checkServerTrusted(X509Certificate[] chain, String authType)  
                        throws CertificateException {  
                }  
  
                public X509Certificate[] getAcceptedIssuers() {  
                    return null;  
                }  
            };  
  
            sslContext.init(null, new TrustManager[] { tm }, null);  
        }  
  
        @Override  
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose)  
                throws IOException, UnknownHostException {  
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);  
        }  
  
        @Override  
        public Socket createSocket() throws IOException {  
            return sslContext.getSocketFactory().createSocket();  
        }  
    }  
}
