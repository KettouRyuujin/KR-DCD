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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

/**
 * @author hoykj
 */

public class Kuuga_Pegasus_FAR2 extends AbstractGameEffect {
    private final int damage;
    private int stage;
    private AbstractCreature source, target;
    private Vector2  current;

    public Kuuga_Pegasus_FAR2(AbstractCreature source, AbstractCreature target, int damage) {
        this.duration = 4.67F;
        this.startingDuration = this.duration;
        this.stage = 0;
        this.source = source;
        this.target = target;
        this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2 + target.hb.height / 2);
        this.damage = damage;
    }

    public void update() {
        if(this.duration == this.startingDuration){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(113);//中途切换模型
        }

        this.duration -= Gdx.graphics.getDeltaTime();



        if((this.duration < this.startingDuration) && (this.duration > this.startingDuration - 0.2F)){
            this.source.drawY += 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.y += 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
        }
        if((this.duration < this.startingDuration -2.18F) && (this.duration > this.startingDuration - 2.38F)){
            this.source.drawY -= 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.y -= 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
        }

        AbstractAnimation kuuga_pegasus_far;
        if(this.duration < this.startingDuration -0.2F && this.stage == 0){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(134);//中途切换模型
            CardCrawlGame.sound.playA("gouram_sound", 0.0f);
            this.stage ++;
        }
        else if(this.duration < this.startingDuration -0.7F && this.stage == 1){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(135);//中途切换模型
            CardCrawlGame.sound.playA("pegasus_charge", 0.0f);
            this.stage ++;
        }
        else if(this.duration < this.startingDuration -1.93F && this.stage == 2){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(136);//中途切换模型
            CardCrawlGame.sound.playA("pegasus_attack", 0.0f);
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {
                    CardCrawlGame.sound.playA("kuuga_attack", 0.0f);
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.HP_LOSS)));
                }
            }
            String KUUGA_ATTACKED_ATLAS;
            String KUUGA_ATTACKED_JSON;
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/FAR-R/pegasus_far-r_attacked1.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/FAR-R/pegasus_far-r_attacked1.json";
            new AbstractAnimation("Kuuga_Pegasus_MonsterAttacked2", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.4f, this.target.drawX, this.target.drawY + this.target.hb_h/2, this.target.hb_w, this.target.hb_h, 1.0f);
            kuuga_pegasus_far =  AbstractAnimation.getAnimation("Kuuga_Pegasus_MonsterAttacked2");
            kuuga_pegasus_far.setMovable(false);
            kuuga_pegasus_far.state.setAnimation(0, "attacked", false);
            this.stage ++;
        }
        else if(this.duration < this.startingDuration -3.07F && this.stage == 3){
            AbstractAnimation.clear("Kuuga_Pegasus_MonsterAttacked2");
            String KUUGA_ATTACKED_ATLAS;
            String KUUGA_ATTACKED_JSON;
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/FAR-R/pegasus_far-r_attacked2.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/FAR-R/pegasus_far-r_attacked2.json";
            new AbstractAnimation("Kuuga_Pegasus_MonsterAttacked2", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.4f, this.target.drawX, this.target.drawY + this.target.hb_h/2, this.target.hb_w, this.target.hb_h, 1.0f);
            kuuga_pegasus_far =  AbstractAnimation.getAnimation("Kuuga_Pegasus_MonsterAttacked2");
            kuuga_pegasus_far.setMovable(false);
            kuuga_pegasus_far.state.setAnimation(0, "attacked", false);
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {
                    CardCrawlGame.sound.playA("kuuga_attack", 0.0f);
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.HP_LOSS)));
                }
            }
            this.stage ++;
        }
        else if(this.duration < this.startingDuration -3.87F && this.stage == 4){
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {
                    CardCrawlGame.sound.playA("kuuga_attack", 0.0f);
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.HP_LOSS)));
                }
            }
            this.stage ++;
        }

        if(this.duration < 0.0F){
            AbstractAnimation.clear("Kuuga_Pegasus_MonsterAttacked2");
            AbstractDungeon.effectsQueue.add(new Kuuga_FAR_Background(true,false));
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(4);//中途切换模型
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}