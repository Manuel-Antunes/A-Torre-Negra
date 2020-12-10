package com.aTorreNegra.screen;

import com.aTorreNegra.TorreNegra;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {

	private TorreNegra app1;

	public AbstractScreen(TorreNegra app) {
		this.app1 = app;
	}

	@Override
	public abstract void dispose();

	@Override
	public abstract void hide();

	@Override
	public abstract void pause();

	@Override
	public abstract void render(float delta);

	@Override
	public abstract void resize(int width, int height);

	@Override
	public abstract void resume();

	@Override
	public abstract void show();

	public TorreNegra getApp() {
		return app1;
	}

}
