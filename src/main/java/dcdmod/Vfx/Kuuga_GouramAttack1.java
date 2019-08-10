package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Power.KuugaSpecialPower;

/**
 * @author hoykj
 */
public class Kuuga_GouramAttack1 extends AbstractGameEffect {
    private final int damage;
    private int stage;
    private AbstractCreature source, target;
    private Vector2 start, current ,Tstart;

    public Kuuga_GouramAttack1(AbstractCreature source, AbstractCreature target, int damage) {
        this.duration = 2.0F;
        this.startingDuration = this.duration;
        this.stage = 0;
        this.source = source;
        this.target = target;
        this.start = new Vector2(source.drawX, source.drawY);
        this.Tstart = new Vector2(target.drawX, target.drawY);
        this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2 + target.hb.height / 2);
        this.damage = damage;
    }

    public void update() {
        if(this.duration == this.startingDuration){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(137);//中途切换模型
        }

        this.duration -= Gdx.graphics.getDeltaTime();


        if((this.duration < this.startingDuration - 0.2F) && (this.duration > this.startingDuration - 0.5F)){
            this.source.drawY += 1500 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.y += 1500 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.source.drawX -= 3000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.x -= 3000 * Settings.scale * Gdx.graphics.getDeltaTime();
        }

        if(this.stage == 2){
            this.source.drawX += (this.target.hb.cX - this.current.x) / 0.4 * Gdx.graphics.getDeltaTime();
            if(this.source.drawX + this.source.hb_w *2 > this.target.drawX){
                this.target.drawX += (this.target.hb.cX - this.current.x) / 0.2 * Gdx.graphics.getDeltaTime();
            }
        }

        if((this.duration < this.startingDuration - 0.5F) && this.stage == 0){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(138);//中途切换模型
            stage ++;
        }
        else if((this.duration < this.startingDuration - 0.5F) && this.stage == 1){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(140);//中途切换模型
            CardCrawlGame.sound.playA("motorbike_sound", 0.0f);
            this.source.drawY = this.start.y;
            stage ++;
        }
        else if(this.stage == 2){
            if(this.source.drawX > Settings.M_W + this.source.hb_w){
                stage ++;
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Gouram_Boom(this.target)));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target,new DamageInfo(this.source, this.damage, DamageInfo.DamageType.NORMAL)));
                if(this.source.hasPower("RisingMightyPower")){
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target,this.source, new KuugaSpecialPower(this.target,1), 1));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target,this.source, new KuugaSpecialPower(this.target,1), 1));
                for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    if ((!monster.isDead) && (!monster.isDying) && monster != this.target) {
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(this.source, this.damage/2, DamageInfo.DamageType.NORMAL)));
                        if(this.source.hasPower("RisingMightyPower")){
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,this.source, new KuugaSpecialPower(monster,1), 1));
                        }
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,this.source, new KuugaSpecialPower(monster,1), 1));
                    }
                }
            }
        }
        else if(this.stage == 3){
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_GouramAttack2(this.source,this.target,this.start.x,this.start.y,this.Tstart.x,this.Tstart.y)));
            stage ++;
        }




    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}