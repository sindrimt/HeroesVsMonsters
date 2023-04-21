package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.mygdx.game.components.AttackComponent;
import com.mygdx.game.components.CollisionComponent;
import com.mygdx.game.components.HealthComponent;
import com.mygdx.game.components.MonsterComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.ProjectileComponent;
import com.mygdx.game.components.SpriteComponent;

public class CollisionSystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> healthMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<CollisionComponent> collisionMapper;
    private ComponentMapper<MonsterComponent> monsterMapper;
    private ComponentMapper<ProjectileComponent> projectileMapper;
    private ImmutableArray<Entity> monsters;
    private Engine engine;

    public CollisionSystem(Engine engine) {
        super(Family.all(PositionComponent.class, CollisionComponent.class).get());
        healthMapper = ComponentMapper.getFor(HealthComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
        monsterMapper = ComponentMapper.getFor(MonsterComponent.class);
        projectileMapper = ComponentMapper.getFor(ProjectileComponent.class);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = positionMapper.get(entity);
        CollisionComponent collision = collisionMapper.get(entity);
        ProjectileComponent projectile = projectileMapper.get(entity);

        //Check if projectile has collided with a monster
        // Check if the entity is a projectile
        if (projectileMapper.has(entity)) {
            monsters = engine.getEntitiesFor(Family.all(MonsterComponent.class).get());

            if (monsters.size() > 0) {
                for (Entity monster : monsters) {
                    //TODO
                }
            }
        }
    }
}