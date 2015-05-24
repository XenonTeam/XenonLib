/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.minecraft.util.ResourceLocation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xenonteam.xenonlib.util.XUtils;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class ElementLoader
{

	public static List<GuiElement> getElements(LoaderType type, ResourceLocation loc)
	{
		try
		{
		
		switch(type)
		{
			case XML:
				return getElementsFromXML(loc);
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static List<GuiElement> getElementsFromXML(ResourceLocation loc) throws Exception
	{
		List<GuiElement> res = new ArrayList<GuiElement>();

		InputStream stream = XUtils.getStreamFromRL(loc);
		
		if (stream == null)
			return null;
		
		      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		      dbf.setValidating(false);
		      dbf.setIgnoringComments(false);
		      dbf.setIgnoringElementContentWhitespace(true);
		      dbf.setNamespaceAware(true);

		      DocumentBuilder db = null;
		      db = dbf.newDocumentBuilder();

		      Document doc =  db.parse(stream);
		      
		      NodeList root = doc.getChildNodes();
		      
		      for(int i = 0; i < root.getLength(); i++)
		      {
		    	  
		    	  Node node = root.item(i);
		    	  
		    	  if(node.getNodeType() != Node.ELEMENT_NODE)
		    	  {
		    		  return null;
		    	  }
		    	  
		    	  Element XMLElm = (Element) node;
		    	  
		    	  if(!XMLElm.getNodeName().equalsIgnoreCase("element"))
		    	  {
		    		  return null;
		    	  }
		    	  
		    	  GuiElement elm = parseFromXML(XMLElm);
		    	  
		    	  if(elm == null)
		    		  return null;
		    	  
		    	  res.add(elm);
		      }

		return res;
	}
	
	public static GuiElement parseFromXML(Element elm)
	{
		GuiElement res = null;
		
			if(!elm.hasAttribute("name") || !elm.hasAttribute("type"))
			{
				return null;
			}
		
			String name = elm.getAttribute("name");	
			String type = elm.getAttribute("type");
			
			
		
		return res;
	}

	public static enum LoaderType
	{
		XML;
	}

}
