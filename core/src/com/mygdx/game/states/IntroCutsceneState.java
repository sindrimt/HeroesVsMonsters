package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.SoundManager;

public class IntroCutsceneState extends State {
    private GameStateManager gsm;
    private Texture cityBackground;
    private Texture explosionsBackground;
    private Texture nick;
    private Texture speech_1;
    private Texture speech_2;
    private Texture speech_3;
    private SoundManager soundmanager = SoundManager.getInstance();

    private float nickPositionX;
    private float nickWidth;
    private float nickHeight;
    private float slidingSpeed;
    private float elapsedTime;
    private int phase;

    public IntroCutsceneState() {
        cityBackground = new Texture("City.jpg");
        explosionsBackground = new Texture("Explosions.png");
        nick = new Texture("Nick.png");
        speech_1 = new Texture("speech_1.png");
        speech_2 = new Texture("speech_2.png");
        speech_3 = new Texture("speech_3.png");

        gsm = GameStateManager.getGsm();

        float nickPercentage = 0.4f; // Change this value to control the percentage of the screen width that Nick should occupy
        nickWidth = Gdx.graphics.getWidth() * nickPercentage;
        nickHeight = nick.getHeight() * (nickWidth / nick.getWidth());
        slidingSpeed = 400.0f; // Change to desired sliding speed
        nickPositionX = Gdx.graphics.getWidth();
        elapsedTime = 0.0f;
        phase = 0;
    }

    @Override
    public void update(float dt) {
        elapsedTime += dt;
        Boolean musicOn = false;
        switch (phase) {
            case 0:
                nickPositionX -= slidingSpeed * dt;
                if (nickPositionX <= Gdx.graphics.getWidth() - nickWidth) {
                    nickPositionX = Gdx.graphics.getWidth() - nickWidth;

                    soundmanager.playMusic("avengersHype", false);


                    phase = 1;
                    elapsedTime = 0;


                }
                break;
            case 1:
                if (elapsedTime >= 2.0f) {
                    soundmanager.playSound("hit2");
                    phase = 2;
                    elapsedTime = 0;

                }
                break;
            case 2:
                if (elapsedTime >= 2.0f) {
                    soundmanager.playSound("heroDeath");
                    phase = 3;
                    elapsedTime = 0;
                }
                break;
            case 3:
                if (elapsedTime >= 2.0f) {
                    soundmanager.playSound("hit2");
                    phase = 4;
                    elapsedTime = 0;
                }
                break;
            case 4:
                if (elapsedTime >= 3.0f) {
                    //System.out.println("Cutscene finished!");
                    soundmanager.stopMusic("avengersHype");
                    gsm.push(new PlayState());

                }
                break;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        ScreenUtils.clear(0, 0, 1, 1);
        sb.begin();

        if (phase < 3) {
            sb.draw(cityBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        } else {
            sb.draw(explosionsBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        sb.draw(nick, nickPositionX, 0, nickWidth, nickHeight);

        if (phase == 1) {
            sb.draw(speech_1, nickPositionX - speech_1.getWidth(), nickHeight - (Gdx.graphics.getHeight() * 0.2f ));
        } else if (phase == 2) {
            sb.draw(speech_2, nickPositionX - speech_2.getWidth(), nickHeight - (Gdx.graphics.getHeight() * 0.2f ));
        } else if (phase == 4) {
            sb.draw(speech_3, nickPositionX - speech_3.getWidth(), nickHeight - (Gdx.graphics.getHeight() * 0.2f ));
        }
        sb.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {
        cityBackground.dispose();
        explosionsBackground.dispose();
        nick.dispose();
        speech_1.dispose();
        speech_2.dispose();
        speech_3.dispose();
    }
}
