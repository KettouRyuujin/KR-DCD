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
public class Decade_Slash extends AbstractGameEffect {
    private final int damage;
    private int stage;
    private AbstractCreature source, target;
    private Vector2 start, current;

    public Decade_Slash(AbstractCreature source, AbstractCreature target, int x) {
        this.duration = 3.0F;
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
            Decade.Trickster(82);//中途切换模型
            this.stage ++;
        }
        else if(this.duration < this.startingDuration -1.5F && this.stage == 1){
            CardCrawlGame.sound.playA("decade_slash", 0F);
            this.stage ++;
        }
        else if(this.duration <= (this.startingDuration - 1.7F)&& (this.stage == 2)){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(83);//中途切换模型
            this.stage ++;
        }
        else if(this.stage == 3){
            if(this.source.drawX < this.target.drawX - this.target.hb_w/2){
                this.source.drawX += (this.target.hb.cX - this.current.x) / 0.5 * Gdx.graphics.getDeltaTime();
            }
            else{
                this.stage ++;
            }
            if(this.source.drawY > this.target.drawY){
                this.source.drawY -= (this.target.hb.cY - this.current.y) / 0.2 * Gdx.graphics.getDeltaTime();
            }
        }
        else if(this.stage == 4){
            AbstractDungeon.effectsQueue.add(new Decade_Slash2(this.source, this.target , this.damage,this.start.x,this.start.y));
            this.stage ++;
        }

        if (this.duration <= 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}