package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

/**
 * @author hoykj
 */
public class Kuuga_Titan_FAR extends AbstractGameEffect {
    private final int damage;
    private int stage;
    private AbstractCreature source, target;
    private Vector2 start, current;

    public Kuuga_Titan_FAR(AbstractCreature source, AbstractCreature target, int x) {
        this.duration = 99.0F;
        this.startingDuration = this.duration;
        this.stage = 0;
        this.source = source;
        this.target = target;
        this.start = new Vector2(source.drawX, source.drawY);
        this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2 + target.hb.height / 2);
        this.damage = x;
    }

    public void update() {

        this.duration -= Gdx.graphics.getDeltaTime();

        if(this.duration < this.startingDuration && this.stage == 0){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(118);//中途切换模型
            this.stage ++;
        }
        else if(this.duration < this.startingDuration -0.5F && this.stage == 1){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(9);//中途切换模型
            this.stage ++;
        }

        if(this.stage == 2){
            if(this.source.drawX < this.target.drawX - this.target.hb_w){
                this.source.drawX += (this.target.hb.cX - this.current.x) / 2.0 * Gdx.graphics.getDeltaTime();
            }
            else{
                this.stage ++;
            }
            if(this.source.drawY > this.target.drawY){
                this.source.drawY -= (this.target.hb.cY - this.current.y) / 0.2 * Gdx.graphics.getDeltaTime();
            }
        }
        else if(this.stage == 3){
            AbstractDungeon.effectsQueue.add(new Kuuga_Titan_FAR2(this.source, this.target , this.damage,this.start.x,this.start.y));
            this.stage ++;
            this.isDone= true;
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}