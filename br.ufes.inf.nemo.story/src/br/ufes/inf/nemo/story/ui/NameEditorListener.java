package br.ufes.inf.nemo.story.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import stories.Link;
import stories.Node;
import stories.Node_state;
import stories.impl.LinkImpl;
import stories.impl.NodeImpl;
import stories.impl.Node_stateImpl;

public class NameEditorListener implements Listener {
		final Tree tree;
		TreeItem lastItem = null;
		final TreeEditor editor;
		final Color black;
		NameEditorListener(final Composite parent, Tree t){
			tree = t;
			black = parent.getDisplay().getSystemColor (SWT.COLOR_BLACK);
			editor = new TreeEditor (tree);
		}
		@Override
		public void handleEvent (Event event) {
			
			Point point = new Point (event.x, event.y);
			final TreeItem item = tree.getItem (point);
			int column =  StoryElementTimeline.getColumn(point,item);
			if (item != null && item == lastItem && column == 0) {
				boolean showBorder = true;
				final Composite composite = new Composite (tree, SWT.NONE);
				if (showBorder) composite.setBackground (black);
				final Text text = new Text (composite, SWT.NONE);
				final int inset = showBorder ? 1 : 0;
				composite.addListener (SWT.Resize, new Listener () {
					@Override
					public void handleEvent (Event e) {
						Rectangle rect = composite.getClientArea ();
						text.setBounds (rect.x + inset, rect.y + inset, rect.width - inset * 2, rect.height - inset * 2);
					}
				});
				Listener textListener = new Listener () {
					@Override
					public void handleEvent (final Event e) {
						switch (e.type) {
							case SWT.FocusOut:
								item.setText (text.getText ());
								editStoryElementName(item,text.getText ());
								composite.dispose ();
								break;
							case SWT.Verify:
								String newText = text.getText ();
								String leftText = newText.substring (0, e.start);
								String rightText = newText.substring (e.end, newText.length ());
								GC gc = new GC (text);
								Point size = gc.textExtent (leftText + e.text + rightText);
								gc.dispose ();
								size = text.computeSize (size.x, SWT.DEFAULT);
								editor.horizontalAlignment = SWT.LEFT;
								Rectangle itemRect = item.getBounds (), rect = tree.getClientArea ();
								editor.minimumWidth = Math.max (size.x, itemRect.width) + inset * 2;
								int left = itemRect.x, right = rect.x + rect.width;
								editor.minimumWidth = Math.min (editor.minimumWidth, right - left);
								editor.minimumHeight = size.y + inset * 2;
								editor.layout ();
								break;
							case SWT.Traverse:
								switch (e.detail) {
									case SWT.TRAVERSE_RETURN:
										item.setText (text.getText ());
										editStoryElementName(item,text.getText ());
										//FALL THROUGH
									case SWT.TRAVERSE_ESCAPE:
										composite.dispose ();
										e.doit = false;
								}
								break;
						}
					}
				};
				text.addListener (SWT.FocusOut, textListener);
				text.addListener (SWT.Traverse, textListener);
				text.addListener (SWT.Verify, textListener);
				editor.setEditor (composite, item);
				text.setText (item.getText ());
				
				text.selectAll ();
				text.setFocus ();
			}
			lastItem = item;
	}
		
	void editStoryElementName(TreeItem item, String name){
		Object data = item.getData();
		if(data!=null && data.getClass() == NodeImpl.class){
			Node n = (Node)data;
			n.setLabel(name);
		}
		else if( data!=null && data.getClass() == LinkImpl.class ){
			Link l  = (Link)data;
			l.setLabel(name);					
		}
		else if( data!=null && data.getClass() == Node_stateImpl.class ){
			Node_state ns  = (Node_state)data;
			ns.setLabel(name);	
		}
	}
}
