package com.anwanxtd;

import com.anwanxtd.tampilan.SplashScreen;
import com.badlogic.gdx.Game;

public class AnwanXTD extends Game {
	
	@Override
	public void create () {

	    this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {
        
	}
}
