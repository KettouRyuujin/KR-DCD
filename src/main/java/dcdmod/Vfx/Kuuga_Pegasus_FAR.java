package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

/**
 * @author hoykj
 */

public class Kuuga_Pegasus_FAR extends AbstractGameEffect {
    private final int damage;
    private int stage;
    private AbstractCreature source, target;
    private Vector2  current;
    private AbstractAnimation kuuga_pegasus_far = null;
    private String id;
    private static int x = 0;

    public Kuuga_Pegasus_FAR(AbstractCreature source, AbstractCreature target, int damage) {
        this.duration = 5.0F;
        this.startingDuration = this.duration;
        this.stage = 0;
        this.source = source;
        this.target = target;
        this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2 + target.hb.height / 2);
        this.damage = damage;
        this.id = "kuuga_attacked" + x;
    }

    public void update() {
        if(this.duration == this.startingDuration){
            final Decade Decade = (Decade) AbstractDungeon.player;
            Decade.Trickster(133);//中途切换模型
            CardCrawlGame.sound.playA("pegasus_charge", 0.0f);
        }

        this.duration -= Gdx.graphics.getDeltaTime();



        if((this.duration < this.startingDuration - 3.23F) && (this.stage == 0)){
            String KUUGA_KICK_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/pegasus_bullet.atlas";
            String KUUGA_KICK_JSON = "img/char/DCD_Animation/kuuga/pegasus/pegasus_bullet.json";
            new AbstractAnimation("kuuga_pegasus_far", KUUGA_KICK_ATLAS, KUUGA_KICK_JSON, 0.8f, this.source.drawX + this.source.hb_w/2, this.source.drawY + this.source.hb_h/2, this.source.hb_w, this.source.hb_h, 1.0f);
            kuuga_pegasus_far = AbstractAnimation.getAnimation("kuuga_pegasus_far");
            kuuga_pegasus_far.setMovable(false);
            kuuga_pegasus_far.state.setAnimation(0, "bullet", true);
            CardCrawlGame.sound.playA("pegasus_attack", 0.0f);
            this.stage ++;
        }
        else if(this.stage == 1){
            if(kuuga_pegasus_far.drawY > this.target.drawY){
                kuuga_pegasus_far.drawY += (this.target.hb.cY - this.current.y) / 0.1 * Gdx.graphics.getDeltaTime();
            }
            if(kuuga_pegasus_far.drawX < this.target.drawX){
                kuuga_pegasus_far.drawX += (this.target.hb.cX - this.current.x) / 0.1 * Gdx.graphics.getDeltaTime();
            }
            else{
                stage ++;
            }
        }
        else if(this.stage == 2){
            AbstractAnimation.clear("kuuga_pegasus_far");
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {
                    x += 1;
                    this.id = "kuuga_attacked" + x;
                    String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/pegasus_far_effect.atlas";
                    String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/pegasus_far_effect.json";
                    new AbstractAnimation(this.id, KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.8f, monster.drawX, monster.drawY + monster.hb_h/2, monster.hb_w, monster.hb_h, 1.0f);
                    AbstractAnimation kuuga_attacked = AbstractAnimation.getAnimation(this.id);
                    kuuga_attacked.setMovable(false);
                    kuuga_attacked.state.setAnimation(0, "effect", false);
                    stage ++;
                    AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_Pegasus_MonsterAttacked(monster,this.damage,this.id)));
                }
            }
            stage ++;
        }
        if(this.duration < 0.0F){
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