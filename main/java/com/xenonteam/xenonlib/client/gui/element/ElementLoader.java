/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiElementParser;
import com.xenonteam.xenonlib.util.XUtils;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class ElementLoader
{

	private static HashMap<String, IGuiElementParser<?>> m_parsers = new HashMap<String, IGuiElementParser<?>>();

	public static void addTypeToParse(String type, IGuiElementParser<?> elm)
	{
		m_parsers.put(type, elm);
	}

	public static List<IGuiElement> getElements(LoaderType type, ResourceLocation loc)
	{
		try
		{

			switch(type)
			{
				case XML:
					return getElementsFromXML(loc);
			}

		} catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static List<IGuiElement> getElementsFromXML(ResourceLocation loc) throws Exception
	{
		List<IGuiElement> res = new ArrayList<IGuiElement>();

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

		Document doc = db.parse(stream);

		NodeList root = doc.getChildNodes();

		for (int i = 0; i < root.getLength(); i++)
		{

			Node node = root.item(i);

			if (node.getNodeType() != Node.ELEMENT_NODE)
			{
				return null;
			}

			Element XMLElm = (Element) node;

			if (!XMLElm.getNodeName().equalsIgnoreCase("element"))
			{
				return null;
			}

			IGuiElement elm = parseFromXML(XMLElm);

			if (elm == null)
				return null;

			res.add(elm);
		}

		return res;
	}

	public static IGuiElement parseFromXML(Element elm)
	{

		String name = null;
		String type = null;
		int x = 0;
		int y = 0;
		int w = 0;
		int h = 0;
		int prio = 0;

		if (!elm.hasAttribute("name"))
		{
			return null;
		} else
		{
			name = elm.getAttribute("name");
		}

		if (!elm.hasAttribute("type"))
		{
			return null;
		} else
		{
			name = elm.getAttribute("type");
		}

		if (elm.hasAttribute("x"))
			x = Integer.parseInt(elm.getAttribute("x"));
		if (elm.hasAttribute("y"))
			x = Integer.parseInt(elm.getAttribute("y"));
		if (elm.hasAttribute("w"))
			x = Integer.parseInt(elm.getAttribute("w"));
		if (elm.hasAttribute("h"))
			x = Integer.parseInt(elm.getAttribute("h"));
		if (elm.hasAttribute("prio"))
			x = Integer.parseInt(elm.getAttribute("prio"));

		return m_parsers.get(type).parseXML(elm, x, y, prio, w, h);

	}

	public static enum LoaderType
	{
		XML;
	}

}
