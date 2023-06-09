package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.AttackComponent;
import com.mygdx.game.components.CollisionComponent;
import com.mygdx.game.components.HealthComponent;
import com.mygdx.game.components.HeroComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.PriceComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.types.HeroType;

public class HeroFactory {
    //Creates a hero that is placed on the board
    public static Entity createHero(HeroType heroType, Vector2 boardPosition) {
        Entity hero = new Entity();
        hero.add(new SpriteComponent(getHeroSprite(heroType)));
        hero.add(new PositionComponent(boardPosition));
        hero.add(new AttackComponent(getHeroAttackTimer(heroType), getHeroAttackTimeElapsed(), getHeroAttackDamage(heroType)));
        hero.add(new HealthComponent(getHeroHealth()));
        hero.add(new HeroComponent(heroType));
        hero.add(new CollisionComponent(new Rectangle(boardPosition.x, boardPosition.y, Gdx.graphics.getWidth()/30f, Gdx.graphics.getHeight()/10f)));
        return hero;
    }

    //Create a display version of the hero on the left-hand side of the screen
    public static DisplayHero createDisplayHero(HeroType heroType, Vector2 position) {
        DisplayHero displayHero = new DisplayHero();
        displayHero.setSpriteComponent(new SpriteComponent(getHeroSprite(heroType)));
        displayHero.setPositionComponent(new PositionComponent(position));
        displayHero.setPriceComponent(new PriceComponent(getHeroPrice(heroType)));
        displayHero.setHeroComponent(new HeroComponent(heroType));
        return displayHero;
    }
    
    private static Texture getHeroSprite(HeroType heroType) {
        switch (heroType) {
            case IRONMAN:
                return new Texture(Gdx.files.internal("images/Hero_2.png"));
            case HULK:
                return new Texture(Gdx.files.internal("images/Hero_5.png"));
            case SPIDERMAN:
                return new Texture(Gdx.files.internal("images/Hero_1.png"));
            case THOR:
                return new Texture(Gdx.files.internal("images/Hero_3.png"));
            case CAPTAIN_AMERICA:
                return new Texture(Gdx.files.internal("images/Hero_4.png"));
            default:
                return null;
        }
    }

    private static Vector2 getHeroStartingPosition(HeroType heroType) {
        // Position for the display heroes
        switch (heroType) {
            case SUPERMAN:
                return new Vector2(50, 100);
            case IRONMAN:
                return new Vector2(50, 150);
            case HULK:
                return new Vector2(50, 200);
            case SPIDERMAN:
                return new Vector2(50, 250);
            case THOR:
                return new Vector2(50, 300);
            case CAPTAIN_AMERICA:
                return new Vector2(50, 350);
            default:
                return null;
        }
    }

    private static float getHeroAttackSpeed(HeroType heroType) {
        switch (heroType) {
            case SUPERMAN:
                return 2f;
            case IRONMAN:
                return 4f;
            case HULK:
                return 1f;
            case SPIDERMAN:
                return 2.5f;
            case THOR:
                return 1.5f;
            case CAPTAIN_AMERICA:
                return 5f;
            default:
                return 0;
        }
    }

    private static float getHeroAttackDamage(HeroType heroType) {
        switch (heroType) {
            case SUPERMAN:
                return 20f;
            case IRONMAN:
                return 15f;
            case HULK:
                return 30f;
            case SPIDERMAN:
                return 10f;
            case THOR:
                return 35f;
            case CAPTAIN_AMERICA:
                return 50f;
            default:
                return 0;
        }
    }

    private static float getHeroAttackTimer(HeroType heroType) {
        switch (heroType) {
            case IRONMAN:
                return 6.5f;
            case HULK:
                return 9f;
            case SPIDERMAN:
                return 6f;
            case THOR:
                return 7.5f;
            case CAPTAIN_AMERICA:
                return 7f;
            default:
                return 0;
        }
    }

    private static float getHeroAttackTimeElapsed() {
        return 0f;
    }

    private static int getHeroPrice(HeroType heroType) {
        switch (heroType) {
            case SUPERMAN:
                return 400;
            case IRONMAN:
                return 350;
            case HULK:
                return 500;
            case SPIDERMAN:
                return 200;
            case THOR:
                return 300;
            case CAPTAIN_AMERICA:
                return 650;
            default:
                return 0;
        }
    }

    private static int getHeroHealth() {
        return 3;
    }
}
