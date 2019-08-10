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
import dcdmod.Patches.AbstractAnimation;
import dcdmod.Power.KuugaSpecialPower;

/**
 * @author hoykj
 */
public class Another_GouramAttack1 extends AbstractGameEffect {
    private final int damage;
    private int stage;
    private AbstractCreature source, target;
    private Vector2 start, current ,Tstart;
    private AbstractAnimation Gouram_Attack;

    public Another_GouramAttack1(AbstractCreature source, AbstractCreature target, int damage) {
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

        this.duration -= Gdx.graphics.getDeltaTime();


        String ANOTHER_GOURAM_ATTACK_ATLAS;
        String ANOTHER_GOURAM_ATTACK_JSON;
        if (this.stage == 0){
            ANOTHER_GOURAM_ATTACK_ATLAS = "img/char/DCD_Animation/kuuga/gouram/gouram_attack.atlas";
            ANOTHER_GOURAM_ATTACK_JSON = "img/char/DCD_Animation/kuuga/gouram/gouram_attack.json";
            new AbstractAnimation("another_gouram_attack", ANOTHER_GOURAM_ATTACK_ATLAS, ANOTHER_GOURAM_ATTACK_JSON, 0.8f, this.source.drawX - this.source.hb_w*2, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
            Gouram_Attack = AbstractAnimation.getAnimation("another_gouram_attack");
            Gouram_Attack.setMovable(false);
            Gouram_Attack.state.setAnimation(0, "attack", false);
            CardCrawlGame.sound.playA("motorbike_sound", 0.0f);
            stage ++;
        }

        if(this.stage == 1){
            Gouram_Attack.drawX+= (this.target.hb.cX - this.current.x) / 0.4 * Gdx.graphics.getDeltaTime();
            if(Gouram_Attack.drawX + this.source.hb_w *2 > this.target.drawX){
                this.target.drawX += (this.target.hb.cX - this.current.x) / 0.2 * Gdx.graphics.getDeltaTime();
            }
        }

        if(this.stage == 1){
            if(Gouram_Attack.drawX > Settings.M_W + this.source.hb_w){
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
        else if(this.stage == 2){
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Another_GouramAttack2(this.source,this.target,this.start.x,this.start.y,this.Tstart.x,this.Tstart.y,Gouram_Attack)));
            stage ++;
        }




    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}