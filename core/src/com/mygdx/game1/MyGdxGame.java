package com.mygdx.game1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class MyGdxGame extends ApplicationAdapter {

	private Stage stage;
	
	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

//		Image image = new Image(new Texture(Gdx.files.internal("badlogic.jpg")));
//		image.setPosition(300,400);
//		image.setWidth(250);
//		image.setHeight(200);
//		stage.addActor(image);
//
//
//		Label label = new Label("plusieurs\nlignes !", mySkin);
//		label.setAlignment(Align.center);
//		label.setPosition(200, 400);
//		stage.addActor(label);
//
//		Button sound = new ImageButton();
//		sound.setPosition(300, 400);
//		sound.setSize(50,50);
//		stage.addActor(sound);
//		ImageTextButton.ImageTextButtonStyle buttonStyle = new ImageTextButton.ImageTextButtonStyle();
//		buttonStyle.up = new TextureRegionDrawable(new Texture("button_up.png"));
//		buttonStyle.down = new TextureRegionDrawable(new Texture("button_down.png"));
//		buttonStyle.disabled = new TextureRegionDrawable(new Texture("button_disabled.png"));
//		buttonStyle.font = new BitmapFont();
//
//		ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle(buttonStyle);
//		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(buttonStyle);
//
//		Button button1 = new TextButton("test", textButtonStyle);
//		button1.setBounds(0,0,50,50);
//		stage.addActor(button1);
//
//		Button button2 = new TextButton("Play", mySkin);
//		button2.setBounds(100,0,100,100);
//		stage.addActor(button2);
//
//		Button button3 = new ImageButton(imageButtonStyle);
//		button3.setBounds(50,0,50,50);
//		stage.addActor(button3);
//
//		Label label = new Label("hello", mySkin);
//		label.setPosition(0, 100);
//		stage.addActor(label);

		Skin mySkin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

		// BG
		Image backgroundImage = new Image(new Texture("ui/BG.png"));
		backgroundImage.setSize(stage.getWidth(), stage.getHeight());
		stage.addActor(backgroundImage);

		// START
		final Label startLabel = new Label("TAP TO START !", mySkin);
		startLabel.setAlignment(Align.center);
		startLabel.setPosition(200, 250);
		stage.addActor(startLabel);

		// SOUND
		ImageButton.ImageButtonStyle soundButtonStyle = new ImageButton.ImageButtonStyle();
		soundButtonStyle.down = new TextureRegionDrawable(new Texture("ui/sound_green.png"));
		soundButtonStyle.up = new TextureRegionDrawable(new Texture("ui/sound_blue.png"));
		// soundButtonStyle.over = new TextureRegionDrawable(new Texture("ui/sound_blue.png"));
		// soundButtonStyle.disabled = new TextureRegionDrawable(new Texture("ui/sound_gris.png"));

		Button soundButton = new ImageButton(soundButtonStyle);
		soundButton.setBounds(100, 100, 100, 100);
		soundButton.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				startLabel.setText("up");
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				startLabel.setText("down");
				return true;
			}
		});
		stage.addActor(soundButton);

		// ABOUT
		ImageButton.ImageButtonStyle aboutButtonStyle = new ImageButton.ImageButtonStyle();
		aboutButtonStyle.down = new TextureRegionDrawable(new Texture("ui/about_green.png"));
		aboutButtonStyle.up = new TextureRegionDrawable(new Texture("ui/about_blue.png"));

		Button aboutButton = new ImageButton(aboutButtonStyle);
		aboutButton.setBounds(220, 100, 100, 100);
		stage.addActor(aboutButton);

		// LEVEL
		ImageButton.ImageButtonStyle levelButtonStyle = new ImageButton.ImageButtonStyle();
		levelButtonStyle.down = new TextureRegionDrawable(new Texture("ui/level_green.png"));
		levelButtonStyle.up = new TextureRegionDrawable(new Texture("ui/level_blue.png"));

		Button levelButton = new ImageButton(levelButtonStyle);
		levelButton.setBounds(340, 100, 100, 100);
		stage.addActor(levelButton);

		// TITLE
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = new BitmapFont();

		Label label1 = new Label("BALLS\nVS\nBRICKS", labelStyle);
		label1.setAlignment(Align.center);
		label1.setPosition(200, 300);
		stage.addActor(label1);

		stage.addActor(new MyParticles());

	}


	class MyParticles extends Actor {
		private TextureAtlas particleAtlas;
		private ParticleEffect effect;

		MyParticles() {
			// load some atlas with your particle assets in
			particleAtlas = new TextureAtlas("particles/texture.atlas");
			effect = new ParticleEffect();
			effect.load(Gdx.files.internal("particles/myp.p"), particleAtlas);

			effect.start();
			effect.setDuration(10);
		}

		@Override
		public void act(float delta) {
			effect.update(delta);

			// auto replay
			if(effect.isComplete()) effect.reset();

			effect.setPosition(
				Gdx.input.getX(),
				Gdx.graphics.getHeight() - Gdx.input.getY());
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			effect.draw(batch);
		}
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
