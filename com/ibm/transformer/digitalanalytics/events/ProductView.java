package com.ibm.transformer.digitalanalytics.events;

	import java.io.DataInput;
	import java.io.DataOutput;
	import java.io.IOException;
	import java.io.Serializable;
	import java.util.Arrays;
	import java.util.Date;

	public class ProductView extends DAEvent implements Serializable {
		public static final long serialVersionUID = 1L;
		public String hourID = "";
		public String destinationUrl = "";
		public String clientPageID = "";
		public String pageName = "";
		public String productID = "";
		public String productName = "";
		public String ioVC = "";
		public String clientSiteLocationID = "";
		public String clientSiteLocationName = "";
		public String nonArgsTimestamp = "";
		public String ipAddress = "";
		public String clientIds = "";
		public String cookieSettings = "";
		public String modVers = "";
		public String valid = "";
		public String lang = "";
		public String coreMsg = "";
		public String browserName = "";
		public String browserVersion = "";
		public String osName = "";
		public String osVersion = "";
		public String[] productAttributes = {};
		public String[] extraFields = {};
		public String[] reportAttributes = {};
		public String siteID = "";
		public String color = "";
		public String size = "";
		public String imageURL = "";
		public String productURL = "";

		public ProductView() {
			super();
		}


		

	
	}


