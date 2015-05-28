/**
 * 
 */
package com.xenonteam.xenonlib.api.book;

import com.xenonteam.xenonlib.client.render.SpriteSheet.Sprite;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class BookCategory
{

	private String m_title;
	private String m_spriteSheet;
	private String m_titleImg;
	
	public BookCategory(String title, String sheet, String titleImg)
	{
		m_title = title;
		m_spriteSheet = sheet;
		m_titleImg = titleImg;
	}
	
	public String getTitle()
	{
		return m_title;
	}
	
	public String getSpriteSheedID()
	{
		return m_spriteSheet;
	}
	
	public String getTitleSpriteIF()
	{
		return m_titleImg;
	}
	
}
