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

import com.xenonteam.xenonlib.client.gui.element.IGuiElement;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public abstract class GuiFactory extends GuiContainer implements IGuiFactory {

	

	protected HashMap<String, IGuiElement> elements = new HashMap<String, IGuiElement>();
	private List old_keys = new ArrayList<String>();
	protected List keys = new ArrayList<String>();

	
	public GuiFactory(Container container)
	{
		super(container);
	}

	protected void orderKeys() {
		keys = old_keys;

		for (int i = 0; i < keys.size(); i++) {
			int priority = elements.get(old_keys.get(i)).getPriority();
			keys.set(priority, old_keys.get(i));
		}

		old_keys = keys;

	}

	@Override
	public boolean addElement(String ID, IGuiElement element) {
		if (!elements.containsKey(ID)) {
			elements.put(ID, element);
				old_keys.add(ID);
			orderKeys();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeElement(String ID) {
		if (elements.containsKey(ID)) {
			elements.remove(ID);
			
			for (int i = 0; i < old_keys.size(); i++) {
				if (old_keys.get(i).equals(ID)) {
					old_keys.remove(i);
				}
			}
			orderKeys();
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
