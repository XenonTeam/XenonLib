/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.factory;

import java.awt.Point;
import java.util.HashMap;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public abstract class GuiFactory extends GuiContainer implements IGuiFactory {

	protected HashMap<String, IGuiElement> elements = new HashMap<String, IGuiElement>();
	protected String[] m_orderedKeys;

	public GuiFactory(Container container) {
		super(container);
		
		m_orderedKeys = (String[]) elements.entrySet().toArray();
	}
	
	protected void orderKeys()
	{
		String[] m_new = m_orderedKeys;
		
		for(String s : elements.keySet())
		{
			int imp = elements.get(s).getPriority();
			for(int i = 0; i < m_orderedKeys.length; i++)
			{
				int imp2 = elements.get(m_orderedKeys[i]).getPriority();
			}
		}
	}

	@Override
	public boolean addElement(String ID, IGuiElement element) {
		if (!elements.containsKey(ID)) {
			elements.put(ID, element);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeElement(String ID) {
		if (elements.containsKey(ID)) {
			elements.remove(ID);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public IGuiElement getElement(String ID) {
		if (elements.containsKey(ID)) {
			return elements.get(ID);
		} else {
			return null;
		}
	}

	@Override
	public boolean setElementPos(String id, int x, int y) {
		if (elements.containsKey(id)) {
			IGuiElement element = elements.get(id);
			element.setPos(x, y);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean setElementPos(String id, Point p) {
		if (elements.containsKey(id)) {
			setElementPos(id, p.x, p.y);
			return true;
		} else {
			return false;
		}
	}

}