package com.mygdx.game1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

public class MyGdxGame extends ApplicationAdapter {

	Stage stage;

	
	@Override
	public void create () {
		stage = new Stage();

		/*
		Image image = new Image(new Texture(Gdx.files.internal("badlogic.jpg")));
		image.setPosition(300,400);
		image.setWidth(250);
		image.setHeight(200);
		stage.addActor(image);
		*/

		Skin mySkin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

		Button button = new TextButton("Hello", mySkin);
		button.setBounds(200,200,100,100);
		button.addListener(
			new InputListener() {
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				}
			}
		);

		stage.addActor(button);

		Label label = new Label("plusieurs\nlignes !", mySkin);
		label.setAlignment(Align.center);
		label.setPosition(200, 400);
		stage.addActor(label);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
