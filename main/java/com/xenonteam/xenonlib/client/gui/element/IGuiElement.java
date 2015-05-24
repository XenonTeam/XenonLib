/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.awt.Point;

import org.w3c.dom.Element;

import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;

/**
 * The core interface for all drawable gui elements
 * 
 * @author tim4242
 * @author philipas
 * 
 */
public interface IGuiElement
{

	/**
	 * Returns the type of this {@link IGuiElement} 
	 * 
	 * @return The type of the {@link IGuiElement}
	 */
	public String getType();
	
	/**
	 * Draws the element to the provided {@link IGuiFactory}.
	 * 
	 * @param factory
	 *            The {@link IGuiFactory}
	 */
	public void draw(IGuiFactory factory);

	/**
	 * Sets the x offset
	 * 
	 * @param x
	 *            new offset
	 */
	public void setXOff(int x);

	/**
	 * Sets the y offset
	 * 
	 * @param y
	 *            new y offset
	 */
	public void setYOff(int y);

	/**
	 * Sets the position
	 * 
	 * @param x
	 *            new x offset
	 * @param y
	 *            new y offset
	 */
	public void setPos(int x, int y);

	/**
	 * Sets the position
	 * 
	 * @param p
	 *            new position
	 */
	public void setPos(Point p);

	/**
	 * Sets the priority (z order)
	 * 
	 * @param priority
	 *            new priority
	 */
	public void setPriority(int priority);

	/**
	 * Sets the height
	 * 
	 * @param height
	 *            new height
	 */
	public void setHeight(int height);

	/**
	 * sets the width
	 * 
	 * @param width
	 *            new width
	 */
	public void setWidth(int width);

	/**
	 * @return The x offset
	 */
	public int getXOff();

	/**
	 * @return The y offset
	 */
	public int getYOff();

	/**
	 * @return The priority (z order)
	 */
	public int getPriority();

	/**
	 * @return The height
	 */
	public int getHeight();

	/**
	 * @return The width
	 */
	public int getWidth();

	/**
	 * @return {@link IGuiElement} the is the parent
	 */
	public IGuiElement getParent();
	
	/**
	 * A version of {@link IGuiElement} meant to be interactive
	 * 
	 * @author tim4242
	 * @author philipas
	 *
	 */
	public interface IGuiActionProvider
	{
		/**
		 * Registers a {@link IGuiActionHandler}
		 * 
		 * @param handler
		 *            The handler to register
		 * @return If it was successful
		 */
		public boolean addActionHandler(IGuiActionHandler handler);

		/**
		 * Deregisters a {@link IGuiActionHandler}
		 * 
		 * @param handler
		 * @return
		 */
		public boolean removeActionHandler(IGuiActionHandler handler);
	}

	/**
	 * A interface to react to events
	 * 
	 * @author tim4242
	 * @author philipas
	 *
	 */
	public interface IGuiActionHandler extends IGuiElement
	{
		/**
		 * The method that gets called when an action occurs
		 * 
		 * @param action
		 *            The action
		 * @param args
		 *            The action specific arguments
		 */
		public void handleAction(int action, Object... args);
	}
	
	public interface IGuiSpriteHandler extends IGuiElement
	{
		
		/**
		 * Sets the sprite id
		 * 
		 * @param ID
		 *            The sprite id
		 * @param key
		 *            The key of the {@link com.xenonteam.xenonlib.client.render.SpriteSheet#Sprite SpriteSheet#Sprite}
		 */
		public void setSpriteID(String ID, String key);

		/**
		 * Sets the sprite id with the key default
		 * 
		 * @param ID
		 *            The sprite id
		 */
		public void setSpriteID(String ID);
		
		/**
		 * Sets the {@link com.xenonteam.xenonlib.client.render.SpriteSheet SpriteSheet} id
		 * 
		 * @param sheet new {@link com.xenonteam.xenonlib.client.render.SpriteSheet SpriteSheet} id
		 */
		public void setSpriteSheet(String sheetId);
		
		/**
		 * @return The {@link com.xenonteam.xenonlib.client.render.SpriteSheet SpriteSheet} id
		 */
		public String getSpriteSheet();
	}
	
	public static interface IGuiElementParser<T extends IGuiElement>
	{
		/**
		 * 
		 * Returns a new instance of of this {@link IGuiElement} according to the given {@link org.w3c.dom.Element}
		 * 
		 * @param elm The {@link org.w3c.dom.Element Element} to load from
		 * @return A new instance of of this {@link IGuiElement}
		 */
		public T parseXML(Element elm, int x, int y, int prio, int w, int h);
		
		public Class<T> getType();
	}

}
