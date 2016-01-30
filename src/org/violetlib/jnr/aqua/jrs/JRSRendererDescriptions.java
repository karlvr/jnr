/*
 * Copyright (c) 2015 Alan Snyder.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the license agreement. For details see
 * accompanying license terms.
 */

package org.violetlib.jnr.aqua.jrs;

import org.jetbrains.annotations.*;

import org.violetlib.jnr.aqua.AquaUIPainter;
import org.violetlib.jnr.aqua.SegmentedButtonConfiguration;
import org.violetlib.jnr.aqua.coreui.CoreUIRendererDescriptions;
import org.violetlib.jnr.aqua.impl.NativeSupport;
import org.violetlib.jnr.impl.BasicRendererDescription;
import org.violetlib.jnr.impl.JNRUtils;
import org.violetlib.jnr.impl.RendererDescription;

/**
	Renderer descriptions for rendering on OS X 10.10 (Yosemite) using the JDK JRS classes.
*/

public class JRSRendererDescriptions
	extends CoreUIRendererDescriptions
{
	@Override
	public @NotNull RendererDescription getSegmentedButtonRendererDescription(@NotNull SegmentedButtonConfiguration g)
	{
		if (g.getWidget() == AquaUIPainter.SegmentedButtonWidget.BUTTON_SEGMENTED_TEXTURED_SEPARATED) {
			// an attempted workaround, must coordinate with renderer
			g = new SegmentedButtonConfiguration(AquaUIPainter.SegmentedButtonWidget.BUTTON_SEGMENTED_TEXTURED,
				g.getSize(), g.getState(), g.isSelected(), g.isFocused(), g.getDirection(), g.getPosition(),
				g.getLeftDividerState(), g.getRightDividerState());
		}

		RendererDescription rd = super.getSegmentedButtonRendererDescription(g);
		AquaUIPainter.SegmentedButtonWidget bw = g.getWidget();
		AquaUIPainter.Size sz = g.getSize();
		AquaUIPainter.Position position = g.getPosition();

		try {
			switch (bw)
			{
				case BUTTON_SEGMENTED:
				{
					if (sz == AquaUIPainter.Size.MINI) {
						rd = new BasicRendererDescription(0, 0, position == AquaUIPainter.Position.MIDDLE ? 1 : 0, 4);
					}
					float yOffset = JNRUtils.size(sz, -1, -1, 0);
					return JNRUtils.changeRendererDescription(rd, JNRUtils.NO_CHANGE, yOffset, JNRUtils.NO_CHANGE, JNRUtils.NO_CHANGE);
				}

				case BUTTON_SEGMENTED_INSET:
					return JNRUtils.changeRendererDescription(rd, JNRUtils.NO_CHANGE, -1, JNRUtils.NO_CHANGE, JNRUtils.NO_CHANGE);

				case BUTTON_SEGMENTED_SCURVE:
				case BUTTON_SEGMENTED_TEXTURED:
				case BUTTON_SEGMENTED_TOOLBAR:
					if (sz == AquaUIPainter.Size.MINI) {
						rd = new BasicRendererDescription(0, 0, position == AquaUIPainter.Position.MIDDLE ? 1 : 0, 4);
					}
					return JNRUtils.changeRendererDescription(rd, JNRUtils.NO_CHANGE, 0, JNRUtils.NO_CHANGE, JNRUtils.NO_CHANGE);
			}
		} catch (UnsupportedOperationException ex) {
			NativeSupport.log("Unable to adjust segmented button renderer description for " + g);
		}

		return rd;
	}
}
