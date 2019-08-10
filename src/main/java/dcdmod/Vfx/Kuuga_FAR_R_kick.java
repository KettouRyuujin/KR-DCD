package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

/**
 * @author hoykj
 */
public class Kuuga_FAR_R_kick extends AbstractGameEffect {
    private final int damage;
    private int stage;
    private AbstractCreature source, target;
    private Vector2 start, current;
    private AbstractAnimation kuuga_far_kick = null;

    public Kuuga_FAR_R_kick(AbstractCreature source, AbstractCreature target, int x) {
        this.duration = 3.2F;
        this.startingDuration = this.duration;
        this.stage = 0;
        this.source = source;
        this.target = target;
        this.start = new Vector2(source.drawX, source.drawY);
        this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2 + target.hb.height / 2);
        this.damage = x;
    }

    public void update() {
        if(this.duration == this.startingDuration){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(125);//中途切换模型
        }

        this.duration -= Gdx.graphics.getDeltaTime();

        if((this.duration < this.startingDuration - 1.0F) && (this.stage == 0)){
            CardCrawlGame.sound.playA("kuuga_currentsound", 0.0f);
            this.stage ++;
        }

        else if((this.duration < this.startingDuration - 1.62F) && (this.stage == 1)){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(126);//中途切换模型
            this.stage ++;
        }

        if((this.duration < this.startingDuration - 1.62F) && (this.duration > this.startingDuration - 2.12F)){
            this.source.drawY += 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.y += 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.source.drawX += 500 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.x += 500 * Settings.scale * Gdx.graphics.getDeltaTime();
        }

        if((this.duration < this.startingDuration - 1.72F) && this.stage == 2){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(127);//中途切换模型
            this.stage++;
        }
        else if((this.duration < this.startingDuration - 1.82F) && this.stage == 3){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(128);//中途切换模型
            this.stage++;
        }
        else if((this.duration < this.startingDuration - 2.12F)){
            if(this.stage == 4){
                final Decade Decade = (Decade) AbstractDungeon.player;
                Decade.Trickster(104);//中途切换模型
                String KUUGA_KICK_ATLAS = "img/char/DCD_Animation/kuuga/FAR-R/Kuuga_FAR-R5.atlas";
                String KUUGA_KICK_JSON = "img/char/DCD_Animation/kuuga/FAR-R/Kuuga_FAR-R5.json";
                new AbstractAnimation("kuuga_far_kick", KUUGA_KICK_ATLAS, KUUGA_KICK_JSON, 0.8f, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
                kuuga_far_kick = AbstractAnimation.getAnimation("kuuga_far_kick");
                kuuga_far_kick.setMovable(false);
                kuuga_far_kick.state.setAnimation(0, "FAR5", false);
                stage ++;
            }
            if(this.source.drawY > this.target.drawY){
                this.source.drawY += (this.target.hb.cY - this.current.y) / 0.5 * Gdx.graphics.getDeltaTime();
                kuuga_far_kick.drawY += (this.target.hb.cY - this.current.y) / 0.5 * Gdx.graphics.getDeltaTime();
            }
            if(this.source.drawX < this.target.drawX){
                this.source.drawX += (this.target.hb.cX - this.current.x) / 0.5 * Gdx.graphics.getDeltaTime();
                kuuga_far_kick.drawX += (this.target.hb.cX - this.current.x) / 0.5 * Gdx.graphics.getDeltaTime();
            }
            else{
                AbstractAnimation.clear("kuuga_far_kick");
                AbstractDungeon.actionManager.addToTop(new DamageAction(this.target,new DamageInfo(this.source, this.damage, DamageInfo.DamageType.NORMAL)));
                AbstractDungeon.effectsQueue.add(new Kuuga_FAR_R_kick2(this.source, this.target , this.damage,this.start.x,this.start.y));
                this.isDone = true;
            }
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}