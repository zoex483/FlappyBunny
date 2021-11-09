package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Cat cat;
	Obstacles obstacles;
	boolean gameOver;
	Texture restartTexture;



	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		cat = new Cat();
		obstacles = new Obstacles();
		gameOver = false;
		restartTexture = new Texture("gameover.png");

	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg.render(batch);
		obstacles.render(batch);
		if(!gameOver) {
			cat.render(batch);
		}else{
			batch.draw(restartTexture, 220, 280);
		}
		batch.end();
	}

	public void update(){
		bg.update();
		cat.update();
		obstacles.update();
		for (int i = 0; i < Obstacles.obs.length; i++) {
			if(cat.position.x > Obstacles.obs[i].position.x && cat.position.x < Obstacles.obs[i].position.x+50){
				if(!Obstacles.obs[i].emptySpace.contains(cat.position)){
					gameOver = true;
				}
			}
		}
		if(cat.position.y <0 || cat.position.y > 600){
			gameOver = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver){
			recreate();
		}


	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void recreate(){
		cat.recreate();
		obstacles.recreate();
		gameOver = false;
	}
}