package com.zhl.job.util.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class HttpHostnameVerifier implements HostnameVerifier{

	public HttpHostnameVerifier(){
	}

	public boolean verify(String hostname, SSLSession session){
		return true;
	}
}
