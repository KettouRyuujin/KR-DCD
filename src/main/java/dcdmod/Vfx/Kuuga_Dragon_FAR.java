package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

/**
 * @author hoykj
 */
public class Kuuga_Dragon_FAR extends AbstractGameEffect {
    private final int damage;
    private final int combo;
    private int stage;
    private AbstractCreature source, target;
    private Vector2 start, current;

    public Kuuga_Dragon_FAR(AbstractCreature source, AbstractCreature target, int damage, int combo) {
        this.duration = 3.2F;
        this.startingDuration = this.duration;
        this.stage = 0;
        this.source = source;
        this.target = target;
        this.start = new Vector2(source.drawX, source.drawY);
        this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2 + target.hb.height / 2);
        this.damage = damage;
        this.combo = combo;
    }

    public void update() {
        if(this.duration == this.startingDuration){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(113);//中途切换模型
        }

        this.duration -= Gdx.graphics.getDeltaTime();


        if((this.duration < this.startingDuration - 0.33F) && (this.duration > this.startingDuration - 0.46F)){
            this.source.drawY += 2000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.y += 2000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.source.drawX += 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.x += 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
        }

        if((this.duration < this.startingDuration - 0.46F) && this.stage == 0){
            if(this.source.drawY > this.target.drawY){
                this.source.drawY += (this.target.hb.cY - this.current.y) / 0.3 * Gdx.graphics.getDeltaTime();
            }
            else{
                this.stage ++;
            }
            if(this.source.drawX < this.target.drawX - this.target.hb_w/2){
                this.source.drawX += (this.target.hb.cX - this.current.x) / 0.2 * Gdx.graphics.getDeltaTime();
            }

        }

        if(this.stage == 1){
            if(this.combo >= 4){
                AbstractDungeon.effectsQueue.add(new Kuuga_Dragon_FAR2(this.target,this.damage,this.combo,this.start.x,this.start.y));
            }
            else{
                AbstractDungeon.effectsQueue.add(new Kuuga_DragonAttack(this.target,this.damage,this.combo,true,this.start.x,this.start.y));
            }
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}