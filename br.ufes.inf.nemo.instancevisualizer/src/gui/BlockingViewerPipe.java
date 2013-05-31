package gui;

import org.graphstream.graph.Graph;
import org.graphstream.stream.thread.ThreadProxyPipe;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerPipe;
import org.miv.mbox.MBoxStandalone;

public class BlockingViewerPipe extends ViewerPipe {
	public BlockingViewerPipe(Viewer viewer) {
		super(String.format("viewer_%d", (int) (Math.random() * 10000)),
				new BlockingThreadProxyPipe(viewer.getGraphicGraph(), false));
	}

	private static class BlockingThreadProxyPipe extends ThreadProxyPipe {
		public BlockingThreadProxyPipe(Graph g, boolean replayGraph) {
			super(g, replayGraph);
			((MBoxStandalone) events).setNotifyOnPost(true);
		}

		@Override
		public void pump() {
			if (((MBoxStandalone) events).getMessageCount() == 0)
				try {
					synchronized (events) {
						events.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			super.pump();
		}
	}
}