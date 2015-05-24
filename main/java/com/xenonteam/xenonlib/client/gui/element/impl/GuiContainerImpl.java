/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.gui.inventory.GuiContainer;

import com.xenonteam.xenonlib.client.gui.element.GuiElement;
import com.xenonteam.xenonlib.client.gui.element.IGuiContainer;
import com.xenonteam.xenonlib.client.gui.element.IGuiElement;
import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;
import com.xenonteam.xenonlib.util.java.SortingUtils;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class GuiContainerImpl extends GuiElement implements IGuiContainer
{

	private Map<String, IGuiElement> elements;
	private List<String> old_keys = new ArrayList<String>();
	protected List<String> keys = new ArrayList<String>();
	
	/**
	 * @param parent
	 */
	protected GuiContainerImpl(IGuiContainer parent)
	{
		super(parent);

		elements = new HashMap<String, IGuiElement>();
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getType()
	 */
	@Override
	public String getType()
	{
		return "container_impl";
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#draw(com.xenonteam.xenonlib.client.gui.factory.IGuiFactory)
	 */
	@Override
	public void draw(IGuiFactory factory)
	{
		
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiContainer#getGuiScreen()
	 */
	@Override
	public GuiContainer getGuiScreen()
	{
		return null;
	}
	
	protected void orderKeys()
	{
		keys = SortingUtils.sortGuiElements(elements);
	}

	@Override
	public boolean addGuiElement(String ID, IGuiElement element)
	{
		if (!elements.containsKey(ID))
		{
			elements.put(ID, element);
			old_keys.add(ID);
			orderKeys();
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean removeGuiElement(String ID)
	{
		if (elements.containsKey(ID))
		{
			elements.remove(ID);

			for (int i = 0; i < old_keys.size(); i++)
			{
				if (old_keys.get(i).equals(ID))
				{
					old_keys.remove(i);
				}
			}
			orderKeys();
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public IGuiElement getGuiElement(String ID)
	{
		if (elements.containsKey(ID))
		{
			return elements.get(ID);
		}
		else
		{
			return null;
		}
	}

	@Override
	public boolean setElementPos(String id, Point p)
	{
		if (elements.containsKey(id))
		{
			setElementPos(id, p.x, p.y);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean setElementPos(String id, int x, int y)
	{
		if (elements.containsKey(id))
		{
			IGuiElement element = elements.get(id);
			element.setPos(x, y);
			return true;
		}
		else
		{
			return false;
		}
	}


}
