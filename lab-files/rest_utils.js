/*
 * Utility method to allow concurrent GET requests
 * url - The resource URL
 * onLoad - The callback method called after response is returned.
 * acceptType - The MIME type set in teh Accept header.
 */
function invokeByGet(url, onLoad, acceptType) {
	var xhr = createXHR();
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			onLoad(xhr);
			xhr = null; //Avoid IE memory leak
		}
	};
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Accept", acceptType);
	xhr.send("");
}

/*
 * Utility method that posts a DOM document.
 * url - The resource URL
 * doc - A DOM document object. This is converted to string and set as request body.
 * onLoad - The callback method called after response is returned.
 * acceptType - The MIME type set in teh Accept header.
 */
function invokeByXMLPost(url, doc, onLoad) {
	var xhr = createXHR();
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if(xhr.status == 200) {
				onLoad(xhr);
			}
			xhr = null; //Prevent memory leak in IE
		}
	};
	var xml = DOMToString(doc);
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type",
		"application/xml; charset=UTF-8");
	xhr.setRequestHeader("Accept", "application/xml");
	xhr.send(xml);
}

/*
 * Utility method to make POST requests
 * url - The resource URL
 * requestData - An object with a property for each request parameter.
 * onLoad - The callback method called after response is returned.
 * acceptType - The MIME type set in teh Accept header.
 */
function invokeByPost(url, requestData, onLoad, acceptType) {
	var xhr = createXHR();
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			onLoad(xhr);
			xhr = null; //Avoid IE memory leak
		}
	}
	var body = "";
	for (var name in requestData) {
		if (body.length > 0) {
			body = body + "&";
		}
		body = body + escape(name) + "=" + escape(requestData[name]);
	}
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type",
		"application/x-www-form-urlencoded; charset=UTF-8");
	xhr.setRequestHeader("Accept", acceptType);
	xhr.send(body);
}
/*
 * Converts a DOM document into XML text in a browser
 * neutral fashion.
 */
function DOMToString(doc) {
	var xml = null;
	try {
		//Most modern browsers
		var ser = new XMLSerializer();
		xml = ser.serializeToString(doc);
	} catch (e) {
		//Older IE
		xml = doc.xml;
	}
	
	return xml;
}

/*
 * This function adds a child element to a parent. 
 * The body text of the child element is also set.
 * 
 * doc - The DOM document.
 * parent - The parent element.
 * name - The name of tag of the element to be created.
 * text - The body text of the newly created element.
 */
function addElement(doc, parent, name, text) {
	var e =  doc.createElement(name);
	var txt = doc.createTextNode(text);
	
	e.appendChild(txt);
	parent.appendChild(e);
	
	return e;
}

//Shortcut method to get input values
function getInput(name) {
	return document.getElementsByName(name)[0].value;
}

/*
 * This method searches for an element by its name within a parent and returns
 * its body text.
 * 
 * parent - The parent Element
 * elementName - The name of the element.
 */
function getElementText(parent, elementName) {
	var list = parent.getElementsByTagName(elementName);
	if (list == null || list.length == 0) {
		//Invalid element name
		return null;
	}
	var e = list[0];
	var txt = "";
	
	for (var i = 0; i < e.childNodes.length; ++i) {
		var n = e.childNodes[i];
		if (n.nodeType == 3) {
			//This is a text node
			txt = txt + n.nodeValue;
		}
	}
	
	return txt;
}

/*
 * Attaches an event listener function in a browser neutral fashion.
 * 
 * elementId - The ID of the element.
 * eventType - The type of event, such as "click", "mouseover".
 * listener - The event handler function.
 */
function attachEventListener(elementId, eventType, listener) {
	var e = document.getElementById(elementId);
	if (e.addEventListener) {
		//Most modern browsers
		e.addEventListener(eventType, listener, false);
	} else {
		//Old IE
		e.attachEvent("on" + eventType, listener);
	}
}
/*
 * Create the most optimal XML HTTP request
 * object for the browser.
 */
function createXHR() {
	var v;

	try {
		v = new XMLHttpRequest(); //Mozilla and IE7
	} catch (e) {
		v = createActivexXMLHttp(); //IE6 and back
	}

	if (v == null)
		throw new Error("XMLHttpRequest not supported");

	return v;
}

function createActivexXMLHttp() {
    var aVersions = [ "MSXML2.XMLHttp.5.0",
        "MSXML2.XMLHttp.4.0","MSXML2.XMLHttp.3.0",
        "MSXML2.XMLHttp","Microsoft.XMLHttp"];

    for (var i = 0; i < aVersions.length; i++) {
        try {
            var oXmlHttp = new ActiveXObject(aVersions[i]);
			
			return oXmlHttp;
        } catch (oError) {
            //Do nothing
        }
    }

    throw new Error("MSXML is not installed.");
}

/*
 * This function base64 encodes a string.
 */
function encodeBase64(str){
	var chr1, chr2, chr3, rez = '', arr = [], i = 0, j = 0, code = 0;
	var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/='.split('');

	while(code = str.charCodeAt(j++)){
		if(code < 128){
			arr[arr.length] = code;
		}
		else if(code < 2048){
			arr[arr.length] = 192 | (code >> 6);
			arr[arr.length] = 128 | (code & 63);
		}
		else if(code < 65536){
			arr[arr.length] = 224 | (code >> 12);
			arr[arr.length] = 128 | ((code >> 6) & 63);
			arr[arr.length] = 128 | (code & 63);
		}
		else{
			arr[arr.length] = 240 | (code >> 18);
			arr[arr.length] = 128 | ((code >> 12) & 63);
			arr[arr.length] = 128 | ((code >> 6) & 63);
			arr[arr.length] = 128 | (code & 63);
		}
	};

	while(i < arr.length){
		chr1 = arr[i++];
		chr2 = arr[i++];
		chr3 = arr[i++];

		rez += chars[chr1 >> 2];
		rez += chars[((chr1 & 3) << 4) | (chr2 >> 4)];
		rez += chars[chr2 === undefined ? 64 : ((chr2 & 15) << 2) | (chr3 >> 6)];
		rez += chars[chr3 === undefined ? 64 : chr3 & 63];
	};
	return rez;
}
