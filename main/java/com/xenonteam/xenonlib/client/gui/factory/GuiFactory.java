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
	protected String[] old_keys;
	protected String[] keys;

	public GuiFactory(Container container) {
		super(container);
		
		keys = (String[]) elements.entrySet().toArray();
	}
	
	protected void orderKeys()
	{
		keys = old_keys;
		
		
		
		for (int i = 0; i < keys.length; i++) {
			int priority = elements.get(old_keys[i]).getPriority();
			keys[priority] = old_keys[i];
		}
		
		old_keys = keys;
	
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
