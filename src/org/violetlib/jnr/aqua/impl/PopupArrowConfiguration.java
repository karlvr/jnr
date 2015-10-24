package org.violetlib.jnr.aqua.impl;

import java.util.Objects;

import org.jetbrains.annotations.*;

import org.violetlib.jnr.aqua.Configuration;
import org.violetlib.jnr.aqua.LayoutConfiguration;
import org.violetlib.jnr.aqua.PopupButtonConfiguration;

/**
	A pseudo configuration for internal and evaluation use. Should not be used by clients.
*/

public class PopupArrowConfiguration
	extends LayoutConfiguration
	implements Configuration
{
	private final @NotNull PopupButtonConfiguration g;

	public PopupArrowConfiguration(@NotNull PopupButtonConfiguration g)
	{
		this.g = g;
	}

	public @NotNull PopupButtonConfiguration getPopupButtonConfiguration()
	{
		return g;
	}

	@Override
	public boolean equals(@Nullable Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PopupArrowConfiguration that = (PopupArrowConfiguration) o;
		return g == that.g;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(g);
	}
}
