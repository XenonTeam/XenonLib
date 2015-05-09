/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.factory;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import com.xenonteam.xenonlib.client.gui.element.IGuiContainer;
import com.xenonteam.xenonlib.client.gui.element.IGuiElement;
import com.xenonteam.xenonlib.util.java.SortingUtils;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public abstract class GuiFactory extends GuiContainer implements IGuiFactory, IGuiContainer, IGuiElement.IGuiActionHandler
{

	protected HashMap<String, IGuiElement> elements = new HashMap<String, IGuiElement>();
	private List<String> old_keys = new ArrayList<String>();
	protected List<String> keys = new ArrayList<String>();

	protected ResourceLocation m_resLoc;
	
	protected int m_mouseX, m_mouseY, m_mouseButton;
	
	

	public GuiFactory(Container container)
	{
		super(container);

		m_resLoc = new ResourceLocation("xenon_lib:textures/gui/GenBackground.png");
		
		generate(null);
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

	public GuiContainer getGuiScreen()
	{
		return this;
	}

	@Override
	public boolean addElement(String ID, IGuiElement element)
	{

		if (!elements.containsKey(ID))
		{
			elements.put(ID, element);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean removeElement(String ID)
	{
		if (elements.containsKey(ID))
		{
			elements.remove(ID);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public IGuiElement getElement(String ID)
	{
		return elements.get(ID);
	}

	public void draw(IGuiFactory factory)
	{
		for(String s : keys)
		{
			elements.get(s).draw(factory);
		}
	}

	public void setXOff(int x)
	{
	}

	public void setYOff(int y)
	{
	}

	public void setPos(int x, int y)
	{
	}

	public void setPos(Point p)
	{
	}

	public void setPriority(int priority)
	{
	}

	public void setHeight(int height)
	{
	}

	public void setWidth(int width)
	{
	}

	public int getXOff()
	{
		return 0;
	}

	public int getYOff()
	{
		return 0;
	}

	public int getPriority()
	{
		return 0;
	}

	public int getHeight()
	{
		return this.height;
	}

	public int getWidth()
	{
		return this.width;
	}

	public IGuiElement getParent()
	{
		return this;
	}

	public ResourceLocation getResource()
	{
		return m_resLoc;
	}

	public void setResource(ResourceLocation loc)
	{
		
	}
	
	public GuiContainer getContainer()
	{
		return this;
	}
	
	public int getMouseX()
	{
		return m_mouseX;
	}
	
	public int getMouseY()
	{
		return m_mouseY;
	}
	
	public boolean getMouseClicked()
	{
		return (m_mouseButton != -1 ? true : false);
	}
	
	public void mouseClicked(int mX, int mY, int button)
	{
		m_mouseX = mX;
		m_mouseY = mY;
		
		m_mouseButton = button;
	}
	
	public void mouseReleased(int mX, int mY, int mouseButton)
	{
		m_mouseButton = -1;
	}

}
