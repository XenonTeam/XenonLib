/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.xenonteam.xenonlib.client.gui.element.IGuiElement;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class SortingUtils
{
	
	public static void quickSort(ISortable[] a)
	{
		quickSort(a, 0, a.length - 1);
	}
	
	public static void quickSort(ISortable[] a, int p, int r)
	{
		if (p < r)
		{
			int q = partition(a, p, r);
			quickSort(a, p, q);
			quickSort(a, q + 1, r);
		}
	}

	private static int partition(ISortable[] a, int p, int r)
	{

		int x = a[p].getInt();
		int i = p - 1;
		int j = r + 1;

		while (true)
		{
			i++;
			while (i < r && a[i].getInt() < x)
				i++;
			j--;
			while (j > p && a[j].getInt() > x)
				j--;

			if (i < j)
				swap(a, i, j);
			else
				return j;
		}
	}

	private static void swap(ISortable[] a, int i, int j)
	{
		ISortable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static List<String> sortGuiElements(Map<String, IGuiElement> elms)
	{
		String[] temp = new String[elms.size()];
		
		Map.Entry<String , IGuiElement>[] elms2 = new Map.Entry[temp.length];
		new HashSet<Map.Entry<String , IGuiElement>>(elms.entrySet()).toArray(elms2);
		
		for(int i = 0; i < elms2.length; i++)
		{
			temp[i] = elms2[i].getValue().getInt() + "=:=" + elms2[i].getKey();
		}
		
		List<String> sortedGuiList = new ArrayList<String>();
		
		quickSortGui(temp, 0, temp.length - 1);
		
		for(int i = 0; i < temp.length; i++)
		{
			sortedGuiList.add(stringFromGuiElement(temp[i]));
		}
		
		return sortedGuiList;
	}
	
	public static void quickSortGui(String[] a, int p, int r)
	{
		if (p < r)
		{
			int q = partitionGui(a, p, r);
			quickSortGui(a, p, q);
			quickSortGui(a, q + 1, r);
		}
	}

	private static int partitionGui(String[] a, int p, int r)
	{

		int x = intFromGuiElement(a[p]);
		int i = p - 1;
		int j = r + 1;

		while (true)
		{
			i++;
			while (i < r && intFromGuiElement(a[i]) < x)
				i++;
			j--;
			while (j > p && intFromGuiElement(a[j]) > x)
				j--;

			if (i < j)
				swapGui(a, i, j);
			else
				return j;
		}
	}

	private static void swapGui(String[] a, int i, int j)
	{
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	private static int intFromGuiElement(String elm)
	{
		return Integer.parseInt(elm.split("=:=")[0]);
	}
	
	private static String stringFromGuiElement(String elm)
	{
		return elm.split("=:=")[1];
	}
	
	public static interface ISortable
	{
		public int getInt();
	}

}
