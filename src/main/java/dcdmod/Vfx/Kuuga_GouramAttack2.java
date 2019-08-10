package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

/**
 * @author hoykj
 */
public class Kuuga_GouramAttack2 extends AbstractGameEffect {

    private int stage;
    private AbstractCreature source, target;
    private Vector2 start, current ,Tstart;

    Kuuga_GouramAttack2(AbstractCreature source, AbstractCreature target, float PlayerStartDrawX, float PlayerStartDrawY, float MonsterStartDrawX, float MonsterStartDrawY) {
        this.duration = 2.0F;
        this.startingDuration = this.duration;
        this.stage = 0;
        this.source = source;
        this.target = target;
        this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2 + target.hb.height / 2);
        this.start = new Vector2(PlayerStartDrawX, PlayerStartDrawY);
        this.Tstart = new Vector2(MonsterStartDrawX, MonsterStartDrawY);
    }

    public void update() {
        if(this.duration == this.startingDuration){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(138);//中途切换模型
        }

        this.duration -= Gdx.graphics.getDeltaTime();


        if((this.duration < this.startingDuration - 0.2F) && (this.duration > this.startingDuration - 0.5F)){
            this.source.drawY += 3000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.y += 3000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.source.drawX -= 2000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.x -= 2000 * Settings.scale * Gdx.graphics.getDeltaTime();
        }

        if(this.duration < this.startingDuration - 0.5F && stage == 0){
            if(this.source.drawY > this.start.y){
                this.source.drawY += (this.target.hb.cY - this.current.y) / 0.3 * Gdx.graphics.getDeltaTime();
                this.source.drawX -= (this.target.hb.cX - this.current.x) / 0.3 * Gdx.graphics.getDeltaTime();
            }
            else{
                stage ++;
            }
        }
        else if(this.stage == 1){
            if(this.source.hasPower("KamenRideKuugaPower")){
                final Decade Decade = (Decade) AbstractDungeon.player;
                Decade.Trickster(142);//中途切换模型
            }
            else{
                final Decade Decade = (Decade) AbstractDungeon.player;
                Decade.Trickster(139);//中途切换模型
            }
            stage ++;
        }

        if(this.duration < 0.0F){
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_FAR_Background(true,false)));
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(4);//中途切换模型
            this.source.drawX = this.start.x;
            this.source.drawY = this.start.y;
            this.target.drawX = this.Tstart.x;
            this.target.drawY = this.Tstart.y;
            this.isDone = true;
        }



    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}